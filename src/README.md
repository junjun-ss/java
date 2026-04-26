# Source Map

`src` 아래 예제는 시험 주제별로 느슨하게 나뉘어 있습니다.

## Core

| 폴더/파일 | 용도 |
| --- | --- |
| `FileIO/` | 텍스트 파일 읽기/쓰기, JSON 파일 읽기 |
| `DataCasting/` | Gson 기반 JSON 변환 |
| `UserInput/` | `Scanner` 사용자 입력 |
| `ecrypt/` | AES 암호화/복호화 예제 |
| `AIModel/` | AI 모델 사용 흐름 메모성 skeleton |

## Network

| 폴더/파일 | 용도 |
| --- | --- |
| `HttpMaker/` | Jetty HTTP server/client |
| `AsyncHttp/` | Jetty async HTTP server와 다중 요청 client |
| `HttpPolling.java` | HTTP polling client |
| `MessageProducer.java` | HTTP POST producer |
| `MessageConsumer.java` | HTTP GET consumer |
| `WebSocketClient.java` | WebSocket client endpoint |
| `RealTime/` | TCP `ServerSocket` streaming server |

## Concurrency / Process

| 폴더/파일 | 용도 |
| --- | --- |
| `MessageQueue/` | `wait/notifyAll` 기반 in-memory queue |
| `threadAsync/` | Thread, ProcessBuilder, Pipe 예제 |
| `MultiProcessThread/` | Thread, ProcessBuilder, CompletableFuture 예제 |
| `multiProcessKill/` | 자식 프로세스 재시작 흐름 |

## 추가할 때 규칙

- 새 예제는 주제 폴더 아래에 넣고, public class 이름과 파일명을 맞춥니다.
- 외부 라이브러리가 필요하면 README에 의존성을 적습니다.
- 시험장에서 복붙할 수 있도록 한 예제는 가능한 작게 유지합니다.
