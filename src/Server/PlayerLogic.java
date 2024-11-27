package Server;

import java.io.*;
import java.net.*;

public class PlayerLogic {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public PlayerLogic() {
        try {
            socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println(serverMessage); // Server feedback
                BufferedReader playerInput = new BufferedReader(new InputStreamReader(System.in));
                String playerResponse = playerInput.readLine();
                out.println(playerResponse); // Send input to server
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PlayerLogic();
    }
}
