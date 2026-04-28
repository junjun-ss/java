package exam._03_http_jetty_client;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;

import java.util.concurrent.TimeUnit;

public class MessageProducer {
    private static final String QUEUE_URL = "http://localhost:8080/queue";

    public static void main(String[] args) {
        sendMessage("Hello, World!");
    }

    public static void sendMessage(String message) {
        HttpClient httpClient = new HttpClient();

        try {
            httpClient.start();
            ContentResponse response = httpClient.newRequest(QUEUE_URL)
                    .method("POST")
                    .timeout(3, TimeUnit.SECONDS)
                    .header("Content-Type", "text/plain;charset=UTF-8")
                    .content(new StringContentProvider(message))
                    .send();

            if (response.getStatus() == 200) {
                System.out.println("메시지 전송 성공: " + message);
            } else {
                System.out.println("메시지 전송 실패: " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
