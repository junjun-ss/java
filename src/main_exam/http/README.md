# Main Exam HTTP

시험에서 HTTP 통신이 나오면 이 패키지를 먼저 봅니다.

사용 라이브러리:

- Server: Jetty 9 embedded server
- Client: Jetty 9 `HttpClient`
- JSON body: Google Gson `2.8.6`

| 파일 | 용도 |
| --- | --- |
| `EmbeddedJettyServer.java` | Jetty embedded server로 `/health`, `/echo` API 제공 |
| `JettyHttpClientExample.java` | Jetty `HttpClient`로 GET, JSON POST 요청 |
| `HttpJsonScenarioExample.java` | 서버 실행 후 client로 GET/POST까지 한 번에 확인 |
| `HttpMessage.java` | HTTP JSON body용 POJO |
