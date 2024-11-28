package JFrame;

import Server.GameInformation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JFrameWinLoesScreen extends JPanel {

    //Colors
    String cPurple = "#7540EE";
    String cYellow ="#F4B512";
    String cRed ="#F95179";
    String cBlack ="#0D071A";
    String cWhite ="#CECECE";
    public JFrameWinLoesScreen() {
        //Set Layout and Color for the Main main panel
        setLayout(new BorderLayout());
        setBackground(Color.decode(cBlack));
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

    JLabel titleLabel = new JLabel("QuizKampen");
    public static JLabel winUserName = new JLabel("Username");
    public static JLabel winUserPicture = new JLabel(":)");
    JButton mainMenuButton = new JButton("Main Menu");
    JButton exitButton = new JButton("Exit");


    //Result

    JPanel resultPanel = new JPanel();
    JLabel resultTextLabel = new JLabel("Your Score is:");
    public static JLabel resultLabel = new JLabel();
    public static int player1ScoreInt;


    public void InitializeComponents(){
        //Main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.decode(cPurple));
        mainPanel.setPreferredSize(new Dimension(800, 600));
        //Adds a border around
        Border border = BorderFactory.createLineBorder(Color.decode(cBlack), 15);
        mainPanel.setBorder(border);
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
        centerPanel.setPreferredSize(new Dimension(800, 300));
        centerCenterPanel.setLayout(new GridBagLayout());
        centerCenterPanel.setBorder(new EmptyBorder(100, 0, 0, 0)); // Top padding of 100px
        centerCenterPanel.setBackground(Color.decode(cPurple));
        centerTopPanel.setBackground(Color.decode(cRed));
        centerTopPanel.setLayout(new BoxLayout(centerTopPanel, BoxLayout.Y_AXIS));


        //Custom rounds and question Panel
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBackground(Color.decode(cYellow));
        resultPanel.setPreferredSize(new Dimension(800, 150));
        //Result Label
        resultLabel.setFont(new Font("Garamond", Font.BOLD, 60));
        resultLabel.setForeground(Color.decode(cBlack));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.CENTER);
        resultLabel.setBackground(Color.decode(cPurple));
        resultTextLabel.setFont(new Font("Garamond", Font.BOLD, 60));
        resultTextLabel.setForeground(Color.decode(cBlack));
        resultTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultTextLabel.setVerticalAlignment(SwingConstants.CENTER);
        resultTextLabel.setBackground(Color.decode(cPurple));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        //Start Buttons
        mainMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
        mainMenuButton.setFont(new Font("Garamond", Font.BOLD, 40));
        mainMenuButton.setBackground(Color.decode(cYellow));
        mainMenuButton.setForeground(Color.decode(cBlack));
        mainMenuButton.setOpaque(true);
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        exitButton.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.setFont(new Font("Garamond", Font.BOLD, 40));
        exitButton.setForeground(Color.decode(cBlack));
        exitButton.setBackground(Color.decode(cRed));
        exitButton.setOpaque(true);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //When pressed start button saves information about username and userPicture
        mainMenuButton.addActionListener(new MyActionListener());
        exitButton.addActionListener(new MyActionListener());
        mainMenuButton.setActionCommand("mainMenu");
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
        resultPanel.add(Box.createVerticalGlue());
        resultPanel.add(resultTextLabel);
        resultLabel.add(Box.createRigidArea(new Dimension(0, 10)));
        resultPanel.add(resultLabel);
        resultPanel.add(Box.createVerticalGlue());

        centerTopPanel.add(winUserPicture);
        centerTopPanel.add(winUserName);
        centerCenterPanel.add(mainMenuButton);
        topPanel.add(titleLabel);
        centerPanel.add(centerTopPanel, BorderLayout.NORTH);
        centerPanel.add(resultPanel,BorderLayout.CENTER);
        centerPanel.add(centerCenterPanel, BorderLayout.SOUTH);


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
