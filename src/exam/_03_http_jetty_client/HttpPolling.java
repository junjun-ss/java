package exam._03_http_jetty_client;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.server.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HttpPolling {
    private static final String DATA_URL = "http://localhost:18081/data";
    private static final int POLLING_INTERVAL_MS = 1000;

    public static void main(String[] args) throws Exception {
        Server server = LocalQueueServer.start(18081);
        try {
            List<String> values = pollFixedTimes(DATA_URL, 3, 100);
            System.out.println(values);
        } finally {
            server.stop();
            server.join();
        }
    }

    public static List<String> pollFixedTimes(String url, int count, long intervalMillis) throws Exception {
        HttpClient httpClient = new HttpClient();
        List<String> result = new ArrayList<>();
        try {
            httpClient.start();
            for (int i = 0; i < count; i++) {
                result.add(pollData(httpClient, url));
                Thread.sleep(intervalMillis);
            }
            return result;
        } finally {
            httpClient.stop();
        }
    }

    public static void startPollingForever(String url) throws Exception {
        while (true) {
            for (String value : pollFixedTimes(url, 1, POLLING_INTERVAL_MS)) {
                System.out.println("받은 데이터: " + value);
            }
        }
    }

    public static String pollData(HttpClient httpClient, String url) throws Exception {
        ContentResponse response = httpClient.newRequest(url)
                .method("GET")
                .timeout(3, TimeUnit.SECONDS)
                .send();

        if (response.getStatus() != 200) {
            throw new IllegalStateException("데이터 폴링 실패: " + response.getStatus());
        }

        return response.getContentAsString().trim();
    }
}
