# 03 HTTP Jetty Client

HTTP client 통신 문제가 나오면 Jetty 9 `HttpClient`를 사용합니다.

서버까지 포함한 핵심 시나리오는 `src/main_exam/http`의 `HttpJsonScenarioExample.java`를 먼저 보세요.

| 파일 | 용도 |
| --- | --- |
| `MessageProducer.java` | Jetty `HttpClient` POST 예제 |
| `MessageConsumer.java` | Jetty `HttpClient` GET 예제 |
| `HttpPolling.java` | Jetty `HttpClient`로 주기적 polling |

가장 먼저 볼 파일: `MessageProducer.java`, `MessageConsumer.java`
