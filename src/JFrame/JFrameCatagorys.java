package JFrame;
import javax.swing.*;
import java.awt.*;

public class JFrameCatagorys extends JPanel {

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
    JPanel mainPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel westPanel = new JPanel();
    JPanel eastPanel = new JPanel();

    JLabel titleLabel = new JLabel("Välj en kategori");
    JButton category1Button = new JButton("Category 1");
    JButton category2Button = new JButton("Category 2");
    JButton category3Button = new JButton("Category 3");

    public void InitializeComponents() {
        // Main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.decode(cPurple));
        mainPanel.setPreferredSize(new Dimension(800, 600));
        mainPanel.setVisible(true);

        // Top Panel
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
        topPanel.setBackground(Color.decode(cPurple));
        topPanel.setPreferredSize(new Dimension(800, 200));
        // Title
        titleLabel.setBackground(Color.decode(cPurple));
        titleLabel.setForeground(Color.decode(cBlack));
        titleLabel.setFont(new Font("Garamond", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Center Panel
        centerPanel.setLayout(new GridLayout(3,1,0,40));
        centerPanel.setBackground(Color.decode(cPurple));
        centerPanel.setPreferredSize(new Dimension(300, 600));

        //Buttons

        CategoryButton(category1Button,"Länder");
        CategoryButton(category2Button,"Personer");
        CategoryButton(category3Button,"Djur & Natur");

        centerPanel.add(category1Button);
        centerPanel.add(category2Button);
        centerPanel.add(category3Button);


        // Side Panels
        westPanel.setLayout(new FlowLayout());
        westPanel.setBackground(Color.decode(cPurple));
        westPanel.setPreferredSize(new Dimension(500, 600));
        eastPanel.setLayout(new FlowLayout());
        eastPanel.setBackground(Color.decode(cPurple));
        eastPanel.setPreferredSize(new Dimension(500, 600));

        // Bottom Panel
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.decode(cPurple));
        bottomPanel.setPreferredSize(new Dimension(800, 100));


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


    public void CategoryButton (JButton button, String Category) {

        //Set Color
        button.setBackground(Color.decode(cYellow));
        button.setForeground(Color.decode(cBlack));
        //Size
        button.setFont(new Font("Garamond", Font.BOLD, 50));
        button.setPreferredSize(new Dimension(500, 100));
        button.setMaximumSize(button.getPreferredSize());
        //Text
        button.setText(Category);
        // Placement
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Action listener
        button.addActionListener(new MyActionListener());
        button.setActionCommand("StartQuestions");

        centerPanel.add(button);

    }

}
