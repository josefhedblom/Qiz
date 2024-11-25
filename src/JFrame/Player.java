package JFrame;
import javax.swing.*;
import java.awt.*;
import java.io.*;

import static JFrame.JFrameStart.userName;
import static JFrame.JFrameStart.userPicture;

public class Player implements Serializable {

    String name;
    String picture;

    public Player(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }



    public static void createPlayerLabels(JLabel userPicture, JLabel userName, String name, String picture) {

        String cBlack ="#0D071A";

        userPicture.setHorizontalAlignment(SwingConstants.CENTER);
        userPicture.setText(picture);
        userPicture.setFont(new Font("Garamond", Font.BOLD, 30));
        userPicture.setForeground(Color.decode(cBlack));
        userPicture.setAlignmentX(Component.CENTER_ALIGNMENT);

        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setFont(new Font("Garamond", Font.BOLD, 30));
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);
        userName.setForeground(Color.decode(cBlack));
        userName.setText(name);

    }

    //Might remove if we have another way
    public static void savePlayer(Player player, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename, true))) {
            oos.writeObject(player);
            System.out.println("Player saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving player: " + e.getMessage());
        }
    }

    public String getName() {
        return name;
    }
    public String getPicture() {
        return picture;
    }
    @Override
    public String toString() {
        return "Player{" +
                "userName='" + userName + '\'' +
                ", userPicture='" + userPicture + '\'' +
                '}';
    }

}
