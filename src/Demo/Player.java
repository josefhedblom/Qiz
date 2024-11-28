package Demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
    public Player() {
        try (Socket socket = new Socket("127.0.0.1", 12345); // Serverns IP och port
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader playerIn = new BufferedReader(new InputStreamReader(System.in))) {

            String fromServer;
            String fromPlayer;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Från servern: " + fromServer);

                if (fromServer.contains("Välj en kategori")) {
                    System.out.println("Ange kategori (t.ex. Film, Musik, TV, etc.):");
                    fromPlayer = playerIn.readLine();
                    System.out.println("Skickade till servern: " + fromPlayer);
                    out.println(fromPlayer);
                    out.flush();
                }

                if (fromServer.contains("Välj svårighetsnivå")) {
                    System.out.println("Ange svårighetsnivå (t.ex. Lätt, Medium, Svår):");
                    fromPlayer = playerIn.readLine();
                    System.out.println("Skickade till servern: " + fromPlayer);
                    out.println(fromPlayer);
                    out.flush();
                }

                if (fromServer.contains("Fråga:")) {
                    System.out.println(fromServer);
                    fromServer = in.readLine();
                    System.out.println(fromServer);


                    System.out.println("Ange ditt svar:");
                    fromPlayer = playerIn.readLine();
                    System.out.println("Skickade till servern: " + fromPlayer);
                    out.println(fromPlayer);
                    out.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Player();
    }
}
