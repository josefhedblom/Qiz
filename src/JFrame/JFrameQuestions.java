package JFrame;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JFrameQuestions extends JPanel {

    String cPurple = "#7540EE";
    String cYellow ="#F4B512";
    String cRed ="#F95179";
    String cBlack ="#0D071A";
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
    JPanel answersPanel = new JPanel();

    JLabel userNameLabel = new JLabel("User Name");
    JLabel userPictureLabel = new JLabel();

    JLabel questionLabel = new JLabel("Question bla bla bla la ejied sjs");

    //Temp
    JLabel timerLabel = new JLabel("Timer");


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
        userCustomize.userProfile(userPictureLabel,userNameLabel,"Player 1");

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
        timerPanel.setPreferredSize(new Dimension(800, 50));
        timerPanel.add(timerLabel);

        //Answer Panel
        answersPanel.setBackground(Color.decode(cPurple));
        answersPanel.setPreferredSize(new Dimension(600, 400));
        answersPanel.setLayout(new GridLayout(2,2,10,10));


        CategoryButton();


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


    public void CategoryButton () {

        for (int i = 0; i <= 3; i++) {


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
            button.setText("Answer");
            // Placement
            button.setAlignmentX(Component.CENTER_ALIGNMENT);


            //Action listener
            button.addActionListener(new MyActionListener());
            //button.setActionCommand("Answer 1");

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
            answersPanel.add(button);
        }
    }
}
