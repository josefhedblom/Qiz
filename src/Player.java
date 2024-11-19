import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Player {

    /*
    public Player() {
        try (Socket socket = new Socket("127.0.0.1", 8888);
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

     */

    public Player () throws IOException{
        int port = 12540;
        String ip = "234.235.236.237"; // ändra detta om denna används
        InetAddress id = InetAddress.getByName(ip);
        InetSocketAddress group = new InetSocketAddress(id, port);
        NetworkInterface netif = NetworkInterface.getByName("Wireless-AC 3165");

        MulticastSocket socket = new MulticastSocket(port);
        socket.joinGroup(group, netif);

        byte[] data = new byte[1024];

        while (true) {
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0 , packet.getLength());
            System.out.println(message);
        }
    }
    public static class Main {
        public static void main(String[] args) throws IOException {
            Player k = new Player();
        }
    }
}