# Java Exam Examples

회사 Java 시험에서 자주 나오는 유형을 폴더명만 보고 바로 찾아 쓰기 위한 예제 모음입니다.
문제가 나오면 아래 표에서 키워드를 찾고, 해당 폴더로 들어가 필요한 `.java` 파일을 복사해서 쓰면 됩니다.

## 문제 나오면 여기로

| 시험 문제 키워드 | 들어갈 폴더 | 대표 파일 |
| --- | --- | --- |
| 파일 읽기, 파일 쓰기, 라인 파싱 | `src/exam/_01_file_read_write` | `FileReadWriteExample.java` |
| 파일 변경 감지, polling | `src/exam/_01_file_read_write` | `FileMonitorExample.java` |
| JSON 파일 읽기, Gson, POJO 변환 | `src/exam/_02_json_gson` | `GsonCastingExample.java` |
| `HttpURLConnection`, GET, POST, polling | `src/exam/_03_http_urlconnection` | `MessageProducer.java`, `MessageConsumer.java` |
| Jetty HTTP 서버/클라이언트 | `src/exam/_04_http_jetty` | `HttpJettyExample.java` |
| 비동기 HTTP 서버, 동시 요청 | `src/exam/_05_http_async_jetty` | `AsyncHttpServerExample.java` |
| WebSocket 클라이언트 | `src/exam/_06_websocket_client` | `WebSocketClient.java` |
| TCP socket server, 실시간 수신 | `src/exam/_07_socket_server` | `RealTimeStreamingServer.java` |
| 메시지 큐, `wait`, `notifyAll` | `src/exam/_08_message_queue` | `MessageQueueExample.java` |
| Thread, CompletableFuture, pipe | `src/exam/_09_thread_async` | `CompletableFutureExample.java` |
| ProcessBuilder, 자식 프로세스 실행/재시작 | `src/exam/_10_process_builder` | `ProcessExample.java` |
| AES 암호화/복호화 | `src/exam/_11_encryption_aes` | `AESEncryptionExample.java` |
| Scanner 사용자 입력 | `src/exam/_12_user_input` | `UserInputExample.java` |
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
find src -name "*.java" -print | xargs javac -cp "libs/*" -d bin
```

단, WebSocket 예제는 `javax.websocket` 구현체가 필요해서 Maven 사용을 추천합니다.

예제 실행:

```bash
java -cp "bin:libs/*" exam._01_file_read_write.FileReadWriteExample
java -cp "bin:libs/*" exam._02_json_gson.GsonCastingExample
java -cp "bin:libs/*" exam._04_http_jetty.HttpJettyExample
```

Windows에서는 classpath 구분자를 `:` 대신 `;`로 바꿔주세요.

## 보조 문서

- [docs/EXAM_CHEATSHEET.md](docs/EXAM_CHEATSHEET.md): 시험장에서 복붙하기 좋은 짧은 코드 조각
- [src/exam/README.md](src/exam/README.md): 전체 예제 폴더맵
