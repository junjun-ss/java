package exam._03_http_jetty_client;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;

import java.util.concurrent.TimeUnit;

public class HttpPolling {
    private static final String DATA_URL = "http://localhost:8080/data";
    private static final int POLLING_INTERVAL_MS = 1000;

    public static void main(String[] args) {
        startPolling();
    }

    public static void startPolling() {
        HttpClient httpClient = new HttpClient();

        try {
            httpClient.start();

            while (true) {
                String data = pollData(httpClient);
                if (data != null) {
                    System.out.println("받은 데이터: " + data);
                }

                Thread.sleep(POLLING_INTERVAL_MS);
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

    public static String pollData(HttpClient httpClient) {
        try {
            ContentResponse response = httpClient.newRequest(DATA_URL)
                    .method("GET")
                    .timeout(3, TimeUnit.SECONDS)
                    .send();

            if (response.getStatus() == 200) {
                return response.getContentAsString();
            }

            System.out.println("데이터 폴링 실패: " + response.getStatus());
        } catch (Exception e) {
            System.out.println("데이터 폴링 중 오류: " + e.getMessage());
        }

        return null;
    }
}
