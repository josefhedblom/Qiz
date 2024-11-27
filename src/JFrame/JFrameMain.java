package JFrame;

import javax.swing.*;
import java.awt.*;
import Server.Player;

//This controls all the JFrames
public class JFrameMain extends JFrame {
    //Card layout is used to move between different views
    static CardLayout cardLayout = new CardLayout();
    static JPanel masterPanel = new JPanel(cardLayout);


    /*
    Colors
    Purple #7540EE
    Yellow #F4B512
    Red #F95179
    Black #0D071A
    White #CECECE
     */

   public JFrameMain (){
        add(masterPanel);
        masterPanel.add(new JFrameStart(),"JFrameStart");
        masterPanel.add(new JFrameScore(),"JFrameScore");
        masterPanel.add(new JFrameCatagorys(),"JFrameCatgorys");
        masterPanel.add(new JFrameQuestions(),"JFrameQuestions");
        masterPanel.add(new JFrameWinLoesScreen(),"JFrameWinLoesScreen");

       InitializeComponents();
       startPlayer();
    }

    public void InitializeComponents(){

        //End
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Switch between the JPanels
    public static void switchPanel(String panelToShow) {
        cardLayout.show(masterPanel, panelToShow);
        // Resize the window based on the panel name
        Window window = SwingUtilities.getWindowAncestor(masterPanel);  // Get the top-level window
        if (window instanceof JFrame) {
            JFrame frame = (JFrame) window;
            frame.setLocationRelativeTo(null);
            masterPanel.revalidate();
            masterPanel.repaint();
        }
    }
    public void startPlayer (){
       new Player();
    }

    public static void main(String[] args) {
        JFrameMain frame = new JFrameMain();
        switchPanel("JFrameStart");
    }
}
