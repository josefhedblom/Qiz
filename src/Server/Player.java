package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Player {

    public Player() {

        // byt detta till serverns ip eller kolla om det funkar med datorns egna ip
        try (Socket socket = new Socket("127.0.0.1", 12345);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader playerIn = new BufferedReader(new InputStreamReader(System.in))) {
            String fromPlayer;
            String fromServer;

            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                fromPlayer = playerIn.readLine();
                if (fromPlayer != null){
                    out.println(fromPlayer);
                }
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Main {
        public static void main(String[] args) throws IOException {
            Player player1 = new Player();
        }
    }
}