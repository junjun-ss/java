# Java Exam Examples

회사 Java 시험에서 자주 나오는 유형을 폴더명만 보고 바로 찾아 쓰기 위한 예제 모음입니다.
문제가 나오면 아래 표에서 키워드를 찾고, 해당 폴더로 들어가 필요한 `.java` 파일을 복사해서 쓰면 됩니다.

기준 환경:

- Java 8
- HTTP: Jetty 9 embedded server + Jetty 9 `HttpClient`
- JSON: Google Gson `2.8.6`

## 문제 나오면 여기로

### 핵심 시험 패키지

| 시험 문제 키워드 | 들어갈 폴더 | 대표 파일 |
| --- | --- | --- |
| HTTP 서버/클라이언트, JSON body 통신 | `src/main_exam/http` | `HttpJsonScenarioExample.java` |
| JSON 파일 읽기, Gson 2.8.6 파싱 | `src/main_exam/json` | `JsonFileReadExample.java` |

### 유형별 참고 예제

| 시험 문제 키워드 | 들어갈 폴더 | 대표 파일 |
| --- | --- | --- |
| 빠른 입력, 빠른 출력, `StringTokenizer` | `src/exam/_00_fast_io` | `FastIOExample.java` |
| 파일 읽기, 파일 쓰기, 라인 파싱 | `src/exam/_01_file_read_write` | `FileReadWriteExample.java` |
| 파일 변경 감지, polling | `src/exam/_01_file_read_write` | `FileMonitorExample.java` |
| JSON 파일 읽기, Gson, POJO 변환 | `src/exam/_02_json_gson` | `GsonCastingExample.java` |
| Jetty 9 HttpClient GET, POST, polling | `src/exam/_03_http_jetty_client` | `MessageProducer.java`, `MessageConsumer.java` |
| Jetty HTTP 서버/클라이언트 | `src/exam/_04_http_jetty` | `HttpJettyExample.java` |
| 비동기 HTTP 서버, 동시 요청 | `src/exam/_05_http_async_jetty` | `AsyncHttpServerExample.java` |
| WebSocket 클라이언트 | `src/exam/_06_websocket_client` | `WebSocketClient.java` |
| TCP socket server, 실시간 수신 | `src/exam/_07_socket_server` | `RealTimeStreamingServer.java` |
| 메시지 큐, `wait`, `notifyAll` | `src/exam/_08_message_queue` | `MessageQueueExample.java` |
| Thread, CompletableFuture, pipe | `src/exam/_09_thread_async` | `CompletableFutureExample.java` |
| ProcessBuilder, 자식 프로세스 실행/재시작 | `src/exam/_10_process_builder` | `ProcessExample.java` |
| AES 암호화/복호화 | `src/exam/_11_encryption_aes` | `AESEncryptionExample.java` |
| Scanner 사용자 입력 | `src/exam/_12_user_input` | `UserInputExample.java` |
| List, Map, Set, Queue, Heap | `src/exam/_13_collections` | `CollectionsExample.java` |
| 정렬, 다중 조건 정렬, Comparator | `src/exam/_14_sort_comparator` | `SortComparatorExample.java` |
| 문자열 파싱, `charAt`, 숫자 변환 | `src/exam/_15_string_parsing` | `StringParsingExample.java` |
| BFS, DFS, 격자 탐색 | `src/exam/_16_bfs_dfs` | `BfsDfsExample.java` |
| 이분탐색, lower bound, parametric search | `src/exam/_17_binary_search` | `BinarySearchExample.java` |
| 투포인터, 슬라이딩 윈도우 | `src/exam/_18_two_pointer_sliding_window` | `TwoPointerSlidingWindowExample.java` |
| 누적합, 2차원 구간합 | `src/exam/_19_prefix_sum` | `PrefixSumExample.java` |
| Union-Find, 연결 여부, 사이클 | `src/exam/_20_union_find` | `UnionFindExample.java` |
| 다익스트라, 최단거리 | `src/exam/_21_dijkstra` | `DijkstraExample.java` |
| DP, 점화식, LCS | `src/exam/_22_dynamic_programming` | `DynamicProgrammingExample.java` |
| 백트래킹, 순열, 조합 | `src/exam/_23_backtracking` | `BacktrackingExample.java` |
| 날짜 파싱, 날짜/시간 차이 | `src/exam/_24_date_time` | `DateTimeExample.java` |
| AI 모델 skeleton | `src/exam/_90_ai_model_stub` | `Predictor.java` |

## 추천 사용 흐름

1. 문제에서 핵심 키워드를 찾습니다. 예: `json 파일 읽기`, `websocket`, `thread`.
2. 위 표의 폴더로 들어갑니다.
3. 폴더 안 `README.md`를 먼저 보고, 대표 `.java` 파일을 복사합니다.
4. 필요한 메서드만 시험 코드에 붙여 넣고 파일 경로, URL, port만 바꿉니다.

## 빌드/실행

Maven이 있으면:

```bash
mvn compile
```

Maven 없이 `libs/`의 jar를 직접 쓰면:

```bash
find src -name "*.java" ! -path "*/_06_websocket_client/*" -print | xargs javac -cp "libs/*" -d bin
```

단, `libs/`에는 WebSocket API/구현체 jar가 없으므로 WebSocket 예제까지 컴파일하려면 Maven을 쓰거나 관련 jar를 추가해야 합니다.

예제 실행:

```bash
java -cp "bin:libs/*" exam._01_file_read_write.FileReadWriteExample
java -cp "bin:libs/*" main_exam.json.JsonFileReadExample
java -cp "bin:libs/*" main_exam.http.HttpJsonScenarioExample
java -cp "bin:libs/*" exam._16_bfs_dfs.BfsDfsExample
```

Windows에서는 classpath 구분자를 `:` 대신 `;`로 바꿔주세요.

## 보조 문서

- [docs/EXAM_CHEATSHEET.md](docs/EXAM_CHEATSHEET.md): 시험장에서 복붙하기 좋은 짧은 코드 조각
- [src/main_exam/http](src/main_exam/http): 핵심 HTTP 통신 예제
- [src/main_exam/json](src/main_exam/json): 핵심 JSON/Gson 예제
- [src/exam/README.md](src/exam/README.md): 전체 예제 폴더맵
