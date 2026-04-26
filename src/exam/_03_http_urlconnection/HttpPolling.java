package exam._03_http_urlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPolling {
    private static final String DATA_URL = "http://localhost:8080/data"; // 데이터를 폴링할 URL 입력

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

                Thread.sleep(100); // 0.1초 대기
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String pollData() {
        try {
            URL url = new URL(DATA_URL);
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
                System.out.println("데이터 폴링 실패: " + responseCode);
            }

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
