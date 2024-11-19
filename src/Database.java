
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String BASE_PATH = "src" + File.separator + "DB";
    private final String category;
    private final String difficulty;

    public Database(String category,String difficulty) {
        this.category = Category.getCategory(category);
        this.difficulty = Difficulty.getDifficulty(difficulty);
    }

    public static List<String> loadAllJSON(){
        List<String> allJSON = new ArrayList<>();
        try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(BASE_PATH), "*json")) {
            for (Path file : directoryStream) {
                allJSON.add(file.getFileName().toString());
            }
        }catch (IOException e){
            throw new RuntimeException("Kunde inte läsa filer",e);
        }
        return allJSON;
    }

    public JSONObject loadJSON() {
        String filePath = buildFilePath(category, difficulty);
        try {
            return new JSONObject(new String(Files.readAllBytes(Paths.get(filePath))));
        } catch (IOException e) {
            throw new RuntimeException("Kunde inte läsa filen: " + filePath, e);
        }
    }

    public static String buildFilePath(String category, String difficulty) {
        return category + "_" + difficulty + ".json";
    }
}
