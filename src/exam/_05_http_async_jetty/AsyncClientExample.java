package exam._05_http_async_jetty;

import com.google.gson.Gson;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AsyncClientExample {
    private static final int NUM_REQUESTS = 100;

    public static void main(String[] args) throws Exception {
        HttpClient httpClient = new HttpClient();
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_REQUESTS);
        List<Future<String>> futures = new ArrayList<>();

        try {
            httpClient.start();

            for (int i = 0; i < NUM_REQUESTS; i++) {
                Future<String> future = executorService.submit(() -> sendRequest(httpClient));
                futures.add(future);
            }

            for (Future<String> future : futures) {
                String response = future.get();
                System.out.println("Received response: " + response);
            }
        } finally {
            executorService.shutdown();
            httpClient.stop();
        }
    }

    private static String sendRequest(HttpClient httpClient) throws Exception {
        Gson gson = new Gson();
        String payload = gson.toJson(new Person("John Doe", 30));

        ContentResponse response = httpClient.newRequest("http://localhost:8080/id")
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

    private static class Person {
        private String name;
        private int age;

        // Getter and Setter methods

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

		public Person(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

    }
}
