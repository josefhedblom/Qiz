package JFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button pressed");
        String actionCommand = e.getActionCommand();

        if ("startNewGame".equals(actionCommand)) {
            System.out.println("Starting new game");
            JFrameMain.switchPanel("JFrameScore");

        } else if ("joinGame".equals(actionCommand)) {
            System.out.println("Join game");
            //Switch panels with: JFrameMain.switchPanel("");
        } else if ("exit".equals(actionCommand)) {
            System.out.println("exit");
            System.exit(0);
        } else if ("startRound".equals(actionCommand)) {
            JFrameMain.switchPanel("JFrameCatgorys");
        } else if ("StartQuestions".equals(actionCommand)) {
            JFrameMain.switchPanel("JFrameQuestions");
        }
    }
}
