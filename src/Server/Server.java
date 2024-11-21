package Server;

import java.io.*;
import java.net.*;

public class Server extends Thread {
    Socket socket = new Socket();

    Protocol p = new Protocol();
    private String film = "Film";
    private String lätt = "Lätt";
    //Database d = new Database(film, lätt);

    //JSONArray results = d.loadJSON().getJSONArray("results");

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {
                String inputLine;

                out.println(p.processInput(null));

                while ((inputLine = in.readLine()) != null) {
                    String response = p.processInput(inputLine.trim());
                    System.out.println(inputLine);
                    out.println(response);

                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
