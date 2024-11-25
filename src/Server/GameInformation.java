package Server;

public class GameInformation {


    static int RoundNumber =0;
    static String categoryPicked;
    static String difficultyPicked;

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
}
