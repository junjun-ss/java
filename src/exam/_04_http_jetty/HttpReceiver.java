package exam._04_http_jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpReceiver {

    public static Server startServer(int port, AbstractHandler handler) throws Exception {
        Server server = new Server(port);
        server.setHandler(handler);
        server.start();
        return server;
    }

    public static String readBody(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }
        return requestBody.toString();
    }

    static class ServerHandler extends AbstractHandler {
        private final String serverName;

        public ServerHandler(String serverName) {
            this.serverName = serverName;
        }

        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);

            String queryString = request.getQueryString();  
            String body = readBody(request);

            response.getWriter().println("{"
                    + "\"server\":\"" + serverName + "\","
                    + "\"method\":\"" + request.getMethod() + "\","
                    + "\"query\":\"" + (queryString == null ? "" : queryString) + "\","
                    + "\"body\":\"" + body.replace("\"", "\\\"") + "\""
                    + "}");
        }
    }
}
