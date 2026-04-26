package exam._04_http_jetty;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpReceiver {

    static void startServer(int port, AbstractHandler handler) {
        Server server = new Server(port);
        server.setHandler(handler);

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ServerHandler extends AbstractHandler {
        private String serverName;

        public ServerHandler(String serverName) {
            this.serverName = serverName;
        }

        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {
            // HTTP 요청 처리
            response.setContentType("text/html;charset=utf-8");
//			"application/json"
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);


            // GET 방식일 떄 URL ? 뒤의 값들
            String queryString = request.getQueryString();  


            // POST 방식일 떄 들어온 데이터 처리
            StringBuilder requestBody = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
            String data = requestBody.toString();

            response.getWriter().println(queryString);
            response.getWriter().println(data);
        }
    }
}
