package JFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JFrameStart extends JPanel {

    String cPurple = "#7540EE";
    String cYellow ="#F4B512";
    String cRed ="#F95179";
    String cBlack ="#0D071A";
    String cWhite ="#CECECE";
    public JFrameStart(){
        //Set Layout and Color for the Main main panel
        setLayout(new BorderLayout());
        setBackground(Color.decode(cPurple));

        InitializeComponents();

    }
    JPanel mainPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel westPanel = new JPanel();
    JPanel eastPanel = new JPanel();
    JPanel centerTopPanel = new JPanel();
    JPanel centerCenterPanel = new JPanel();

    JLabel titleLabel = new JLabel("QuizKampen");
    JLabel userPicture = new JLabel();
    JLabel userName = new JLabel("UserName");
    JButton startButton = new JButton("Start New Game");
    JButton joinButton = new JButton("Join Game");
    JButton exitButton = new JButton("Exit");

    public void InitializeComponents(){
        //Main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.decode(cPurple));
        mainPanel.setPreferredSize(new Dimension(800, 600));
        mainPanel.setVisible(true);

        //Top Panel
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(Color.decode(cPurple));
        topPanel.setPreferredSize(new Dimension(800, 100));
        //title
        titleLabel.setFont(new Font("Garamond", Font.BOLD, 50));
        titleLabel.setForeground(Color.decode(cBlack));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //Center Panel
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.decode(cPurple));
        centerPanel.setPreferredSize(new Dimension(800, 600));
        centerCenterPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerCenterPanel.setBorder(new EmptyBorder(300, 0, 0, 0)); // Top padding of 100px
        centerCenterPanel.setBackground(Color.decode(cPurple));
        centerTopPanel.setBackground(Color.decode(cPurple));
        centerTopPanel.setLayout(new BoxLayout(centerTopPanel, BoxLayout.Y_AXIS));


        //User information
        char smiley= '\u263A';
        userPicture.setHorizontalAlignment(SwingConstants.CENTER);
        userPicture.setText(String.valueOf(smiley));
        userPicture.setFont(new Font("Garamond", Font.BOLD, 100));
        userPicture.setForeground(Color.decode(cBlack));
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setFont(new Font("Garamond", Font.BOLD, 40));
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);
        userName.setForeground(Color.decode(cBlack));
        userPicture.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Start Buttons
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        startButton.setFont(new Font("Garamond", Font.BOLD, 40));
        startButton.setBackground(Color.decode(cYellow));
        startButton.setForeground(Color.decode(cBlack));
        startButton.setOpaque(true);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinButton.setHorizontalAlignment(SwingConstants.CENTER);
        joinButton.setFont(new Font("Garamond", Font.BOLD, 40));
        joinButton.setBackground(Color.decode(cYellow));
        joinButton.setForeground(Color.decode(cBlack));
        joinButton.setOpaque(true);
        joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.setFont(new Font("Garamond", Font.BOLD, 40));
        exitButton.setForeground(Color.decode(cBlack));
        exitButton.setBackground(Color.decode(cRed));
        exitButton.setOpaque(true);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(new MyActionListener());
        joinButton.addActionListener(new MyActionListener());
        exitButton.addActionListener(new MyActionListener());
        startButton.setActionCommand("startNewGame");
        joinButton.setActionCommand("joinGame");
        exitButton.setActionCommand("exit");


        //Side Panel
        westPanel.setLayout(new FlowLayout());
        westPanel.setBackground(Color.decode(cPurple));
        westPanel.setPreferredSize(new Dimension(100, 600));
        eastPanel.setLayout(new FlowLayout());
        eastPanel.setBackground(Color.decode(cPurple));
        eastPanel.setPreferredSize(new Dimension(100, 600));

        //Bottom Panel
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.decode(cPurple));
        bottomPanel.setPreferredSize(new Dimension(800, 100));

        //Add components
        topPanel.add(titleLabel);
        centerPanel.add(centerTopPanel, BorderLayout.NORTH);
        centerPanel.add(centerCenterPanel, BorderLayout.CENTER);

        centerTopPanel.add(userPicture);
        centerTopPanel.add(userName);
        centerCenterPanel.add(startButton);
        centerCenterPanel.add(joinButton);

        bottomPanel.add(exitButton);

        add(mainPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(westPanel, BorderLayout.WEST);



        mainPanel.repaint();
        mainPanel.revalidate();

    }
}
