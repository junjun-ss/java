import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MessageConsumer {
    private static final String QUEUE_URL = "http://localhost:8080/queue"; // 메시지 큐의 URL 입력

    public static void main(String[] args) {
        // 메시지 수신
        String message = receiveMessage();
        System.out.println("받은 메시지: " + message);
    }

    public static String receiveMessage() {
        try {
            URL url = new URL(QUEUE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                conn.disconnect();

                return response.toString();
            } else {
                System.out.println("메시지 수신 실패: " + responseCode);
            }

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
