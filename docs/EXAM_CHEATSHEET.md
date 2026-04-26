# Exam Cheatsheet

시험장에서 자주 가져다 쓰는 패턴만 짧게 모은 문서입니다. 자세한 전체 예제는 각 소스 파일을 보세요.

## File IO

텍스트 파일 라인 단위 읽기: `src/FileIO/fileInput.java`

```java
List<String> lines = new ArrayList<>();
try (BufferedReader br = new BufferedReader(new FileReader("./input.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        lines.add(line);
    }
}
```

텍스트 파일 쓰기:

```java
try (BufferedWriter bw = new BufferedWriter(new FileWriter("./output.txt"))) {
    for (String line : lines) {
        bw.write(line);
        bw.newLine();
    }
}
```

## Gson

문자열을 POJO로 변환: `src/DataCasting/dataCasting.java`

```java
Gson gson = new Gson();
Person person = gson.fromJson("{\"name\":\"John\",\"age\":30}", Person.class);
```

JSON 배열을 List로 변환:

```java
Type listType = new TypeToken<List<Person>>() {}.getType();
List<Person> people = gson.fromJson(jsonArrayString, listType);
```

파일 JSON 읽기: `src/FileIO/fileInput.java`

```java
try (BufferedReader reader = new BufferedReader(new FileReader("./person.json"))) {
    Person person = new Gson().fromJson(reader, Person.class);
}
```

## HTTP

기본 POST 요청: `src/MessageProducer.java`

```java
URL url = new URL("http://localhost:8080/queue");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("POST");
conn.setDoOutput(true);
conn.getOutputStream().write("Hello".getBytes(StandardCharsets.UTF_8));
int responseCode = conn.getResponseCode();
conn.disconnect();
```

기본 GET 요청: `src/MessageConsumer.java`

```java
URL url = new URL("http://localhost:8080/queue");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");
try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
    String body = in.lines().collect(Collectors.joining());
}
conn.disconnect();
```

Jetty server/client 예제는 `src/HttpMaker`와 `src/AsyncHttp` 아래에 있습니다.

## Thread / Async

Thread 직접 실행: `src/threadAsync/MyThread.java`

```java
Thread thread = new Thread(() -> System.out.println("work"));
thread.start();
thread.join();
```

CompletableFuture 병렬 실행: `src/MultiProcessThread/AsyncLogic.java`

```java
ExecutorService executor = Executors.newFixedThreadPool(2);
CompletableFuture<Void> task = CompletableFuture.runAsync(() -> {
    System.out.println("async work");
}, executor);
task.join();
executor.shutdown();
```

## ProcessBuilder

패키지가 있는 클래스를 실행할 때는 classpath와 전체 클래스명을 같이 넘깁니다.

```java
ProcessBuilder pb = new ProcessBuilder(
    "java",
    "-cp",
    "bin:libs/*",
    "threadAsync.MyThread"
);
Process process = pb.inheritIO().start();
int exitCode = process.waitFor();
```

Windows에서는 `bin:libs/*` 대신 `bin;libs/*`를 사용합니다.

## WebSocket

WebSocket 클라이언트 endpoint 예제는 `src/WebSocketClient.java`에 있습니다.

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
