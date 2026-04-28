package exam._03_http_jetty_client;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class LocalQueueServer {
    private LocalQueueServer() {
    }

    public static Server start(int port) throws Exception {
        Server server = new Server(port);
        server.setHandler(new QueueHandler());
        server.start();
        return server;
    }

    static class QueueHandler extends AbstractHandler {
        private final Queue<String> messages = new ConcurrentLinkedQueue<>();
        private final AtomicInteger dataSequence = new AtomicInteger(1);

        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {
            response.setCharacterEncoding("UTF-8");

            if ("/queue".equals(target) && "POST".equals(request.getMethod())) {
                String body = readBody(request);
                messages.offer(body);
                write(response, HttpServletResponse.SC_OK, "stored:" + body);
                baseRequest.setHandled(true);
                return;
            }

            if ("/queue".equals(target) && "GET".equals(request.getMethod())) {
                String message = messages.poll();
                write(response, HttpServletResponse.SC_OK, message == null ? "" : message);
                baseRequest.setHandled(true);
                return;
            }

            if ("/data".equals(target) && "GET".equals(request.getMethod())) {
                write(response, HttpServletResponse.SC_OK, "data-" + dataSequence.getAndIncrement());
                baseRequest.setHandled(true);
                return;
            }

            write(response, HttpServletResponse.SC_NOT_FOUND, "not found");
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

        private void write(HttpServletResponse response, int status, String body) throws IOException {
            response.setStatus(status);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().println(body);
        }
    }
}
