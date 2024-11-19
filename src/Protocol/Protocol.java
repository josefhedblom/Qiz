package Protocol;

public class Protocol {
    private final int Waiting = 0;
    private static final int Player1StartsGame = 1;
    private static final int Round1Loop = 2;
    private static final int Round2Loop = 3;
    private static final int Round3Loop = 4;
    private static final int Round4Loop = 5;
    private static final int Round5Loop = 6;
    private static final int Round6Loop = 7;
    private static final int ResultsGiven = 8;
    private static final int PlayersBackToMenu = 9;

    public Protocol() {

         int state = Waiting;

        //Waiting for user to connect
        if (state == Waiting) {
            /*
            if (   Player presses Start Button  ) {
                state = Player1StartsGame;
            }
             */
        }

        //Player 1 presses start new game
        if (state == Player1StartsGame) {

            state = Round1Loop;
        }

        //Round 1 loop starts
        if (state == Round1Loop) {
            state = Round2Loop;
        }

        //Round 2 Loop Starts
        if (state == Round2Loop) {
            state = Round3Loop;
        }
        //Round 3 Loop Starts
        if (state == Round3Loop) {
            state = Round4Loop;
        }

        //Round 4 Loop Starts
        if (state == Round4Loop) {
            state = Round5Loop;
        }

        //Round 5 Loop Starts
        if (state == Round5Loop) {
            state = Round6Loop;
        }

        //Round 6 Loop Starts
        if (state == Round6Loop) {
            state = ResultsGiven;
        }
        //Results are given
        if (state == ResultsGiven) {
            state = PlayersBackToMenu;
        }

        //Players go back to menu
        if (state == PlayersBackToMenu) {
            state = Waiting;
        }



    /*
    Question Loop     x=Round number{
         Player Given Q1 Rx
         Player answers Q1 Rx
         Player Given Q2 Rx
         Player Answer Q2 Rx
         Player Given Q3 Rx
         Player Answers Q3 Rx
     */
     /*
     Round Loop   Switch between players for each round
         Player 1 picks a Category
         Question Loop for Player 1
         Player 1 sees their score
         Player 1 waits
         Player 2 joins
         Player 2 Question loop 1
         Player 2 sees both scores
     */
    }
}
