package Server;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Protocol {
    private static final int GET_USERNAME = 0;
    private static final int GET_CATEGORY = 1;
    private static final int GET_DIFFICULTY = 2;
    private static final int CONNECT_DATABASE = 3;
    private static final int ASK_QUESTIONS = 4;
    private static final int CHECK_QUESTION = 5;
    private static final int GAME_DONE = 7;

    private User user = new User();

    private int state = GET_USERNAME;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private JSONArray questionsList = new JSONArray();
    private Question question;

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
        if (isValid(input)) return Messages.ENTER_USERNAME.format();
        user.setUsername(input);
        state = GET_CATEGORY;
        return Messages.GREET_USER.format(user.getUsername());
    }

    private String handleGetCategory(String input) {
        // Om svårighetsgraden redan är satt (för Spelare 2), hoppa över inmatning
        if (user.getDifficulty() != null && !user.getDifficulty().isEmpty()) {
            state = CONNECT_DATABASE;  // Gå vidare till att ansluta till databasen
            return Messages.CHOSEN_DIFFICULTY.format(user.getDifficulty()); // Visa vald svårighetsgrad
        }

        // Om svårighetsgraden inte är vald, be användaren att ange den
        if (isValid(input)) return Messages.ENTER_DIFFICULTY.format();

        // Sätt svårighetsgraden om det är ett giltigt val
        user.setDifficulty(input);
        state = CONNECT_DATABASE;  // Gå vidare till att ansluta till databasen
        return Messages.CHOSEN_DIFFICULTY.format(user.getDifficulty()); // Bekräfta vald svårighetsgrad
    }

    private String handleGetDifficulty(String input) {
        // För Spelare 2, hoppa över svårighetsgrad-valet om det finns sparad data
        if (!user.getDifficulty().isEmpty()) {
            state = CONNECT_DATABASE;
            return Messages.CHOSEN_DIFFICULTY.format(user.getDifficulty());
        }else {
            if (isValid(input)) return Messages.ENTER_DIFFICULTY.format();
            user.setDifficulty(input);
            state = CONNECT_DATABASE;
            return Messages.CHOSEN_DIFFICULTY.format(user.getDifficulty());
        }
    }

    private void handleConnectDatabase() {
        Database db = new Database(user.getCategory(), user.getDifficulty());
        this.questionsList = db.loadJSON().getJSONArray("results");
        state = ASK_QUESTIONS;
    }

    private String handleAskQuestions(String input) {
        if (user.getCategory() != null && !user.getCategory().isEmpty()) {
            state = GET_DIFFICULTY;
            return Messages.CHOSEN_CATEGORY.format(user.getCategory());
        }

        if (isValid(input)) return Messages.ENTER_CATEGORY.format();
        user.setCategory(input);
        state = GET_DIFFICULTY;
        return Messages.CHOSEN_CATEGORY.format(user.getCategory());
    }

    private String handleCheckQuestions(String input) {
        if (user.getDifficulty() != null && !user.getDifficulty().isEmpty()) {
            state = CONNECT_DATABASE;
            return Messages.CHOSEN_DIFFICULTY.format(user.getDifficulty());
        }

        if (isValid(input)) return Messages.ENTER_DIFFICULTY.format();
        user.setDifficulty(input);
        state = CONNECT_DATABASE;
        return Messages.CHOSEN_DIFFICULTY.format(user.getDifficulty());
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

    // Metod för att spara användardata till en JSON-fil
    private void saveUserDataToJsonFile() {
        JSONObject userData = new JSONObject();
        userData.put("username", user.getUsername());
        userData.put("category", user.getCategory());
        userData.put("difficulty", user.getDifficulty());

        try (FileWriter file = new FileWriter("src/DB/user_data.json")) {
            file.write(userData.toString(4)); // Skriver JSON-data till fil med indentering för läsbarhet
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metod för att läsa användardata för Spelare 2 från JSON-filen
    private void loadUserDataForPlayer2() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/DB/user_data.json"))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            JSONObject userData = new JSONObject(content.toString());

            // Extrahera kategori och svårighetsgrad för Spelare 2
            String categoryForPlayer2 = userData.getString("category");
            String difficultyForPlayer2 = userData.getString("difficulty");

            // Sätt kategori och svårighetsgrad till Spelare 2
            user.setCategory(categoryForPlayer2);
            user.setDifficulty(difficultyForPlayer2);

        } catch (IOException e) {
            System.err.println("Kunde inte läsa från filen: " + e.getMessage());
        }
    }

    private void handleGameDoneForPlayer1() {
        // Spara data för Spelare 1 till fil
        saveUserDataToJsonFile();

        // Återställ spelet för Spelare 2
        resetGameForPlayer2();
    }

    // När Spelare 2 börjar spela, laddar vi in Spelare 1:s val
    private void resetGameForPlayer2() {
        // Läs in kategori och svårighetsgrad för Spelare 2 från fil
        loadUserDataForPlayer2();

        // Starta spelet för Spelare 2
        System.out.println("Spelare 2 kan nu spela med kategori: " + user.getCategory() + " och svårighetsgrad: " + user.getDifficulty());
    }
}
