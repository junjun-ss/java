package main_exam.http;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.server.Server;

public class HttpJsonScenarioExample {
    public static void main(String[] args) throws Exception {
        Server server = EmbeddedJettyServer.start(18080);
        HttpClient client = new HttpClient();
        client.start();

        try {
            String health = JettyHttpClientExample.get(client, "http://localhost:18080/health");
            System.out.println("GET /health => " + health);

            HttpMessage request = new HttpMessage("exam-client", "json body test");
            HttpMessage response = JettyHttpClientExample.postJson(
                    client,
                    "http://localhost:18080/echo",
                    request,
                    HttpMessage.class
            );
            System.out.println("POST /echo => " + response);
        } finally {
            client.stop();
            server.stop();
            server.join();
        }
    }
}
