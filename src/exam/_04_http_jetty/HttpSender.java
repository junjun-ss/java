package exam._04_http_jetty;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;

import java.util.concurrent.TimeUnit;

public class HttpSender {
    private static final long TIMEOUT_SECONDS = 3L;

    public static String get(String targetUrl) throws Exception {
        HttpClient httpClient = new HttpClient();
        try {
            httpClient.start();
            ContentResponse response = get(httpClient, targetUrl);
            validate(response);
            return response.getContentAsString();
        } finally {
            httpClient.stop();
        }
    }
	
    public static String postJson(String targetUrl, String bodyData) throws Exception {
        HttpClient httpClient = new HttpClient();
        try {
            httpClient.start();
            ContentResponse response = postJson(httpClient, targetUrl, bodyData);
            validate(response);
            return response.getContentAsString();
        } finally {
            httpClient.stop();
        }
    }

    public static ContentResponse get(HttpClient httpClient, String targetUrl) throws Exception {
        Request request = httpClient.newRequest(targetUrl)
                .method("GET")
                .timeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .header("x-requestId", "test");
        return request.send();
    }

    public static ContentResponse postJson(HttpClient httpClient, String targetUrl, String bodyData) throws Exception {
        Request request = httpClient.newRequest(targetUrl)
                .method("POST")
                .timeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .header("Content-Type", "application/json;charset=UTF-8")
                .content(new StringContentProvider(bodyData));
        return request.send();
    }

    private static void validate(ContentResponse response) {
        if (response.getStatus() < 200 || response.getStatus() >= 300) {
            throw new IllegalStateException("HTTP 요청 실패: " + response.getStatus());
        }
    }
}
