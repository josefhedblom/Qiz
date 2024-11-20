package Server;

import Protocol.Protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    public ServerListener(){
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                Socket socket = serverSocket.accept();
                Server ser = new Server(socket);
                ser.start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        ServerListener sl = new ServerListener();
    }
}