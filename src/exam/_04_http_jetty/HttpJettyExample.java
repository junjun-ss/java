package exam._04_http_jetty;

import exam._04_http_jetty.HttpReceiver.ServerHandler;
import org.eclipse.jetty.server.Server;

public class HttpJettyExample {
	
    public static void main(String[] args) throws Exception {
        Server server = HttpReceiver.startServer(18082, new ServerHandler("exam-server"));
        try {
            String getResponse = HttpSender.get("http://127.0.0.1:18082/queue?a=1&b=2");
            System.out.println("GET RES: " + getResponse.trim());

            String postResponse = HttpSender.postJson("http://127.0.0.1:18082/queue", "{\"message\":\"testPosting\"}");
            System.out.println("POST RES: " + postResponse.trim());
        } finally {
            server.stop();
            server.join();
        }
    }
}
