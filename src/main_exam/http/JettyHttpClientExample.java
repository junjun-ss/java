package main_exam.http;

import com.google.gson.Gson;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.server.Server;

import java.util.concurrent.TimeUnit;

public class JettyHttpClientExample {
    private static final Gson GSON = new Gson();
    private static final long TIMEOUT_SECONDS = 3L;

    public static void main(String[] args) throws Exception {
        Server server = EmbeddedJettyServer.start(18084);
        HttpClient client = new HttpClient();
        client.start();

        try {
            System.out.println(get(client, "http://localhost:18084/health"));

            HttpMessage requestBody = new HttpMessage("client", "hello jetty");
            HttpMessage responseBody = postJson(client, "http://localhost:18084/echo", requestBody, HttpMessage.class);
            System.out.println(responseBody);
        } finally {
            client.stop();
            server.stop();
            server.join();
        }
    }

    public static String get(HttpClient client, String url) throws Exception {
        ContentResponse response = client.newRequest(url)
                .method("GET")
                .timeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .send();

        validate(response);
        return response.getContentAsString();
    }

    public static <T> T postJson(HttpClient client, String url, Object body, Class<T> responseType) throws Exception {
        String jsonBody = GSON.toJson(body);
        Request request = buildJsonPost(client, url, jsonBody);
        ContentResponse response = request.send();
        validate(response);
        return GSON.fromJson(response.getContentAsString(), responseType);
    }

    public static Request buildJsonPost(HttpClient client, String url, String jsonBody) {
        return client.newRequest(url)
                .method("POST")
                .timeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .header("Content-Type", "application/json;charset=UTF-8")
                .content(new StringContentProvider(jsonBody));
    }

    private static void validate(ContentResponse response) {
        if (response.getStatus() < 200 || response.getStatus() >= 300) {
            throw new IllegalStateException("HTTP 요청 실패: " + response.getStatus() + " " + response.getContentAsString());
        }
    }
}
