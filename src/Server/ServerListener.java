package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    public ServerListener() {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket1 = serverSocket.accept();
            Socket socket2 = serverSocket.accept();
            ServerLogic server = new ServerLogic(socket1, socket2);
            server.startGame();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServerListener();
    }
}
