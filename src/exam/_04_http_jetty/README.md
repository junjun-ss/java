# 04 HTTP Jetty

Jetty로 HTTP 서버를 띄우거나 Jetty client로 요청하는 문제가 나오면 이 폴더를 봅니다.

가장 중요한 시험용 HTTP + JSON 통신 시나리오는 `src/main_exam/http`에 따로 분리되어 있습니다.

| 파일 | 용도 |
| --- | --- |
| `HttpReceiver.java` | Jetty HTTP server |
| `HttpSender.java` | Jetty HTTP client GET/POST |
| `HttpJettyExample.java` | 서버를 띄운 뒤 client 요청을 보내는 실행 예제 |

가장 먼저 볼 파일: `HttpJettyExample.java`
