package exam._05_http_async_jetty;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AsyncClientExample {
    private static final int NUM_REQUESTS = 100;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_REQUESTS);
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < NUM_REQUESTS; i++) {
            Future<String> future = executorService.submit(() -> sendRequest());
            futures.add(future);
        }

        for (Future<String> future : futures) {
            String response = future.get();
            System.out.println("Received response: " + response);
        }

        executorService.shutdown();
    }

    private static String sendRequest() throws IOException {
        URL url = new URL("http://localhost:8080/id");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Create a sample JSON payload
        Gson gson = new Gson();
        String payload = gson.toJson(new Person("John Doe", 30));

        try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
            writer.write(payload);
            writer.flush();
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } else {
            throw new IOException("Request failed with response code: " + responseCode);
        }
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
