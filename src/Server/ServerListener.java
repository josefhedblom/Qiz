package Server;


import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    private static final int PORT = 12345;
    private final List<Socket> connectedPlayers = new ArrayList<>();
    private static final int MAX_PLAYERS = 2;

    public ServerListener() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servern väntar på spelare...");

            while (true) {
                Socket socket = serverSocket.accept();

                synchronized (connectedPlayers) { // synchronized får tråden att vänta in en annan tråd så att de startar samtidigt
                    if (connectedPlayers.size() < MAX_PLAYERS) {
                        connectedPlayers.add(socket);
                        System.out.println("Spelare Joina: " + socket.getInetAddress());

                        if (connectedPlayers.size() == MAX_PLAYERS) {
                            System.out.println("Spelet startar");
                            startGame();
                        }
                    } else { // för många som joina en server
                        System.out.println("Servern är full tyvär " + socket.getInetAddress());
                        socket.close();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void startGame() {
        for (Socket playerSocket : connectedPlayers) {
            new Server(playerSocket).start(); // Starta en Server-tråd för varje spelare yippi !!
        }
    }

    public static void main(String[] args) {
        new ServerListener();
    }
}