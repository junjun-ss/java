package exam._03_http_urlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPolling {
    private static final String DATA_URL = "http://localhost:8080/data"; // 데이터를 폴링할 URL 입력
    private static final int CONNECT_TIMEOUT_MS = 3000;
    private static final int READ_TIMEOUT_MS = 3000;
    private static final int POLLING_INTERVAL_MS = 1000;

    public static void main(String[] args) {
        startPolling();
    }

    public static void startPolling() {
        try {
            while (true) {
                // 데이터 폴링
                String data = pollData();
                if (data != null) {
                    System.out.println("받은 데이터: " + data);
                }

                Thread.sleep(POLLING_INTERVAL_MS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String pollData() {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(DATA_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(CONNECT_TIMEOUT_MS);
            conn.setReadTimeout(READ_TIMEOUT_MS);

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    return response.toString();
                }
            } else {
                System.out.println("데이터 폴링 실패: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("데이터 폴링 중 오류: " + e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return null;
    }
}
