package exam._06_websocket_client;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@ClientEndpoint
public class WebSocketClient {
    private static CountDownLatch latch;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("연결이 열렸습니다.");
        // 연결이 열리면 메시지를 전송합니다.
        try {
            session.getBasicRemote().sendText("안녕하세요!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("받은 메시지: " + message);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("연결이 닫혔습니다. 이유: " + closeReason.getReasonPhrase());
        latch.countDown();
    }

    public static void main(String[] args) {
        latch = new CountDownLatch(1);

        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String uri = "ws://localhost:8080/websocket"; // WebSocket 서버 주소 입력

            System.out.println("연결 중...");
            container.connectToServer(WebSocketClient.class, URI.create(uri));

            latch.await(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
