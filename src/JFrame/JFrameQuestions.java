package JFrame;
import Server.Database;
import Server.GameInformation;
import Server.Question;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import static JFrame.JFrameScore.*;
import static JFrame.JFrameScore.user1NameLabel;
import static JFrame.JFrameWinLoesScreen.winUserName;
import static JFrame.JFrameWinLoesScreen.winUserPicture;

public class JFrameQuestions extends JPanel  {

    public static String userPicture ;
    public static String userName;

    private static Timer timer;
    private static int timerCounter = 0;

    String cPurple = "#7540EE";
    static String cYellow ="#F4B512";
    static String cRed ="#F95179";
    static String cBlack ="#0D071A";
    String cWhite ="#CECECE";


    public JFrameQuestions() {
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

    JPanel userPanel = new JPanel();
    static JPanel questionPanel = new JPanel();
    static JPanel timerPanel = new JPanel();
    static JPanel answersPanel = new JPanel();

    JLabel userNameLabel = new JLabel("User Name");
    JLabel userPictureLabel = new JLabel();

    public static JLabel questionLabel = new JLabel("Question");
    public static JLabel question2Label = new JLabel("");

    public static int questionsAsked = 1;



    public void InitializeComponents() {
        // Main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.decode(cPurple));
        mainPanel.setPreferredSize(new Dimension(800, 600));
        mainPanel.setVisible(true);
        Border border = BorderFactory.createLineBorder(Color.decode(cBlack), 15);
        mainPanel.setBorder(border);

        // Top Panel
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
        topPanel.setBackground(Color.decode(cPurple));
        topPanel.setPreferredSize(new Dimension(800, 50));

        // Center Panel
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.decode(cPurple));
        centerPanel.setPreferredSize(new Dimension(300, 600));

        // Side Panels
        westPanel.setLayout(new FlowLayout());
        westPanel.setBackground(Color.decode(cPurple));
        westPanel.setPreferredSize(new Dimension(200, 600));
        eastPanel.setLayout(new FlowLayout());
        eastPanel.setBackground(Color.decode(cPurple));
        eastPanel.setPreferredSize(new Dimension(200, 600));

        // Bottom Panel
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.decode(cPurple));
        bottomPanel.setPreferredSize(new Dimension(800, 100));


        //Inside Panels
        //User Panel
        userPanel.setBackground(Color.decode(cPurple));
        userPanel.setPreferredSize(new Dimension(800, 100));
        userPanel.setLayout(new GridLayout(2,1));

        //Here to update username and picture
        Player.createPlayerLabels(userNameLabel,userPictureLabel,userName,userPicture);
        userPanel.add(userPictureLabel);
        userPanel.add(userNameLabel);

        //Question Panel
        questionPanel.setBackground(Color.decode(cWhite));
        questionPanel.setPreferredSize(new Dimension(800, 400));
        questionPanel.setMaximumSize(new Dimension(800, 400));

        questionPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Vertical alignment
        questionPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));



        //Question Text & Label
        questionLabel.setFont(new Font("Garamond",Font.BOLD,35));
        question2Label.setFont(new Font("Garamond",Font.BOLD,35));
        questionLabel.setMaximumSize(new Dimension(800,200));
        question2Label.setMaximumSize(new Dimension(800,200));




        //Timer Panel
        timerPanel.setBackground(Color.decode(cPurple));
        timerPanel.setPreferredSize(new Dimension(400, 50));
        timerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        timerPanel.setBorder(BorderFactory.createEmptyBorder(0, 300, 0, 300)); // Top, Left, Bottom, Right padding

        //Answer Panel
        answersPanel.setBackground(Color.decode(cPurple));
        answersPanel.setPreferredSize(new Dimension(600, 400));
        answersPanel.setLayout(new GridLayout(2,2,10,10));


        // Add components
        centerPanel.add(userPanel);
        centerPanel.add(questionPanel);
        centerPanel.add(timerPanel);
        centerPanel.add(answersPanel);

        add(mainPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(westPanel, BorderLayout.WEST);

        mainPanel.repaint();
        mainPanel.revalidate();
    }

    public static void getCategory(String category, String difficulty, JLabel questionLabel,JLabel questionlabel2 ,int questionsAsked) {
        Database db = new Database(category, difficulty);
        JSONArray results = db.loadJSON().getJSONArray("results");

        //Clears The Panel
        answersPanel.removeAll();
        answersPanel.repaint();
        answersPanel.revalidate();


        for (int i = 0; i < 1; i++) {
            Random rand = new Random();
            JSONObject result = results.getJSONObject(rand.nextInt(10));
            Question question = new Question(result);

            // Retrieve the question text
            String fullQuestionText = question.getQuestion();
            String firstPart = "";
            String secondPart = "";

            // Split the question text for multiline display
            if (fullQuestionText.length() > 40) { // Adjust the limit for line length as needed
                int breakPoint = fullQuestionText.lastIndexOf(" ", 40); // Find the last space before 40 characters
                if (breakPoint == -1) breakPoint = 40; // If no space is found, force the split
                firstPart = fullQuestionText.substring(0, breakPoint).trim();
                secondPart = fullQuestionText.substring(breakPoint).trim();
            } else {
                firstPart = fullQuestionText;
            }

            // Use HTML formatting for multiline display in JLabel
            questionLabel.setText("<html>" + firstPart + "</html>");
            questionlabel2.setText("<html>" + secondPart + "</html>");

            // Debugging output to verify split
            System.out.println("Label 1: " + questionLabel.getText() + "\nLabel 2: " + questionlabel2.getText());

            questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            questionlabel2.setHorizontalAlignment(SwingConstants.CENTER);
            questionLabel.setFont(new Font("Garamond",Font.BOLD,35));
            questionlabel2.setFont(new Font("Garamond",Font.BOLD,35));
            questionLabel.setMaximumSize(new Dimension(800,200));
            questionlabel2.setMaximumSize(new Dimension(800,200));

            questionLabel.setVerticalAlignment(SwingConstants.CENTER);
            questionlabel2.setVerticalAlignment(SwingConstants.CENTER);



            // Clear and update the question panel
            questionPanel.removeAll(); // Remove old components to avoid duplicates
            questionPanel.add(questionLabel);
            questionPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add vertical spacing
            questionPanel.add(questionlabel2);

            // Refresh the panel
            questionPanel.revalidate();
            questionPanel.repaint();


            JSONArray answerOptions = question.getOptions();
            Collections.shuffle(answerOptions.toList());


            for (int j = 0; j < answerOptions.length(); j++) {

                JButton button = new JButton();

                String answer = answerOptions.getString(j);
                //Create Button
                //Set Color
                button.setBackground(Color.decode(cRed));
                button.setForeground(Color.decode(cBlack));
                //Size
                button.setFont(new Font("Garamond", Font.BOLD, 30));
                button.setPreferredSize(new Dimension(100, 100));
                button.setMaximumSize(button.getPreferredSize());
                //Text

                button.setText(String.valueOf(answer));
                // Placement
                button.setAlignmentX(Component.CENTER_ALIGNMENT);



                //Action listener
                //Here we can save the answer
                button.addActionListener((ActionListener) e -> {
                    if (button.getText().equals(question.getCorrectAnswer())) {

                        //Here we input which player it is
                        //This lets us edit the scores and uses the Questions done in Game information
                        if (true){
                            GameInformation.EditPlayer1Score(GameInformation.GetQuestionsDone(),1);
                        } else if (true) {
                            GameInformation.EditPlayer2Score(GameInformation.GetQuestionsDone(),1);
                        }

                        //Next round information

                        GameInformation.AddQuestionsDone();
                        NextQuestion();


                    }else {
                        //Here we input which player it is
                        //This lets us edit the scores and uses the Questions done in Game information
                        if (true){
                            GameInformation.EditPlayer1Score(GameInformation.GetQuestionsDone(),2);
                        } else if (true) {
                            GameInformation.EditPlayer2Score(GameInformation.GetQuestionsDone(),2);
                        }

                        //Next round information

                        GameInformation.AddQuestionsDone();
                        NextQuestion();

                    }

                    button.addMouseListener(new MouseAdapter() {
                        Color originalColor = button.getBackground();
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            button.setBackground(Color.decode(cYellow)); // Change color on hover
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            button.setBackground(originalColor); // Reset to original color
                        }
                    });
                });
                //Adds button in a random spot
                try {
                    answersPanel.add(button, rand.nextInt(2), rand.nextInt(2));
                }catch (IllegalArgumentException e){
                    answersPanel.add(button);
                }

            }
            answersPanel.repaint();
            answersPanel.revalidate();
        }
    }

    public static void NextQuestion(){
        resetTimer();
        startTimer();
        if (questionsAsked == 3){
            GameInformation.NextRound();
            if (GameInformation.RoundNumber() == GameInformation.GetRoundsWanted()){
                System.out.println("You won");

                JFrameWinLoesScreen.player1ScoreInt = GameInformation.GetPlayer1Result();
                JFrameWinLoesScreen.resultLabel.setText(String.valueOf(GameInformation.GetPlayer1Result()));
                JFrameWinLoesScreen.resultLabel.repaint();
                JFrameWinLoesScreen.resultLabel.revalidate();
                JFrameScore.CreatePlayerLabels(winUserPicture,winUserName,GameInformation.GetUserName(),GameInformation.GetUserPicture());
                JFrameMain.switchPanel("JFrameWinLoesScreen");
            }else {
                JFrameMain.switchPanel("JFrameScore");
            }
            resetTimer();

            JFrameScore.p1RoundListPanel.removeAll();
            JFrameScore.p2RoundListPanel.removeAll();
            JFrameScore.ScorePanel(p1RoundListPanel, GameInformation.player1Score, GameInformation.GetRoundsWanted(), GameInformation.GetQuestionsPerRoundWanted());  // Player 1 Score Panel
            JFrameScore.ScorePanel(p2RoundListPanel, GameInformation.player2Score, GameInformation.GetRoundsWanted(), GameInformation.GetQuestionsPerRoundWanted());
            questionsAsked = 0;

        }

        questionsAsked++;

        getCategory(GameInformation.GetCategoryPicked(),GameInformation.GetDifficultyPicked(),JFrameQuestions.questionLabel,question2Label,questionsAsked);

    }

    //Timer V2
    public static void resetTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timerCounter = 0;
        timerPanel.removeAll();
        timerPanel.repaint();
        timerPanel.revalidate();
    }

    // Timer Start Method
    public static void startTimer() {
        resetTimer();

        timer = new Timer(500, e -> {
            timerCounter++;

            JLabel block = new JLabel();
            block.setOpaque(true);
            block.setPreferredSize(new Dimension(50, 20));
            block.setVerticalAlignment(SwingConstants.CENTER);

            // Change block color based on time left
            if (timerCounter == 9){
                //Here we input which player it is
                //This lets us edit the scores and uses the Questions done in Game information
                if (true){
                    GameInformation.EditPlayer1Score(GameInformation.GetQuestionsDone(),2);

                } else if (true) {
                    GameInformation.EditPlayer2Score(GameInformation.GetQuestionsDone(),2);

                }
                GameInformation.AddQuestionsDone();

                resetTimer();
                NextQuestion();
            }
            if (timerCounter >= 7) {
                block.setBackground(Color.decode(cRed));
            } else {
                block.setBackground(Color.decode(cYellow));
            }

            timerPanel.add(block);
            timerPanel.revalidate();
            timerPanel.repaint();

        });

        timer.start();
    }
}
