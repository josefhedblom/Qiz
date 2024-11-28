package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class QuizGame extends Thread {
    private Socket socket1;
    private Socket socket2;
    private Protocol protocol;
    private Socket currentPlayer;

    public QuizGame(Socket socket1, Socket socket2) {
        this.socket1 = socket1;
        this.socket2 = socket2;
        this.protocol = new Protocol();
        this.currentPlayer = socket1; // Börja med socket1
    }

    @Override
    public void run() {
        try (
                PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);
                BufferedReader in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
                PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);
                BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()))
        ) {
            PrintWriter currentOut = out1;
            BufferedReader currentIn = in1;

            while (true) {
                protocol = new Protocol(); // Initiera en ny protokollinstans för ny omgång

                String inputLine;
                currentOut.println(protocol.processInput(null)); // Skicka initialt meddelande till spelaren

                while ((inputLine = currentIn.readLine()) != null) {
                    String response = protocol.processInput(inputLine.trim());
                    currentOut.println(response);

                    if (protocol.isGameDone()) { // Kontrollera om spelet är klart
                        break; // Avsluta nuvarande omgång
                    }
                }

                // Växla spelare för nästa omgång
                if (currentPlayer == socket1) {
                    currentPlayer = socket2;
                    currentOut = out2;
                    currentIn = in2;
                } else {
                    currentPlayer = socket1;
                    currentOut = out1;
                    currentIn = in1;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
