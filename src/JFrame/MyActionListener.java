package JFrame;
import Server.GameInformation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static JFrame.JFrameScore.*;

public class MyActionListener extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("startNewGame".equals(actionCommand)) {
            System.out.println("Starting new game");

            JFrameScore.ScorePanel(p1RoundListPanel,GameInformation.player1Score, GameInformation.GetRoundsWanted(),GameInformation.GetQuestionsPerRoundWanted());  // Player 1 Score Panel
            JFrameScore.ScorePanel(p2RoundListPanel,GameInformation.player2Score,GameInformation.GetRoundsWanted(),GameInformation.GetQuestionsPerRoundWanted());   // Player 2 Score Panel
            JFrameScore.CreatePlayerLabels(user1PictureLabel,user1NameLabel,GameInformation.GetUserName(),GameInformation.GetUserPicture());
            JFrameScore.CreatePlayerLabels(user2PictureLabel,user2NameLabel,"Player 2",":)");
            JFrameMain.switchPanel("JFrameScore");


            //Add to be able to join a current game
        } else if ("joinGame".equals(actionCommand)) {
            System.out.println("Join game");
            //Switch panels with: JFrameMain.switchPanel("");
        } else if ("exit".equals(actionCommand)) {
            System.out.println("exit");
            System.exit(0);
        } else if ("startRound".equals(actionCommand)) {
            JFrameMain.switchPanel("JFrameCatgorys");
        } else if ("mainMenu".equals(actionCommand)) {
            JFrameMain.switchPanel("JFrameStart");
        }
    }
}
