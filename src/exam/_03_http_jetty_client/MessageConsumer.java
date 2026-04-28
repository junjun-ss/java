package exam._03_http_jetty_client;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;

import java.util.concurrent.TimeUnit;

public class MessageConsumer {
    private static final String QUEUE_URL = "http://localhost:8080/queue";

    public static void main(String[] args) {
        String message = receiveMessage();
        System.out.println("받은 메시지: " + message);
    }

    public static String receiveMessage() {
        HttpClient httpClient = new HttpClient();

        try {
            httpClient.start();
            ContentResponse response = httpClient.newRequest(QUEUE_URL)
                    .method("GET")
                    .timeout(3, TimeUnit.SECONDS)
                    .send();

            if (response.getStatus() == 200) {
                return response.getContentAsString();
            }

            System.out.println("메시지 수신 실패: " + response.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
