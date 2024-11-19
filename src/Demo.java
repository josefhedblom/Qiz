import org.json.JSONArray;
import org.json.JSONObject;

public class Demo {
    public static void main(String[] args) {
        String category = "Film";
        String difficulty = "Lätt";
        Database database = new Database(category, difficulty);
        JSONArray results = database.loadJSON().getJSONArray("results");
        System.out.println(results);
    }
}
