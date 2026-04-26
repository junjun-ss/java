import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.net.HttpURLConnection;
import java.net.URL;

public class MessageProducer {
    private static final String QUEUE_URL = "http://localhost:8080/queue"; // 메시지 큐의 URL 입력

    public static void main(String[] args) {
        // 메시지 전송
        sendMessage("Hello, World!");
    }

    public static void sendMessage(String message) {
        try {
            URL url = new URL(QUEUE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // 메시지를 POST 요청의 body에 포함하여 전송
            conn.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("메시지 전송 성공: " + message);
            } else {
                System.out.println("메시지 전송 실패: " + responseCode);
            }

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
