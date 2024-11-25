package JFrame;
import Server.GameInformation;
import jdk.jfr.Category;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.List;


public class JFrameCatagorys extends JPanel {

    //Customize
    //Here we can add all category's
    String [] CategoryList = {"Brädspel","Film","Musik","TV","TV Spel"};
    public static String categorySelected;
    public static String modeSelected;


    String cPurple = "#7540EE";
    String cYellow ="#F4B512";
    String cRed ="#F95179";
    String cBlack ="#0D071A";
    String cWhite ="#CECECE";


    public JFrameCatagorys() {
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

    JLabel titleLabel = new JLabel("Välj en kategori");
    JButton easyModeButton = new JButton("Bebis Mode");
    JButton mediumModeButton = new JButton("Adult Mode");
    JButton hardModeButton = new JButton("Old Mode");

    public void InitializeComponents() {
        // Main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.decode(cPurple));
        mainPanel.setPreferredSize(new Dimension(800, 600));
        mainPanel.setVisible(true);
        Border border = BorderFactory.createLineBorder(Color.decode(cBlack), 15);
        mainPanel.setBorder(border);

        // Top Panel
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,50));
        topPanel.setBackground(Color.decode(cPurple));
        topPanel.setPreferredSize(new Dimension(800, 150));
        // Title
        titleLabel.setBackground(Color.decode(cPurple));
        titleLabel.setForeground(Color.decode(cBlack));
        titleLabel.setFont(new Font("Garamond", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Center Panel
        centerPanel.setLayout(new GridLayout(3,1,0,40));
        centerPanel.setBackground(Color.decode(cPurple));
        centerPanel.setPreferredSize(new Dimension(300, 500));

        //Buttons

        CategoryButton(CategoryList);

        //Mode Buttons
        easyModeButton.setBackground(Color.decode(cWhite));
        easyModeButton.setPreferredSize(new Dimension(150, 80));
        easyModeButton.setFont(new Font("Garamond", Font.BOLD, 20));
        easyModeButton.setHorizontalAlignment(SwingConstants.CENTER);
        mediumModeButton.setBackground(Color.decode(cYellow));
        mediumModeButton.setPreferredSize(new Dimension(150, 80));
        mediumModeButton.setFont(new Font("Garamond", Font.BOLD, 20));
        mediumModeButton.setHorizontalAlignment(SwingConstants.CENTER);
        hardModeButton.setBackground(Color.decode(cRed));
        hardModeButton.setPreferredSize(new Dimension(150, 80));
        hardModeButton.setFont(new Font("Garamond", Font.BOLD, 20));
        hardModeButton.setHorizontalAlignment(SwingConstants.CENTER);

        //action listeners
        //Save what Mode the question is
        easyModeButton.addActionListener(e -> {
            modeSelected = "easy";
            JFrameMain.switchPanel("JFrameQuestions");
            GameInformation.SetDifficultyPicked("Lätt");
            JFrameQuestions.getCategory(GameInformation.GetCategoryPicked(),GameInformation.GetDifficultyPicked(),JFrameQuestions.questionLabel);


        });
        mediumModeButton.addActionListener(e -> {
            JFrameMain.switchPanel("JFrameQuestions");
            GameInformation.SetDifficultyPicked("Medium");
            JFrameQuestions.getCategory(GameInformation.GetCategoryPicked(),GameInformation.GetDifficultyPicked(),JFrameQuestions.questionLabel);


        });
        hardModeButton.addActionListener(e -> {
            JFrameMain.switchPanel("JFrameQuestions");
            GameInformation.SetDifficultyPicked("Svår");
            JFrameQuestions.getCategory(GameInformation.GetCategoryPicked(),GameInformation.GetDifficultyPicked(),JFrameQuestions.questionLabel);


        });


        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.add(easyModeButton);
        bottomPanel.add(mediumModeButton);
        bottomPanel.add(hardModeButton);


        // Side Panels
        westPanel.setLayout(new FlowLayout());
        westPanel.setBackground(Color.decode(cPurple));
        westPanel.setPreferredSize(new Dimension(500, 600));
        eastPanel.setLayout(new FlowLayout());
        eastPanel.setBackground(Color.decode(cPurple));
        eastPanel.setPreferredSize(new Dimension(500, 600));

        // Bottom Panel
        bottomPanel.setBackground(Color.decode(cPurple));
        bottomPanel.setPreferredSize(new Dimension(800, 150));


        // Add components
        topPanel.add(titleLabel);

        add(mainPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(westPanel, BorderLayout.WEST);

        mainPanel.repaint();
        mainPanel.revalidate();
    }


    private JButton previouslySelectedButton = null;
    //Here we edit the Category buttons and can add what the category is
    public void CategoryButton(String[] listOfCategorys) {
        Random rand = new Random();

        // Create a list to store selected random categories
        List<String> selectedCategories = new ArrayList<>();
        while (selectedCategories.size() < 3) {
            String randomCategory = listOfCategorys[rand.nextInt(listOfCategorys.length)];
            if (!selectedCategories.contains(randomCategory)) {
                selectedCategories.add(randomCategory); // Ensure uniqueness
            }
        }
        for (String category : selectedCategories) {
            JButton button = new JButton();
            //Set Color
            button.setBackground(Color.decode(cYellow));
            button.setForeground(Color.decode(cBlack));
            //Size
            button.setFont(new Font("Garamond", Font.BOLD, 45));
            button.setPreferredSize(new Dimension(400, 80));
            button.setMaximumSize(button.getPreferredSize());
            //Text
            button.setText(category);
            // Placement
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            //Here we can save the information about what Category they want
            button.addActionListener(e -> {
                if (previouslySelectedButton != null) {
                    previouslySelectedButton.setBackground(Color.decode(cYellow));
                }
                previouslySelectedButton = button;
                button.setBackground(Color.decode(cWhite));
                GameInformation.SetCategoryPicked(category);
            });
            centerPanel.add(button);
        }
    }
}
