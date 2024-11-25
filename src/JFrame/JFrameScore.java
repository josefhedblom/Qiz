package JFrame;

import Server.GameInformation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JFrameScore extends JPanel {

    //Customize
    public static String whoTurnText = "Din tur";
    public static String user1UserName = "Player 1";
    public static String user2UserName = "Player 2";
    public static String user1Picture = "☺";
    public static String user2Picture = "☺";

    public static int[] player1Score = {0,1,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    public static int[] player2Score = {0,1,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};



    static String cPurple = "#7540EE";
    static String cYellow ="#F4B512";
    static String cRed ="#F95179";
    static String cBlack ="#0D071A";
    String cWhite ="#CECECE";


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
    public static JPanel p1RoundListPanel = new JPanel();
    public static JPanel p2RoundListPanel = new JPanel();
    JPanel roundWonPanel = new JPanel();
    JPanel userNamesPanel = new JPanel();
    JPanel userPicturePanel = new JPanel();

    //Labels Button
    JLabel whoTurnLabel = new JLabel(whoTurnText);
    JLabel user1PictureLabel = new JLabel();
    JLabel user1NameLabel = new JLabel();
    JLabel user2PictureLabel = new JLabel();
    JLabel user2NameLabel = new JLabel();
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

        RoundWonPanel(roundWonPanel);


        // User Pictures and Names
        //Here we can add if we want custom names and picture
        Player.createPlayerLabels(user1PictureLabel, user1NameLabel, user1UserName,user1Picture);
        Player.createPlayerLabels(user2PictureLabel, user2NameLabel, user2UserName,user2Picture);

        userPicturePanel.setLayout(new GridLayout(1, 2, 0, 0)); // Equal spacing for both users
        userPicturePanel.setBorder(new EmptyBorder(0, 60, 0, 60)); //Makes them closer
        userPicturePanel.setBackground(Color.decode(cPurple));
        userPicturePanel.add(user1PictureLabel);
        userPicturePanel.add(user2PictureLabel);

        userNamesPanel.setLayout(new GridLayout(1, 2, 0, 0)); // Align names above pictures
        userNamesPanel.setBorder(new EmptyBorder(0, 60, 0, 60));  //Makes them closer
        userNamesPanel.setBackground(Color.decode(cPurple));
        userNamesPanel.add(user1NameLabel);
        userNamesPanel.add(user2NameLabel);

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
    //Here we add some sort of List to save who won / Lost
    public static void ScorePanel(JPanel panel, int[] score, int roundsWanted, int questionsPerRound) {

        System.out.println("Rounds Wanted: ."+ roundsWanted);
        System.out.println("Questions wanted: ."+ questionsPerRound);

        panel.setBackground(Color.decode(cPurple));
        panel.setVisible(true);

        int row= 1;
        int col= 1;

        switch (roundsWanted){
            case 1:
                row = 1;
                break;
                case 2:
                    row = 2;
                    break;
                    case 3:
                        row = 3;
                        break;
                        case 4:
                            row = 4;
                            break;
                            case 5:
                                row = 5;
                                break;
                                case 6:
                                    row = 6;
                                    break;
        }
        switch (questionsPerRound){
            case 1:
                col = 1;
                break;
                case 2:
                    col = 2;
                    break;
                    case 3:
                        col = 3;
                        break;
                        case 4:
                            col = 4;
                            break;
                            case 5:
                                col = 5;
                                break;
                                case 6:
                                    col = 6;
                                    break;
        }




        int totalPoints = row * col;

        // Set GridLayout with reduced gaps
        panel.setLayout(new GridLayout(row, col, 1, 1)); // 2px horizontal gap, 5px vertical gap

        for (int i = 0; i < totalPoints; i++) {

            JLabel questionDot = new JLabel("⚫");
            questionDot.setFont(new Font("Garamond", Font.BOLD, 80)); // Smaller dots for narrower width

            if (score[i] == 0){
                questionDot.setForeground(Color.decode(cBlack));
            } else if (score[i] == 1) {
                questionDot.setForeground(Color.decode(cYellow));
            }else if (score[i] == 2) {
                questionDot.setForeground(Color.decode(cRed));
            }


            questionDot.setHorizontalAlignment(SwingConstants.CENTER);

            panel.add(questionDot);
        }
    }

    //Same for this
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
