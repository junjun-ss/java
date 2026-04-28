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
    private static String firstMessage = "안녕하세요!";

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("연결이 열렸습니다.");
        try {
            session.getBasicRemote().sendText(firstMessage);
        } catch (Exception e) {
            throw new IllegalStateException("WebSocket 메시지 전송 실패", e);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("받은 메시지: " + message);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("연결이 닫혔습니다. 이유: " + closeReason.getReasonPhrase());
        if (latch != null) {
            latch.countDown();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("사용법: java ... WebSocketClient ws://localhost:8080/websocket [message]");
            return;
        }

        String message = args.length >= 2 ? args[1] : "안녕하세요!";
        connect(args[0], message, 5);
    }

    public static void connect(String uri, String message, long waitSeconds) throws Exception {
        latch = new CountDownLatch(1);
        firstMessage = message;

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        System.out.println("연결 중...");
        container.connectToServer(WebSocketClient.class, URI.create(uri));
        latch.await(waitSeconds, TimeUnit.SECONDS);
    }
}
