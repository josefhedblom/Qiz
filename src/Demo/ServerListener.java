package Demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONArray;
import org.json.JSONObject;
import Server.Database;

public class ServerListener {
    public ServerListener() {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Väntar på spelare...");


            Socket socket1 = serverSocket.accept();
            System.out.println("Spelare 1 ansluten!");


            Socket socket2 = serverSocket.accept();
            System.out.println("Spelare 2 ansluten!");


            PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);
            PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);

            out1.println("Båda spelare är anslutna! Spelet börjar nu.");
            out2.println("Båda spelare är anslutna! Spelet börjar nu.");


            BufferedReader in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));


            System.out.println("Väntar på kategori från spelare 1...");
            out1.println("Välj en kategori: Film, Musik, TV, etc.");
            out1.flush();

            String categoryInput = in1.readLine();  // Vänta på att spelare 1 skickar sin kategori
            System.out.println("Kategori mottagen från spelare 1: " + categoryInput);

            // Vänta på svårighetsnivå från spelare 1
            System.out.println("Väntar på svårighetsnivå från spelare 1...");
            out1.println("Välj svårighetsnivå: Lätt, Medium, Svår");
            out1.flush();

            String difficultyInput = in1.readLine();
            System.out.println("Svårighetsnivå mottagen från spelare 1: " + difficultyInput);

            // Skicka den valda kategorin och svårighetsnivån till spelare 2
            out2.println("Spelare 2, nu spelar du samma omgång som spelare 1...");
            out2.println("Kategori: " + categoryInput + ", Nivå: " + difficultyInput);

            // Hämta frågorna från databasen
            Database db = new Database(categoryInput, difficultyInput);
            JSONObject questions = db.loadJSON();


            if (questions.has("results")) {
                JSONArray questionArray = questions.getJSONArray("results");

                // Frågor för spelare 1
                askQuestions(out1, in1, questionArray);

                // Frågor för spelare 2
                BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
                askQuestions(out2, in2, questionArray);

                out1.println("Nu spelar du samma omgång som spelare 2...");
                askQuestions(out1, in1, questionArray);
            } else {
                out1.println("Fel: JSON-filen saknar 'questions' nyckel.");
                out2.println("Fel: JSON-filen saknar 'questions' nyckel.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServerListener();
    }

    private void askQuestions(PrintWriter out, BufferedReader in, JSONArray questionArray) throws IOException {
        for (int i = 0; i < questionArray.length(); i++) {
            JSONObject question = questionArray.getJSONObject(i);
            String questionText = question.getString("question");
            JSONArray options = question.getJSONArray("options");
            String correctAnswer = question.getString("correct_answer");

            out.println("Fråga: " + questionText);
            out.flush();

            out.println("Alternativ:");
            for (int j = 0; j < options.length(); j++) {
                out.println((j + 1) + ". " + options.getString(j));
            }
            out.flush();

            // Vänta på spelarens svar efter att både frågan och alternativen har skickats
            System.out.println("Väntar på spelarens svar...");
            String answer = in.readLine();  // Här väntar servern på spelarens svar
            System.out.println("Spelare svarade: " + answer);  // Debug: Vad svarade spelaren?

            // Kontrollera om svaret är korrekt
            if (answer != null && answer.equalsIgnoreCase(correctAnswer)) {
                out.println("Rätt svar!");
            } else {
                out.println("Fel svar! Rätt svar var: " + correctAnswer);
            }
            out.flush();
        }
    }




}
