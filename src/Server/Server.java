package Server;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import org.json.JSONArray;

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

                out.print("welcome to the server " + "skriv ditt namn\n");
                out.println(p.getOutput(null));

                String playerId;
                while ((playerId = in.readLine()) != null) {
                    System.out.println("du heter: " + playerId);
                    System.out.println("Echo: " + p.getOutput(playerId));
                    String dbAnswer = p.getOutput(playerId.trim());
                    out.println(dbAnswer);
                }

                socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
