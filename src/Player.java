import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Player {

    public Player() {
        int port = 12345;
        String serverIp = "127.0.0.1"; // byt detta till serverns ip eller kolla om det funkar med datorns egna ip
        try (Socket socket = new Socket(serverIp, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader playerIn = new BufferedReader(new InputStreamReader(System.in))) {
            String fromPlayer;
            String fromServer;

            fromServer = in.readLine();
            System.out.println(fromServer);

            while ((fromPlayer = playerIn.readLine()) != null) {
                out.println(fromPlayer);
                System.out.println("skrivet till server " + fromPlayer);
                fromServer = in.readLine();
                System.out.println(fromServer);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Main {
        public static void main(String[] args) throws IOException {
            Player k = new Player();
        }
    }
}