import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    //Databas d = new Databas

    public Server() {
        try (ServerSocket ss = new ServerSocket(8888);
             Socket socket = ss.accept();
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);)
        {
            out.println("Waiting for the client request");
            String line;


            while ((line = input.readLine()) != null) {
                //String dbAnswer = d.JosefsFunction(line.trim());
                System.out.println(line.trim());
                out.println(line);
            }




        } catch (IOException e) {
            System.out.println("whomp whomp :c");
            throw new RuntimeException(e);
        }
    }
    public void Sensor () throws IOException{
        Scanner scanner = new Scanner(System.in);
        String ip = "127.0.0.1"; // ändra detta om dena används
        InetAddress iAdress = InetAddress.getByName(ip);
        int port = 8888;
        MulticastSocket socket = new MulticastSocket(port);
        NetworkInterface netif = NetworkInterface.getByName("kolla ip på lanet eller så är det nätverkets ip");
        InetSocketAddress group = new InetSocketAddress(iAdress, port);
        socket.joinGroup(group, netif);

        while (true) {
            System.out.println("kolla att medelandet kommer fram");
            String first = scanner.nextLine();
            System.out.println("kolla att koden funkar");

            String result = first.toUpperCase();
            DatagramPacket packet = new DatagramPacket(result.getBytes(), result.length(), iAdress, port);
            socket.send(packet);
        }
    }


    public static void main(String[] args) {
        Server s = new Server();
    }
}