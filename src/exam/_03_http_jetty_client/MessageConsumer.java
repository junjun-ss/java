package exam._03_http_jetty_client;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.server.Server;

import java.util.concurrent.TimeUnit;

public class MessageConsumer {
    private static final String QUEUE_URL = "http://localhost:18081/queue";

    public static void main(String[] args) throws Exception {
        Server server = LocalQueueServer.start(18081);
        try {
            MessageProducer.sendMessage(QUEUE_URL, "queued-message");
            String message = receiveMessage(QUEUE_URL);
            System.out.println("받은 메시지: " + message);
        } finally {
            server.stop();
            server.join();
        }
    }

    public static String receiveMessage(String url) throws Exception {
        HttpClient httpClient = new HttpClient();
        try {
            httpClient.start();
            ContentResponse response = get(httpClient, url);
            validate(response);
            return response.getContentAsString().trim();
        } finally {
            httpClient.stop();
        }
    }

    public static ContentResponse get(HttpClient httpClient, String url) throws Exception {
        return httpClient.newRequest(url)
                .method("GET")
                .timeout(3, TimeUnit.SECONDS)
                .send();
    }

    private static void validate(ContentResponse response) {
        if (response.getStatus() < 200 || response.getStatus() >= 300) {
            throw new IllegalStateException("메시지 수신 실패: " + response.getStatus());
        }
    }
}
