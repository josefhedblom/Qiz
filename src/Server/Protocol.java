package Server;

import org.json.JSONArray;

public class Protocol {
    private static final int GET_USERNAME = 0;
    private static final int GET_CATEGORY = 1;
    private static final int GET_DIFFICULTY = 2;
    private static final int ASK_QUESTIONS = 3;
    private static final int GAME_OVER = 4;

    private int state = GET_USERNAME;
    private String username;
    private String category;
    private String difficulty;
    private JSONArray questions;
    private int currentQuestionIndex = 0;

    public String processInput(String input) {

        switch (state) {
            case GET_USERNAME:
                username = input;
                state = GET_CATEGORY;
                return "Hej " + username + "! Välj en kategori: Film | Musik | TV | Brädspel";

            case GET_CATEGORY:
                if (input.trim().isEmpty()) {
                    return "Ange ditt categories:";
                }
                category = input.trim();
                state = GET_DIFFICULTY;
                return "Hej " + category;

            case GET_DIFFICULTY:
                if (input == null || input.trim().isEmpty()) {
                    return "Ange ditt svårighetsgrad:";
                }
                category = input.trim();
                state = ASK_QUESTIONS;
                return "Hej " + category;

            case ASK_QUESTIONS:
                if (input == null || input.trim().isEmpty()) {
                    return "Ange ditt användarnamn:";
                }
                category = input.trim();
                state = GAME_OVER;
                return "Hej " + category;

            case GAME_OVER:

            // gör någoting
            default:
                return "Ogiltigt tillstånd. Starta om spelet.";
        }
    }
}