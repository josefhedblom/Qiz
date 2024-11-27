package Server;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.*;

public class ServerLogic {
    private Socket socket1, socket2;
    private PrintWriter out1, out2;
    private BufferedReader in1, in2;
    private User player1, player2;
    private int round = 1;

    public ServerLogic(Socket socket1, Socket socket2) throws IOException {
        this.socket1 = socket1;
        this.socket2 = socket2;
        out1 = new PrintWriter(socket1.getOutputStream(), true);
        out2 = new PrintWriter(socket2.getOutputStream(), true);
        in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
        in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));

        player1 = new User();
        player2 = new User();
    }

    public void startGame() throws IOException {
        while (round <= 3) {
            // Round 1 - Player 1 chooses category and difficulty
            startRound(player1, out1, in1);

            // Round 1 - Player 2 plays same round
            startRound(player2, out2, in2);

            // New round: Player 2 chooses category and difficulty
            startRound(player2, out2, in2);

            // Round 2 - Player 1 plays same round
            startRound(player1, out1, in1);

            round++;
        }

        // End game logic, perhaps calculate score or end message
        out1.println("Spelet är slut! Tack för att du spelade.");
        out2.println("Spelet är slut! Tack för att du spelade.");
    }

    private void startRound(User currentPlayer, PrintWriter out, BufferedReader in) throws IOException {
        out.println("Vänligen välj kategori: ");
        currentPlayer.setCategory(in.readLine());

        out.println("Vänligen välj svårighetsnivå: ");
        currentPlayer.setDifficulty(in.readLine());

        // Hämta frågor från databasen (se kod från tidigare för att ladda frågor)
        Database db = new Database(currentPlayer.getCategory(), currentPlayer.getDifficulty());
        JSONObject questionsJson = db.loadJSON();

        // Skicka frågorna till spelaren
        sendQuestionsToPlayer(questionsJson, out, in);

        // Vänta på att spelaren svarar och ge feedback
    }

    private void sendQuestionsToPlayer(JSONObject questionsJson, PrintWriter out, BufferedReader in) throws IOException {
        JSONArray questions = questionsJson.getJSONArray("questions");
        for (int i = 0; i < questions.length(); i++) {
            JSONObject questionJson = questions.getJSONObject(i);
            out.println("Fråga: " + questionJson.getString("question"));
            out.println("Alternativ: ");
            JSONArray options = questionJson.getJSONArray("options");
            for (int j = 0; j < options.length(); j++) {
                out.println((j + 1) + ": " + options.getString(j));
            }

            String answer = in.readLine(); // Vänta på att spelaren svarar
            // Kontrollera om svaret är korrekt och ge feedback
            checkAnswer(questionJson, answer, out);
        }
    }

    private void checkAnswer(JSONObject questionJson, String answer, PrintWriter out) {
        String correctAnswer = questionJson.getString("correct_answer");
        if (answer.equals(correctAnswer)) {
            out.println("Korrekt svar!");
        } else {
            out.println("Fel svar. Rätt svar var: " + correctAnswer);
        }
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            Socket socket1 = serverSocket.accept();
            Socket socket2 = serverSocket.accept();

            ServerLogic serverLogic = new ServerLogic(socket1, socket2);
            serverLogic.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
