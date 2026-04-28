package exam._05_http_async_jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.google.gson.Gson;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncHttpServerExample extends AbstractHandler {
    private final Gson gson = new Gson();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if ("/id".equals(target) && "POST".equals(request.getMethod())) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);

            AsyncContext asyncContext = request.startAsync();
            asyncContext.start(() -> {
                try {
                    Person person = readPerson(request);

                    Thread.sleep(100);

                    person.setId(idCounter.getAndIncrement());

                    PrintWriter writer = response.getWriter();
                    writer.println(gson.toJson(person));
                    writer.flush();
                } catch (IOException | InterruptedException e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    Thread.currentThread().interrupt();
                } finally {
                    asyncContext.complete();
                }
            });
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        baseRequest.setHandled(true);
    }

    private Person readPerson(HttpServletRequest request) throws IOException {
        try (BufferedReader reader = request.getReader()) {
            return gson.fromJson(reader, Person.class);
        }
    }

    public static Server start(int port) throws Exception {
        QueuedThreadPool threadPool = new QueuedThreadPool(20, 2);
        Server server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);
        server.setHandler(new AsyncHttpServerExample());
        server.start();
        return server;
    }

    public static void main(String[] args) throws Exception {
        Server server = start(18083);
        System.out.println("Async Jetty server started: http://localhost:18083/id");
        server.join();
    }

    public static class Person {
        private String name;
        private int age;
        private int id;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
