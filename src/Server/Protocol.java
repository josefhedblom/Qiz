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
                if (input == null || input.trim().isEmpty()) {
                return "Ange ett användarnamn:";
            }
            username = input.trim();
            state = GET_CATEGORY;
            return "Hej " + username + "! Välj en kategori: Film | Musik | TV | Brädspel";

            case GET_CATEGORY:
                if (input == null || input.trim().isEmpty()) {
                return "Ange en kategori: Film | Musik | TV | Brädspel";
            }
            category = input.trim();
            state = GET_DIFFICULTY;
            return "Du valde kategorin " + category + ". Ange svårighetsgrad: Lätt | Medium | Svår";

            case GET_DIFFICULTY:
                if (input == null || input.trim().isEmpty()) {
                return "Ange en svårighetsgrad: Lätt | Medium | Svår";
            }
            difficulty = input.trim();
            state = ASK_QUESTIONS;
            return "Svårighetsgrad vald: " + difficulty + ". Startar frågor...";

            case ASK_QUESTIONS:
                if (input == null || input.trim().isEmpty()) {
                return "Vänligen svara på frågan.";
            }
            // Placeholder-logik för frågor
            state = GAME_OVER;
            return "Tack för ditt svar! Spelet är över.";

            case GAME_OVER:
                return "Spelet är slut. Tack för att du spelade, " + username + "!";

            default:
                return "Ogiltigt tillstånd. Starta om spelet.";
        }
    }
}