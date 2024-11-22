package JFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JFrameScore extends JPanel {

    String cPurple = "#7540EE";
    String cYellow ="#F4B512";
    String cRed ="#F95179";
    String cBlack ="#0D071A";
    String cWhite ="#CECECE";

    //Customize
    public static String whoTurnText = "Din tur";
    public static String player1UserName = "Player 1";
    public static String player2UserName = "Player 2";


    public JFrameScore() {
        //Set Layout and Color for the Main main panel
        setLayout(new BorderLayout());
        setBackground(Color.decode(cPurple));

        InitializeComponents();
    }
    //Panels
    JPanel mainPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel westPanel = new JPanel();
    JPanel eastPanel = new JPanel();
    JPanel centerTopPanel = new JPanel();
    JPanel centerCenterPanel = new JPanel();
    JPanel p1RoundListPanel = new JPanel();
    JPanel p2RoundListPanel = new JPanel();
    JPanel roundWonPanel = new JPanel();
    JPanel userNamesPanel = new JPanel();
    JPanel userPicturePanel = new JPanel();

    //Labels Button
    JLabel whoTurnLabel = new JLabel(whoTurnText);
    JLabel user1Picture = new JLabel();
    JLabel user1Name = new JLabel();
    JLabel user2Picture = new JLabel();
    JLabel user2Name = new JLabel();
    JButton startButton = new JButton("Start");


    public void InitializeComponents() {
        // Main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.decode(cPurple));
        Border border = BorderFactory.createLineBorder(Color.decode(cBlack), 15);
        mainPanel.setBorder(border);

        // Top Panel
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(Color.decode(cPurple));
        topPanel.setPreferredSize(new Dimension(800, 100));
        // Title
        whoTurnLabel.setFont(new Font("Garamond", Font.BOLD, 50));
        whoTurnLabel.setForeground(Color.decode(cBlack));
        whoTurnLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Center Panel
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.decode(cPurple));
        centerPanel.setPreferredSize(new Dimension(800, 600));

        // Center Center Panel
        centerCenterPanel.setLayout(new BoxLayout(centerCenterPanel, BoxLayout.X_AXIS));
        centerCenterPanel.setBackground(Color.decode(cPurple));
        centerCenterPanel.setPreferredSize(new Dimension(400, 400));
        centerCenterPanel.setBorder(new EmptyBorder(20, 0, 20, 0)); // Add padding around the panels

        // Score Panels
        ScorePanel(p1RoundListPanel);  // Player 1 Score Panel
        ScorePanel(p2RoundListPanel);  // Player 2 Score Panel
        RoundWonPanel(roundWonPanel);


        // User Pictures and Names
        userCustomize.userProfile(user1Picture,user1Name,player1UserName);
        userCustomize.userProfile(user2Picture,user2Name,player2UserName);
        userPicturePanel.setLayout(new GridLayout(1, 2, 0, 0)); // Equal spacing for both users
        userPicturePanel.setBorder(new EmptyBorder(0, 60, 0, 60)); //Makes them closer
        userPicturePanel.setBackground(Color.decode(cPurple));
        userPicturePanel.add(user1Picture);
        userPicturePanel.add(user2Picture);

        userNamesPanel.setLayout(new GridLayout(1, 2, 0, 0)); // Align names above pictures
        userNamesPanel.setBorder(new EmptyBorder(0, 60, 0, 60));  //Makes them closer
        userNamesPanel.setBackground(Color.decode(cPurple));
        userNamesPanel.add(user1Name);
        userNamesPanel.add(user2Name);

        // Add the panels to centerTopPanel
        centerTopPanel.setLayout(new BorderLayout());
        centerTopPanel.add(userPicturePanel, BorderLayout.CENTER);
        centerTopPanel.add(userNamesPanel, BorderLayout.SOUTH);

        // Add the panels to centerCenterPanel
        centerCenterPanel.add(Box.createHorizontalStrut(200)); // Add spacing before Player 1 panel
        centerCenterPanel.add(p1RoundListPanel);
        centerCenterPanel.add(Box.createHorizontalStrut(40)); // Adds spacing between panels
        centerCenterPanel.add(roundWonPanel);
        centerCenterPanel.add(Box.createHorizontalStrut(40)); // Adds spacing between panels
        centerCenterPanel.add(p2RoundListPanel);
        centerCenterPanel.add(Box.createHorizontalStrut(200)); // Add spacing after Player 2 panel

        // Side Panels
        westPanel.setBackground(Color.decode(cPurple));
        westPanel.setPreferredSize(new Dimension(100, 600));
        eastPanel.setBackground(Color.decode(cPurple));
        eastPanel.setPreferredSize(new Dimension(100, 600));

        // Bottom Panel
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.decode(cPurple));
        bottomPanel.setPreferredSize(new Dimension(800, 100));

        // Start Button
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        startButton.setFont(new Font("Garamond", Font.BOLD, 40));
        startButton.setBackground(Color.decode(cYellow));
        startButton.setForeground(Color.decode(cBlack));

        startButton.addActionListener(new MyActionListener());
        startButton.setActionCommand("startRound");

        // Add components
        topPanel.add(whoTurnLabel);
        centerPanel.add(centerTopPanel, BorderLayout.NORTH);
        centerPanel.add(centerCenterPanel, BorderLayout.CENTER);

        bottomPanel.add(startButton);

        add(mainPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(westPanel, BorderLayout.WEST);

        mainPanel.repaint();
        mainPanel.revalidate();
    }


    //Generates the dots for each player
    public void ScorePanel(JPanel panel) {

        panel.setBackground(Color.decode(cPurple));
        panel.setVisible(true);

        // Set GridLayout with reduced gaps
        panel.setLayout(new GridLayout(6, 3, 1, 1)); // 2px horizontal gap, 5px vertical gap

        for (int i = 0; i < 18; i++) {
            JLabel questionDot = new JLabel("⚫");
            questionDot.setFont(new Font("Garamond", Font.BOLD, 80)); // Smaller dots for narrower width

            //This determines the color, We can change them to list or another way to save the answers
            if (i == 2){
                questionDot.setForeground(Color.decode(cYellow));

            } else if (i == 3) {
                questionDot.setForeground(Color.decode(cRed));

            }else {
                questionDot.setForeground(Color.decode(cBlack));
            }

            questionDot.setHorizontalAlignment(SwingConstants.CENTER);

            panel.add(questionDot);
        }
        repaint();
        revalidate();
    }
    public  void RoundWonPanel (JPanel panel) {

        panel.setBackground(Color.decode(cPurple));
        panel.setVisible(true);

        // Set GridLayout with reduced gaps
        panel.setLayout(new GridLayout(6, 1, 1, 1)); // 2px horizontal gap, 5px vertical gap

        for (int i = 0; i < 6; i++) {
            JLabel questionDot = new JLabel("⚫");
            questionDot.setFont(new Font("Garamond", Font.BOLD, 80)); // Smaller dots for narrower width

            //This determines the color, We can change them to list or another way to save the answers
            if (i == 2){
                questionDot.setForeground(Color.decode(cYellow));

            } else if (i == 3) {
                questionDot.setForeground(Color.decode(cRed));

            }else {
                questionDot.setForeground(Color.decode(cBlack));
            }

            questionDot.setHorizontalAlignment(SwingConstants.CENTER);

            panel.add(questionDot);
        }
        repaint();
        revalidate();


    }
}
