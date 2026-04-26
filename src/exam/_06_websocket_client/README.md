# 06 WebSocket Client

WebSocket client endpoint 문제가 나오면 이 폴더를 봅니다.

| 파일 | 용도 |
| --- | --- |
| `WebSocketClient.java` | `@ClientEndpoint`, `@OnOpen`, `@OnMessage`, `@OnClose` 예제 |

주의: `javax.websocket-api`는 API이고, 실행 시에는 Tyrus 같은 구현체가 필요합니다.
