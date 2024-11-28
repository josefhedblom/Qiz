package Server;

public class GameInformation {


    static String userName;
    static String userPicture;
    static int RoundNumber =0;
    static String categoryPicked;
    static String difficultyPicked;
    static int roundsWanted;
    static int questionsPerRoundWanted;
    public static int[] player1Score = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    public static int[] player2Score = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static int questionsDone = 0;

    public static int RoundNumber(){
        return RoundNumber;
    }
    public static void NextRound(){
        RoundNumber++;
    }
    public static String GetCategoryPicked(){
        return categoryPicked;
    }
    public static void SetCategoryPicked(String CategoryPicked){
        categoryPicked = CategoryPicked;
    }
    public static String GetDifficultyPicked(){
        return difficultyPicked;
    }
    public static void SetDifficultyPicked(String DifficultyPicked){
        difficultyPicked = DifficultyPicked;
    }
    public static int GetRoundsWanted(){
        return roundsWanted;
    }
    public static void SetRoundsWanted(int RoundsWanted){
        roundsWanted = RoundsWanted;
    }
    public static int GetQuestionsPerRoundWanted(){
        return questionsPerRoundWanted;
    }
    public static void SetQuestionsPerRoundWanted(int QuestionsPerRoundWanted){
        questionsPerRoundWanted = QuestionsPerRoundWanted;
    }
    public static void EditPlayer1Score(int index, int score){
        player1Score[index] = score;
    }
    public static void EditPlayer2Score(int index, int score){
        player1Score[index] = score;
    }
    public static int GetPlayer1Score(int index){
        return player1Score[index];
    }
    public static int GetPlayer2Score(int index){
        return player2Score[index];
    }
    public static int GetQuestionsDone(){
        return questionsDone;
    }
    public static void AddQuestionsDone(){
        questionsDone++;
    }
    public static String GetUserName(){
        return userName;
    }
    public static void SetUserName(String UserName){
        userName = UserName;
    }
    public static String GetUserPicture(){
        return userPicture;
    }
    public static void SetUserPicture(String UserPicture){
        userPicture = UserPicture;
    }
    public  static int GetPlayer1Result(){
        int score= 0;
        if (player1Score == null){
            System.out.println("Score is null error");
            return score;
        }
        int lenght = player1Score.length;
        System.out.println("Array length: " + lenght);
        for (int i = 0; i <lenght ; i++) {
            if (player1Score[i] == 1){
                score++;
                System.out.println("score:"+score);
            }
        }
        System.out.println("Score is: " + score);
        return score;
    }
}
