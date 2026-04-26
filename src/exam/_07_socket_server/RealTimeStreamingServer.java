package exam._07_socket_server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RealTimeStreamingServer {
    public static void main(String[] args) {
        int port = 8000; // 포트 번호

        try {
            // 서버 소켓 생성
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                // 클라이언트 접속 대기
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // 클라이언트와의 통신을 위한 스레드 생성 및 시작
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                // 클라이언트로부터 데이터를 읽기 위한 입력 스트림 생성
                InputStream inputStream = clientSocket.getInputStream();

                // 데이터를 읽어서 처리
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    // 읽은 데이터 처리 (예: 화면에 출력)
                    String data = new String(buffer, 0, bytesRead);
                    System.out.println("Received data from client: " + data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 클라이언트와의 연결 종료
                try {
                    clientSocket.close();
                    System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
