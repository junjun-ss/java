package exam._03_http_jetty_client;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.server.Server;

import java.util.concurrent.TimeUnit;

public class MessageProducer {
    private static final String QUEUE_URL = "http://localhost:18081/queue";

    public static void main(String[] args) throws Exception {
        Server server = LocalQueueServer.start(18081);
        try {
            String response = sendMessage(QUEUE_URL, "Hello, World!");
            System.out.println(response);
        } finally {
            server.stop();
            server.join();
        }
    }

    public static String sendMessage(String url, String message) throws Exception {
        HttpClient httpClient = new HttpClient();
        try {
            httpClient.start();
            ContentResponse response = postText(httpClient, url, message);
            validate(response);
            return response.getContentAsString();
        } finally {
            httpClient.stop();
        }
    }

    public static ContentResponse postText(HttpClient httpClient, String url, String message) throws Exception {
        return httpClient.newRequest(url)
                    .method("POST")
                    .timeout(3, TimeUnit.SECONDS)
                    .header("Content-Type", "text/plain;charset=UTF-8")
                    .content(new StringContentProvider(message))
                    .send();
    }

    private static void validate(ContentResponse response) {
        if (response.getStatus() < 200 || response.getStatus() >= 300) {
            throw new IllegalStateException("메시지 전송 실패: " + response.getStatus());
        }
    }
}
