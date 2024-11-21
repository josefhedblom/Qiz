package JFrame;

import javax.swing.*;
import java.awt.*;

public class userCustomize {



    public static void userProfile(JLabel userPicture, JLabel userName, String name){
        char smiley= '\u263A';
        String cBlack ="#0D071A";

        userPicture.setHorizontalAlignment(SwingConstants.CENTER);
        userPicture.setText(String.valueOf(smiley));
        userPicture.setFont(new Font("Garamond", Font.BOLD, 30));
        userPicture.setForeground(Color.decode(cBlack));
        userPicture.setAlignmentX(Component.CENTER_ALIGNMENT);

        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setFont(new Font("Garamond", Font.BOLD, 30));
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);
        userName.setForeground(Color.decode(cBlack));
        userName.setText(name);

    }
}
