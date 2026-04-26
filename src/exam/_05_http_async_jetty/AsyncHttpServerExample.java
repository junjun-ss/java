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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncHttpServerExample extends AbstractHandler {
    private final Gson gson = new Gson();
    private final AtomicInteger idCounter = new AtomicInteger(0);
    private final ThreadLocal<AtomicInteger> independentIdCounter = ThreadLocal.withInitial(AtomicInteger::new);

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if ("/id".equals(target) && "POST".equals(request.getMethod())) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);

            AsyncContext asyncContext = request.startAsync();
            asyncContext.start(() -> {
                try {
                    BufferedReader reader = request.getReader();
                    Person person = gson.fromJson(reader, Person.class);
                    System.out.println(person.getAge());
                    reader.close();

                    // Simulate heavy processing by sleeping for 2 seconds
                    Thread.sleep(3000);

                    person.setId(idCounter.getAndIncrement());
                    person.setIndependentId(independentIdCounter.get().getAndIncrement());

                    PrintWriter writer = response.getWriter();
                    writer.println(gson.toJson(person));
                    writer.flush();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    asyncContext.complete();
                }
            });
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        baseRequest.setHandled(true);
    }

    public static void main(String[] args) throws Exception {
    	QueuedThreadPool threadPool = new QueuedThreadPool(100, 10);
    	Server server = new Server(threadPool);
    	ServerConnector connector = new ServerConnector(server);
    	connector.setPort(8080);
    	// 서버에 ServerConnector를 추가합니다.
    	server.addConnector(connector);
    	
        server.setHandler(new AsyncHttpServerExample());
        server.start();
        server.join();
    }

    private static class Person {
        private String name;
        private int age;
        private int id;
        private int independentId;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIndependentId() {
            return independentId;
        }

        public void setIndependentId(int independentId) {
            this.independentId = independentId;
        }
    }
}
