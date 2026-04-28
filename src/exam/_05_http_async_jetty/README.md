# 05 Async HTTP Jetty

비동기 HTTP 처리나 동시 요청 문제가 나오면 이 폴더를 봅니다.

HTTP 통신은 Jetty 9 embedded server와 Jetty 9 `HttpClient` 기준으로 작성합니다.

| 파일 | 용도 |
| --- | --- |
| `AsyncHttpServerExample.java` | Jetty async request 처리 |
| `AsyncClientExample.java` | 여러 요청을 동시에 보내는 client |

실행 순서: 서버 `AsyncHttpServerExample.java` 실행 후 client `AsyncClientExample.java` 실행
