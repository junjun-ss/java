package exam._07_socket_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RealTimeStreamingServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(0);
        int port = serverSocket.getLocalPort();
        Thread serverThread = startServer(serverSocket, 1);

        sendMessage("127.0.0.1", port, "hello socket");
        serverThread.join();
    }

    public static Thread startServer(ServerSocket serverSocket, int maxClients) {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < maxClients; i++) {
                    Socket clientSocket = serverSocket.accept();
                    new ClientHandler(clientSocket).run();
                }
            } catch (IOException e) {
                throw new IllegalStateException("socket server 실패", e);
            } finally {
                closeQuietly(serverSocket);
            }
        });
        thread.start();
        return thread;
    }

    public static void startForever(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            } catch (IOException e) {
                closeQuietly(serverSocket);
                throw e;
            }
        }
    }

    public static void sendMessage(String host, int port, String message) throws IOException {
        try (Socket socket = new Socket(host, port);
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
            writer.println(message);
        }
    }

    private static void closeQuietly(ServerSocket serverSocket) {
        try {
            serverSocket.close();
        } catch (IOException ignored) {
        }
    }

    static class ClientHandler extends Thread {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (Socket socket = clientSocket;
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Received data from client: " + line);
                }
            } catch (IOException e) {
                throw new IllegalStateException("client 처리 실패", e);
            }
        }
    }
}
