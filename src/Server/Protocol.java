package Server;

import org.json.JSONArray;
import org.json.JSONObject;

public class Protocol {
    private static final int GET_USERNAME = 0;
    private static final int GET_CATEGORY = 1;
    private static final int GET_DIFFICULTY = 2;
    private static final int CONNECT_DATABASE = 3;
    private static final int ASK_QUESTIONS = 4;
    private static final int CHECK_QUESTION = 5;
    private static final int GAME_OVER = 6;

    private User user = new User();
    private int state = GET_USERNAME;
    private Database database;
    private JSONArray questionsList = new JSONArray();
    private String username;
    private String category;
    private String difficulty;
    private String playerQuestion;
    private int currentQuestionIndex = 0;
    private Question question;
    private int score = 0;


    public String processInput(String input) {
        switch (state) {
            case GET_USERNAME:
                if (input == null || input.trim().isEmpty()) {
                    return "Ange ett användarnamn: ";
                }
                username = input.trim();
                this.user.setUsername(username);
                state = GET_CATEGORY;
                return "Hej " + username + "! Välj en kategori: Film | Musik | TV | Brädspel: ";

            case GET_CATEGORY:
                if (input == null || input.trim().isEmpty()) {
                    return "Ange en kategori: Film | Musik | TV | Brädspel: ";
                }
                category = input.trim();
                state = GET_DIFFICULTY;
                return "Du valde kategorin " + category + ". Ange svårighetsgrad: Lätt | Medium | Svår: ";

            case GET_DIFFICULTY:
                if (input == null || input.trim().isEmpty()) {
                    return "Ange en svårighetsgrad: Lätt | Medium | Svår: ";
                }
                difficulty = input.trim();
                state = CONNECT_DATABASE;
                return "Svårighetsgrad vald: " + difficulty + ". Startar frågor...";

            case CONNECT_DATABASE:
                if (input == null || input.trim().isEmpty()) {
                    Database db = new Database(category, difficulty);
                    this.questionsList = db.loadJSON().getJSONArray("results");
                }
            case ASK_QUESTIONS:
                if(input == null || input.trim().isEmpty()) {
                    if(currentQuestionIndex < questionsList.length()) {
                        JSONObject result = this.questionsList.getJSONObject(currentQuestionIndex);
                        this.question = new Question(result);
                        playerQuestion = this.question.getQuestion();
                        JSONArray options = this.question.getOptions();
                        currentQuestionIndex++;
                        return "Fråga: " + currentQuestionIndex + " " + playerQuestion + " " + options.toString() + ": ";
                    } else {
                        state = GAME_OVER;
                        return "Alla frågor är klara! Tack för att du spelade!";
                    }
                }
                state = CHECK_QUESTION;
            case CHECK_QUESTION:
                if (input.equalsIgnoreCase(this.question.getCorrectAnswer())) {
                    state = ASK_QUESTIONS;
                    this.score++;
                    this.user.setScore(this.score);
                    return "Rätt!";
                } else {
                    state = ASK_QUESTIONS;
                    return "Fel! Rätt svar är: " + this.question.getCorrectAnswer();
                }
            case GAME_OVER:
                return "Spelet är slut. Tack för att du spelade, " + this.user.getUsername() + " du fick totalt " + this.user.getScore() + " poäng!";

            default:
                return "Ogiltigt tillstånd. Starta om spelet.";
        }
    }
}