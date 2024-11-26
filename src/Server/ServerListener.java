package Server;

import Protocol.Protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    public ServerListener() {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket1 = serverSocket.accept();
            Socket socket2 = serverSocket.accept();
            QuizGame server = new QuizGame(socket1, socket2);
            server.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new ServerListener();
    }
}