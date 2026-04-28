# Exam Cheatsheet

시험장에서 바로 복붙하기 좋은 짧은 패턴만 모았습니다.
전체 실행 예제는 `src/exam/_번호_문제유형` 폴더 안의 `.java` 파일을 보면 됩니다.
파일/HTTP/JSON 핵심 예제는 `src/main_exam/file`, `src/main_exam/http`, `src/main_exam/json`을 먼저 보세요.

## Fast IO

폴더: `src/exam/_00_fast_io`

```java
static class FastScanner {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;

    String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
```

## File IO

폴더: `src/main_exam/file`

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

라인별로 바로 처리:

```java
try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        String[] columns = line.trim().split("\\s+");
        // columns를 바로 처리
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

폴더: `src/main_exam/json`

사용 라이브러리: Google Gson `2.8.6`

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

## HTTP Jetty 9

폴더: `src/main_exam/http`

HTTP 통신은 Jetty 9 embedded server와 Jetty 9 `HttpClient` 기준으로 작성합니다.

Jetty embedded server 시작:

```java
Server server = new Server(8080);
server.setHandler(new AbstractHandler() {
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println("{\"status\":\"ok\"}");
        baseRequest.setHandled(true);
    }
});
server.start();
```

Jetty HttpClient GET:

```java
HttpClient client = new HttpClient();
client.start();
ContentResponse response = client.newRequest("http://localhost:8080/health")
        .method("GET")
        .timeout(3, TimeUnit.SECONDS)
        .send();
String body = response.getContentAsString();
client.stop();
```

Jetty HttpClient JSON POST + Gson:

```java
Gson gson = new Gson();
String jsonBody = gson.toJson(requestObject);

ContentResponse response = client.newRequest("http://localhost:8080/echo")
        .method("POST")
        .header("Content-Type", "application/json;charset=UTF-8")
        .content(new StringContentProvider(jsonBody))
        .timeout(3, TimeUnit.SECONDS)
        .send();

ResponseObject result = gson.fromJson(response.getContentAsString(), ResponseObject.class);
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

## Collections

폴더: `src/exam/_13_collections`

```java
Map<String, Integer> countMap = new HashMap<>();
countMap.put(word, countMap.getOrDefault(word, 0) + 1);

PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

Deque<Integer> deque = new ArrayDeque<>();
deque.addFirst(1);
deque.addLast(2);
```

## Sort / Comparator

폴더: `src/exam/_14_sort_comparator`

```java
Arrays.sort(points, new Comparator<int[]>() {
    @Override
    public int compare(int[] a, int[] b) {
        if (a[0] != b[0]) {
            return Integer.compare(a[0], b[0]);
        }
        return Integer.compare(a[1], b[1]);
    }
});
```

## BFS / DFS

폴더: `src/exam/_16_bfs_dfs`

```java
Queue<Integer> queue = new ArrayDeque<>();
queue.offer(start);
visited[start] = true;

while (!queue.isEmpty()) {
    int node = queue.poll();
    for (int next : graph[node]) {
        if (!visited[next]) {
            visited[next] = true;
            queue.offer(next);
        }
    }
}
```

## Binary Search

폴더: `src/exam/_17_binary_search`

```java
int left = 0;
int right = arr.length;
while (left < right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] >= target) {
        right = mid;
    } else {
        left = mid + 1;
    }
}
```

## Prefix Sum

폴더: `src/exam/_19_prefix_sum`

```java
int[] prefix = new int[n + 1];
for (int i = 1; i <= n; i++) {
    prefix[i] = prefix[i - 1] + arr[i];
}
int sum = prefix[right] - prefix[left - 1];
```

## Union Find

폴더: `src/exam/_20_union_find`

```java
int find(int x) {
    if (parent[x] != x) {
        parent[x] = find(parent[x]);
    }
    return parent[x];
}
```

## Dijkstra

폴더: `src/exam/_21_dijkstra`

```java
PriorityQueue<Node> pq = new PriorityQueue<>();
dist[start] = 0;
pq.offer(new Node(start, 0));

while (!pq.isEmpty()) {
    Node current = pq.poll();
    if (current.cost > dist[current.vertex]) {
        continue;
    }
}
```
