package JFrame;
import Server.Database;
import Server.GameInformation;
import Server.GameQuestion;
import Server.Question;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class JFrameQuestions extends JPanel  {

    public static String userPicture ;
    public static String userName;

    private static boolean timerStarted = false;
    private Timer timer;
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
    JPanel questionPanel = new JPanel();
    JPanel timerPanel = new JPanel();
    static JPanel answersPanel = new JPanel();

    JLabel userNameLabel = new JLabel("User Name");
    JLabel userPictureLabel = new JLabel();

    public static JLabel questionLabel = new JLabel("Question");

    //Temp
    JLabel timerLabel = new JLabel("Timer:");


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
        questionPanel.setLayout(new GridBagLayout());



        //Question Text & Label
        questionLabel.setFont(new Font("Garamond",Font.BOLD,40));
        questionPanel.add(questionLabel);


        //Timer Panel
        timerPanel.setBackground(Color.decode(cPurple));
        timerPanel.setPreferredSize(new Dimension(400, 50));
        timerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        timerPanel.setBorder(BorderFactory.createEmptyBorder(0, 300, 0, 300)); // Top, Left, Bottom, Right padding



        //Answer Panel
        answersPanel.setBackground(Color.decode(cPurple));
        answersPanel.setPreferredSize(new Dimension(600, 400));
        answersPanel.setLayout(new GridLayout(2,2,10,10));

        //Adds the buttons



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

    public static void getCategory(String category, String difficulty, JLabel questionLabel){
        Database db = new Database(category, difficulty);
        JSONArray results = db.loadJSON().getJSONArray("results");

        for (int i = 0; i < 1; i++) {
            JSONObject result = results.getJSONObject(i);
            Question question = new Question(result);

            //Adds question from Json
            questionLabel.setText(question.getQuestion());

            JSONArray answerOptions = question.getOptions();
            System.out.println("Before " + answerOptions);
            Collections.shuffle(answerOptions.toList());
            System.out.println("After " + answerOptions);

            question.getOptions();

            // System.out.println("AnswerOptions: " + answerOptions[1]);
            System.out.println("Alternativ: " + question.getOptions());


            for (int j = 0; j < answerOptions.length(); j++) {
                String answer = answerOptions.getString(j);
                //Create Button
                JButton button = new JButton();
                //Set Color
                button.setBackground(Color.decode(cRed));
                button.setForeground(Color.decode(cBlack));
                //Size
                button.setFont(new Font("Garamond", Font.BOLD, 30));
                button.setPreferredSize(new Dimension(100, 100));
                button.setMaximumSize(button.getPreferredSize());
                //Text
                System.out.println("answer: " + answer);
                button.setText(String.valueOf(answer));
                // Placement
                button.setAlignmentX(Component.CENTER_ALIGNMENT);



                //Action listener
                //Here we can save the answer
                button.addActionListener((ActionListener) e -> {
                    if (question.equals("rätt svar!!")) {
                        JOptionPane.showMessageDialog(null, "yippi");
                        JFrameScore.player1Score[GameInformation.RoundNumber()] = 1;
                        GameInformation.NextRound();

                    }else {
                        JOptionPane.showMessageDialog(null, "whom whomp");
                        JFrameScore.player2Score[GameInformation.RoundNumber()] = 0;
                        GameInformation.NextRound();
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
                answersPanel.add(button);

            }
        }

    }





    ArrayList<String> questionNames = new ArrayList<>();

    public void QuestionNameRandomiser (){




        questionNames.add("rätt svar!!");
        questionNames.add("fel svar1!!");
        questionNames.add("fel svar2!!");
        questionNames.add("fel svar3!!");

        Collections.shuffle(questionNames);
    }





    //15-second timer
    public void StartTimer() {
        if(timerStarted) return;

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerCounter++;
                JLabel block = new JLabel();
                if (timerCounter == 9){
                    block.setBackground(Color.decode(cBlack));
                    //Add lost round



                }
                else if (timerCounter >= 7){
                    block.setBackground(Color.decode(cRed));
                }else {
                    block.setBackground(Color.decode(cYellow));
                }
                block.setVisible(true);
                block.setOpaque(true);
                block.setPreferredSize(new Dimension(50, 20));
                block.setVerticalAlignment(SwingConstants.CENTER);

                timerPanel.add(block);
                timerPanel.revalidate();
                timerPanel.repaint();
            }

        });
        timer.start();
        timerStarted = true;
    }
    public void StopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            StartTimer();  // Start the timer when the panel becomes visible
        } else {
            StopTimer();  // Stop the timer when the panel is hidden
        }
    }
}
