package main_exam.http;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class EmbeddedJettyServer {
    private static final Gson GSON = new Gson();

    public static Server start(int port) throws Exception {
        Server server = new Server(port);
        server.setHandler(new ApiHandler());
        server.start();
        return server;
    }

    public static void main(String[] args) throws Exception {
        Server server = start(8080);
        System.out.println("Jetty embedded server started: http://localhost:8080");
        server.join();
    }

    static class ApiHandler extends AbstractHandler {
        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {
            response.setCharacterEncoding("UTF-8");

            if ("/health".equals(target) && "GET".equals(request.getMethod())) {
                writeJson(response, HttpServletResponse.SC_OK, "{\"status\":\"ok\"}");
                baseRequest.setHandled(true);
                return;
            }

            if ("/echo".equals(target) && "POST".equals(request.getMethod())) {
                String body = readBody(request);
                HttpMessage message = GSON.fromJson(body, HttpMessage.class);
                if (message == null) {
                    writeJson(response, HttpServletResponse.SC_BAD_REQUEST, "{\"error\":\"empty json body\"}");
                    baseRequest.setHandled(true);
                    return;
                }

                message.setReceivedAt(System.currentTimeMillis());
                writeJson(response, HttpServletResponse.SC_OK, GSON.toJson(message));
                baseRequest.setHandled(true);
                return;
            }

            writeJson(response, HttpServletResponse.SC_NOT_FOUND, "{\"error\":\"not found\"}");
            baseRequest.setHandled(true);
        }

        private String readBody(HttpServletRequest request) throws IOException {
            StringBuilder body = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    body.append(line);
                }
            }
            return body.toString();
        }

        private void writeJson(HttpServletResponse response, int status, String json) throws IOException {
            response.setStatus(status);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
        }
    }
}
