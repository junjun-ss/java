# Exam Cheatsheet

시험장에서 바로 복붙하기 좋은 짧은 패턴만 모았습니다.
전체 실행 예제는 `src/exam/_번호_문제유형` 폴더 안의 `.java` 파일을 보면 됩니다.

## File IO

폴더: `src/exam/_01_file_read_write`

텍스트 파일 라인 단위 읽기:

```java
List<String> lines = new ArrayList<>();
try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        lines.add(line);
    }
}
```

텍스트 파일 쓰기:

```java
try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
    for (String line : lines) {
        bw.write(line);
        bw.newLine();
    }
}
```

## JSON / Gson

폴더: `src/exam/_02_json_gson`

문자열을 POJO로 변환:

```java
Gson gson = new Gson();
Person person = gson.fromJson("{\"name\":\"John\",\"age\":30}", Person.class);
```

JSON 배열을 List로 변환:

```java
Type listType = new TypeToken<List<Person>>() {}.getType();
List<Person> people = gson.fromJson(jsonArrayString, listType);
```

JSON 파일 읽기:

```java
try (BufferedReader reader = new BufferedReader(new FileReader("person.json"))) {
    Person person = new Gson().fromJson(reader, Person.class);
}
```

## HTTP URLConnection

폴더: `src/exam/_03_http_urlconnection`

기본 POST 요청:

```java
URL url = new URL("http://localhost:8080/queue");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("POST");
conn.setDoOutput(true);
conn.getOutputStream().write("Hello".getBytes(StandardCharsets.UTF_8));
int responseCode = conn.getResponseCode();
conn.disconnect();
```

기본 GET 요청:

```java
URL url = new URL("http://localhost:8080/queue");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");
try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
    String body = in.lines().collect(Collectors.joining());
}
conn.disconnect();
```

## Jetty HTTP

폴더:

```text
src/exam/_04_http_jetty
src/exam/_05_http_async_jetty
```

서버 handler 핵심:

```java
public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_OK);
    baseRequest.setHandled(true);
    response.getWriter().println(request.getQueryString());
}
```

## Thread / Async

폴더: `src/exam/_09_thread_async`

Thread 직접 실행:

```java
Thread thread = new Thread(() -> System.out.println("work"));
thread.start();
thread.join();
```

CompletableFuture 병렬 실행:

```java
ExecutorService executor = Executors.newFixedThreadPool(2);
CompletableFuture<Void> task = CompletableFuture.runAsync(() -> {
    System.out.println("async work");
}, executor);
task.join();
executor.shutdown();
```

## ProcessBuilder

폴더: `src/exam/_10_process_builder`

패키지가 있는 클래스를 실행할 때는 classpath와 전체 클래스명을 같이 넘깁니다.

```java
ProcessBuilder pb = new ProcessBuilder(
    "java",
    "-cp",
    System.getProperty("java.class.path"),
    "exam._09_thread_async.MyThread"
);
Process process = pb.inheritIO().start();
int exitCode = process.waitFor();
```

## WebSocket

폴더: `src/exam/_06_websocket_client`

```java
@ClientEndpoint
public class WebSocketClient {
    @OnOpen
    public void onOpen(Session session) throws IOException {
        session.getBasicRemote().sendText("hello");
    }
}
```

`javax.websocket-api`는 컴파일용 API이고, 실행 시에는 Tyrus 같은 구현체가 필요합니다. Maven을 쓰면 `pom.xml` 의존성을 참고하세요.
