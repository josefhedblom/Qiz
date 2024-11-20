package Server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server extends Thread {
    Socket socket = new Socket();

    Protocol p = new Protocol();
    //Databas d = new Databas();

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {

                out.print("welcome to the server\n");
                out.println(p.getOutput(null));

                String temp;
                while ((temp = in.readLine()) != null) {
                    System.out.println("received: " + temp);
                    System.out.println("Echo: " + p.getOutput(temp));
                    String dbAnswer = p.getOutput(temp.trim());
                    out.println(dbAnswer);
                }

                socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    }
