package exam._05_http_async_jetty;

import com.google.gson.Gson;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.server.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AsyncClientExample {
    private static final int NUM_REQUESTS = 5;
    private static final String URL = "http://localhost:18083/id";

    public static void main(String[] args) throws Exception {
        Server server = AsyncHttpServerExample.start(18083);
        try {
            List<String> responses = sendParallelRequests(URL, NUM_REQUESTS);
            for (String response : responses) {
                System.out.println("Received response: " + response);
            }
        } finally {
            server.stop();
            server.join();
        }
    }

    public static List<String> sendParallelRequests(String url, int requestCount) throws Exception {
        HttpClient httpClient = new HttpClient();
        ExecutorService executorService = Executors.newFixedThreadPool(requestCount);
        List<Future<String>> futures = new ArrayList<>();
        List<String> responses = new ArrayList<>();

        try {
            httpClient.start();

            for (int i = 0; i < requestCount; i++) {
                final int index = i;
                Future<String> future = executorService.submit(() -> sendRequest(httpClient, url, "user-" + index, 20 + index));
                futures.add(future);
            }

            for (Future<String> future : futures) {
                responses.add(future.get());
            }
            return responses;
        } finally {
            executorService.shutdown();
            httpClient.stop();
        }
    }

    private static String sendRequest(HttpClient httpClient, String url, String name, int age) throws Exception {
        Gson gson = new Gson();
        String payload = gson.toJson(new AsyncHttpServerExample.Person(name, age));

        ContentResponse response = httpClient.newRequest(url)
                .method("POST")
                .timeout(5, TimeUnit.SECONDS)
                .header("Content-Type", "application/json;charset=UTF-8")
                .content(new StringContentProvider(payload))
                .send();

        if (response.getStatus() == 200) {
            return response.getContentAsString();
        }

        throw new IllegalStateException("Request failed with response code: " + response.getStatus());
    }
}
