# Java Exam Examples

회사 Java 시험에서 자주 나오는 기능을 빠르게 찾아 복붙하기 위한 예제 모음입니다.
완성 서비스가 아니라, 파일 IO, JSON/Gson, HTTP, WebSocket, Thread, Process 같은 작은 예제를 주제별로 모아둔 저장소입니다.

## 빠른 사용법

1. 시험 주제에 맞는 예제를 아래 표나 [docs/EXAM_CHEATSHEET.md](docs/EXAM_CHEATSHEET.md)에서 찾습니다.
2. 필요한 클래스 또는 메서드만 시험 코드로 가져갑니다.
3. Gson/Jetty/WebSocket처럼 외부 라이브러리가 필요한 예제는 `libs/` 또는 `pom.xml` 의존성을 같이 확인합니다.

## 예제 인덱스

| 주제 | 파일 | 내용 |
| --- | --- | --- |
| 파일 읽기/쓰기 | `src/FileIO/fileInput.java` | 텍스트 파일 읽기, 라인 파싱, 파일 쓰기 |
| JSON 파일 읽기 | `src/FileIO/fileInput.java` | `person.json`을 Gson으로 POJO 변환 |
| 문자열/JSON 변환 | `src/DataCasting/dataCasting.java` | String, JsonObject, POJO, List 변환 |
| HTTP 클라이언트 | `src/HttpMaker/HttpSender.java` | Jetty `HttpClient`로 GET/POST 요청 |
| HTTP 서버 | `src/HttpMaker/HttpReceiver.java` | Jetty 서버에서 query/body 받기 |
| 비동기 HTTP 서버 | `src/AsyncHttp/AsyncHttpServerExample.java` | Jetty async request 처리 |
| HTTP polling | `src/HttpPolling.java` | 주기적으로 URL polling |
| 메시지 큐 | `src/MessageQueue/MessageQueue.java` | `wait/notifyAll` 기반 간단 큐 |
| HTTP producer/consumer | `src/MessageProducer.java`, `src/MessageConsumer.java` | `HttpURLConnection` GET/POST 예제 |
| WebSocket client | `src/WebSocketClient.java` | `javax.websocket` client endpoint |
| TCP streaming server | `src/RealTime/RealTimeStreamingServer.java` | `ServerSocket` 기반 실시간 수신 |
| Thread | `src/threadAsync/MyThread.java` | 기본 Thread 실행 |
| CompletableFuture | `src/MultiProcessThread/AsyncLogic.java` | `CompletableFuture.runAsync` 예제 |
| ProcessBuilder | `src/threadAsync/MyProcess.java` | Java 프로세스 실행 예제 |
| AES 암호화 | `src/ecrypt/AESEncryptionExample.java` | AES encrypt/decrypt 기본 흐름 |
| 사용자 입력 | `src/UserInput/userInput.java` | `Scanner` 입력 예제 |

더 자세한 카테고리는 [src/README.md](src/README.md)를 참고하세요.

## 빌드/실행

이 저장소는 Eclipse 프로젝트로도 열 수 있고, Maven으로도 의존성을 받을 수 있게 `pom.xml`을 추가해두었습니다.

```bash
# Maven이 있는 경우
mvn compile

# Maven 없이 libs 폴더의 jar를 직접 쓰는 경우
javac -cp "libs/*" -d bin src/**/*.java src/*.java
```

패키지가 있는 예제는 실행할 때 패키지명을 포함해야 합니다.

```bash
java -cp "bin:libs/*" FileIO.fileInput
java -cp "bin:libs/*" DataCasting.dataCasting
java -cp "bin:libs/*" AsyncHttp.AsyncHttpServerExample
```

Windows에서는 classpath 구분자를 `:` 대신 `;`로 바꿔주세요.

## 정리 원칙

- 시험장에서 바로 가져갈 수 있도록 예제는 작게 유지합니다.
- 한 파일은 한 주제를 보여주는 데 집중합니다.
- 외부 의존성이 필요한 예제는 README에 표시합니다.
- 새 예제를 추가할 때는 `src/README.md`와 이 README 인덱스도 같이 갱신합니다.
