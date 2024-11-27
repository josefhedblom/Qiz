package Server;

import org.json.JSONArray;

public class Protocol {
    private static String globalCategory = null; // Spara kategorin
    private static String globalDifficulty = null; // Spara svårighetsgraden

    private static final int GET_USERNAME = 0;
    private static final int GET_CATEGORY = 1;
    private static final int GET_DIFFICULTY = 2;
    private static final int CONNECT_DATABASE = 3;
    private static final int ASK_QUESTIONS = 4;
    private static final int CHECK_QUESTION = 5;
    private static final int GAME_DONE = 7;

    private final User user = new User();

    private int state = GET_USERNAME;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private JSONArray questionsList = new JSONArray();
    private Question question;

    private boolean usernameSaved = false; // Ny variabel för att kontrollera om användarnamnet är sparat

    public enum Messages {
        ENTER_USERNAME("Ange ett användarnamn: "),
        ENTER_CATEGORY("Ange en kategori: Film | Musik | TV | Brädspel: "),
        ENTER_DIFFICULTY("Ange en svårighetsgrad: Lätt | Medium | Svår: "),
        GREET_USER("Hej %s! Välj en kategori: Film | Musik | TV | Brädspel: "),
        CHOSEN_CATEGORY("Du valde kategorin %s. Ange svårighetsgrad: Lätt | Medium | Svår: "),
        CHOSEN_DIFFICULTY("Svårighetsgrad vald: %s. Startar frågor..."),
        QUESTION("Fråga: %d  %s %s : "),
        RIGHT_ANSWER("Rätt!"),
        WRONG_ANSWER("Fel! Rätt svar är: %s "),
        GAME_DONE("Alla frågor är klara! Tack för att du spelade, %s! Du fick totalt %d poäng!"),
        ERROR("Ogiltigt tillstånd. Starta om spelet.");

        private final String message;

        Messages(String message) {
            this.message = message;
        }

        public String format(Object... args) {
            return String.format(message, args);
        }
    }

    public String processInput(String input) {
        switch (state) {
            case GET_USERNAME:
                return handleGetUsername(input);
            case GET_CATEGORY:
                return handleGetCategory(input);
            case GET_DIFFICULTY:
                return handleGetDifficulty(input);
            case CONNECT_DATABASE:
                handleConnectDatabase();
                return processInput(null); // Gå direkt till nästa steg
            case ASK_QUESTIONS:
                return handleAskQuestions(input);
            case CHECK_QUESTION:
                return handleCheckQuestions(input);
            case GAME_DONE:
                return handleGameDone();
            default:
                return Messages.ERROR.format();
        }
    }

    public boolean isGameDone() {
        return state == GAME_DONE;
    }

    private String handleGetUsername(String input) {
        if (usernameSaved) { // Hoppa över användarnamnsinmatning om det redan är sparat
            state = GET_CATEGORY;
            return Messages.GREET_USER.format(user.getUsername());
        }

        if (isValid(input)) return Messages.ENTER_USERNAME.format();
        user.setUsername(input);
        usernameSaved = true; // Markera att användarnamnet är sparat
        state = GET_CATEGORY;
        return Messages.GREET_USER.format(user.getUsername());
    }

    private String handleGetCategory(String input) {
        // Om det är spelare 1, uppdatera den globala kategorin
        if (globalCategory == null || this.user.isPlayerOne()) {
            if (isValid(input)) return Messages.ENTER_CATEGORY.format();
            globalCategory = input.trim(); // Uppdatera kategorin
        }
        user.setCategory(globalCategory); // Tilldela den aktuella spelaren kategorin
        state = GET_DIFFICULTY;
        return Messages.CHOSEN_CATEGORY.format(user.getCategory());
    }



    private String handleGetDifficulty(String input) {
        // Om det är spelare 1, uppdatera den globala svårighetsgraden
        if (globalDifficulty == null || this.user.isPlayerOne()) {
            if (isValid(input)) return Messages.ENTER_DIFFICULTY.format();
            globalDifficulty = input.trim(); // Uppdatera svårighetsgraden
        }
        user.setDifficulty(globalDifficulty); // Tilldela den aktuella spelaren svårighetsgraden
        state = CONNECT_DATABASE;
        return Messages.CHOSEN_DIFFICULTY.format(user.getDifficulty());
    }



    private void handleConnectDatabase() {
        Database db = new Database(user.getCategory(), user.getDifficulty());
        this.questionsList = db.loadJSON().getJSONArray("results");
        state = ASK_QUESTIONS;
    }

    private String handleAskQuestions(String input) {
        if (currentQuestionIndex < questionsList.length()) {
            this.question = new Question(this.questionsList.getJSONObject(currentQuestionIndex));
            currentQuestionIndex++;
            state = CHECK_QUESTION;
            return Messages.QUESTION.format(currentQuestionIndex, question.getQuestion(), question.getOptions());
        } else {
            state = GAME_DONE;
            return Messages.GAME_DONE.format(user.getUsername(), this.score);
        }
    }

    private String handleCheckQuestions(String input) {
        if (input.equalsIgnoreCase(this.question.getCorrectAnswer())) {
            score++;
            user.setScore(score);
            state = ASK_QUESTIONS;
            return Messages.RIGHT_ANSWER.format();
        } else {
            state = ASK_QUESTIONS;
            return Messages.WRONG_ANSWER.format(this.question.getCorrectAnswer());
        }
    }

    private String handleGameDone() {
        resetGame();
        return Messages.GAME_DONE.format(user.getUsername(), score);
    }

    private void resetGame() {
        state = GET_CATEGORY; // Återgå till att fråga kategori
        currentQuestionIndex = 0;
        score = 0;
        questionsList = new JSONArray();
        question = null;
    }

    private boolean isValid(String input) {
        return input == null || input.trim().isEmpty();
    }
}
