package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class QuizGame extends Thread {
    private Socket socket1;
    private Socket socket2;
    private Protocol protocol;


    public QuizGame(Socket socket1, Socket socket2){
        this.socket1 = socket1;
        this.socket2 = socket2;
        this.protocol = new Protocol();
    }

    public void run() {

        try (
                Socket socket = socket1;
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String inputLine;
            out.println(protocol.processInput(null));

            while ((inputLine = in.readLine()) != null) {
                String response = protocol.processInput(inputLine.trim());
                System.out.println(inputLine);
                out.println(response);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/*


 */
