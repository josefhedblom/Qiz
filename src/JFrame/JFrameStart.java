package JFrame;

import Server.GameInformation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameStart extends JPanel {

    public static String userPicture ;
    public static String userName;


    //Colors
    String cPurple = "#7540EE";
    String cYellow ="#F4B512";
    String cRed ="#F95179";
    String cBlack ="#0D071A";
    String cWhite ="#CECECE";
    public JFrameStart(){
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
    JTextField userNameField = new JTextField("UserName");
    JButton startButton = new JButton("Start New Game");
    JButton joinButton = new JButton("Join Game");
    JButton exitButton = new JButton("Exit");

    String [] faceOptions = {"☺","☻","☹","\uD83D\uDC7B","\uD83E\uDD20"};
    JPanel faceOptionPanel = new JPanel();

    //How many rounds
    int [] roundsWanted = {2,3,4,5,6};
    int [] questionsPerRoundWanted = {1,2,3};
    JPanel customizeGamePanel = new JPanel();
    JLabel roundsWantedLabel = new JLabel("  Rounds per Game?");
    JLabel questionsWantedLabel = new JLabel("Questions Per Round?");

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
        centerTopPanel.setBackground(Color.decode(cPurple));
        centerTopPanel.setLayout(new BoxLayout(centerTopPanel, BoxLayout.Y_AXIS));

        //face Option Panel
        faceOptionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        faceOptionPanel.setBackground(Color.decode(cPurple));
        faceOptionPanel.setPreferredSize(new Dimension(800, 100));

        //User information
        //Radio Buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        for (String buttonI : faceOptions) {
            // Create a radio button using the value of buttonI directly
            JRadioButton radioButton = new JRadioButton(buttonI);
            radioButton.setBackground(Color.decode(cPurple));
            radioButton.setForeground(Color.decode(cBlack));
            radioButton.setFont(new Font("Garamond", Font.BOLD, 80));
            radioButton.setHorizontalAlignment(SwingConstants.CENTER);
            radioButton.setBorder(BorderFactory.createLineBorder(Color.decode(cPurple), 0));
            // Set the default selected radio button
            if (buttonI.equals("☺")) {
                radioButton.setSelected(true);
                GameInformation.SetUserPicture("☺");
            }
            // Add the radio button to the button group and the panel
            buttonGroup.add(radioButton);
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Saves it as the user picture
                    GameInformation.SetUserPicture(radioButton.getText());
                    System.out.println("user picture: " + GameInformation.GetUserPicture());
                }
            });
            faceOptionPanel.add(radioButton);
        }

        userNameField.setHorizontalAlignment(SwingConstants.CENTER);
        userNameField.setFont(new Font("Garamond", Font.BOLD, 40));
        userNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNameField.setForeground(Color.decode(cBlack));
        userNameField.setBackground(Color.decode(cPurple));
        userNameField.setPreferredSize(new Dimension(300, 100));
        userNameField.setBorder(new EmptyBorder(0, 0, 0, 0));
        userNameField.setMaximumSize(userNameField.getPreferredSize());


        //Custom rounds and question Panel
        JPanel roundsPanel = new JPanel();
        roundsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        roundsPanel.setBackground(Color.decode(cPurple));
        roundsPanel.setPreferredSize(new Dimension(800, 120));
        JPanel questionsPanel = new JPanel();
        questionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        questionsPanel.setBackground(Color.decode(cPurple));
        questionsPanel.setPreferredSize(new Dimension(800, 130));
        customizeGamePanel.setLayout(new FlowLayout(FlowLayout.LEFT,100,0));
        customizeGamePanel.setBackground(Color.decode(cPurple));
        customizeGamePanel.setPreferredSize(new Dimension(800, 150));
        roundsWantedLabel.setFont(new Font("Garamond", Font.BOLD, 20));
        questionsWantedLabel.setFont(new Font("Garamond", Font.BOLD, 20));

        roundsWantedLabel.setFont(new Font("Garamond", Font.BOLD, 20));
        questionsWantedLabel.setFont(new Font("Garamond", Font.BOLD, 20));
        roundsWantedLabel.setForeground(Color.decode(cBlack));
        questionsWantedLabel.setForeground(Color.decode(cBlack));

        roundsPanel.add(roundsWantedLabel);
        //How many rounds button
        ButtonGroup buttonGroupRoundWanted = new ButtonGroup();
        for (int buttonI : roundsWanted) {
            // Create a radio button using the value of buttonI directly
            JRadioButton radioButton = new JRadioButton(String.valueOf(buttonI));
            radioButton.setBackground(Color.decode(cPurple));
            radioButton.setForeground(Color.decode(cBlack));
            radioButton.setFont(new Font("Garamond", Font.BOLD, 50));
            radioButton.setHorizontalAlignment(SwingConstants.CENTER);
            radioButton.setBorder(BorderFactory.createLineBorder(Color.decode(cPurple), 0));
            // Set the default selected radio button
            if (buttonI == 6) {
                radioButton.setSelected(true);
                GameInformation.SetRoundsWanted(buttonI);
            }
            // Add the radio button to the button group and the panel
            buttonGroupRoundWanted.add(radioButton);
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Saves it as the user picture
                    GameInformation.SetRoundsWanted(buttonI);
                }
            });
            roundsPanel.add(radioButton);
        }


        questionsPanel.add(questionsWantedLabel);


        //How many Questions per round wanted

        ButtonGroup buttonGroupQuestionsPerGame = new ButtonGroup();
        for (int buttonI : questionsPerRoundWanted) {
            // Create a radio button using the value of buttonI directly
            JRadioButton radioButton = new JRadioButton(String.valueOf(buttonI));
            radioButton.setBackground(Color.decode(cPurple));
            radioButton.setForeground(Color.decode(cBlack));
            radioButton.setFont(new Font("Garamond", Font.BOLD, 50));
            radioButton.setHorizontalAlignment(SwingConstants.CENTER);
            radioButton.setBorder(BorderFactory.createLineBorder(Color.decode(cPurple), 0));
            // Set the default selected radio button
            if (buttonI == 3) {
                radioButton.setSelected(true);
                GameInformation.SetQuestionsPerRoundWanted(buttonI);
            }
            // Add the radio button to the button group and the panel
            buttonGroupQuestionsPerGame.add(radioButton);
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Saves it as the user picture
                    GameInformation.SetQuestionsPerRoundWanted(buttonI);
                }
            });
            questionsPanel.add(radioButton);
        }






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

        //When pressed start button saves information about username and userPicture
        startButton.addActionListener(new MyActionListener());
        joinButton.addActionListener(new MyActionListener());
        exitButton.addActionListener(new MyActionListener());
        startButton.setActionCommand("startNewGame");
        joinButton.setActionCommand("joinGame");
        exitButton.setActionCommand("exit");

        startButton.addActionListener(e -> {
            GameInformation.SetUserName(userNameField.getText());
            System.out.println("Username: "+GameInformation.GetUserName());
        });


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
        centerTopPanel.add(faceOptionPanel);
        centerPanel.add(centerTopPanel, BorderLayout.NORTH);
        customizeGamePanel.add(roundsPanel);
        customizeGamePanel.add(questionsPanel);
        centerPanel.add(customizeGamePanel,BorderLayout.CENTER);
        centerPanel.add(centerCenterPanel, BorderLayout.SOUTH);


        centerTopPanel.add(userNameField);
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
