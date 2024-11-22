package Server;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private static final Map<String, String> CATEGORY_PATHS = new HashMap<>();

    static {
        CATEGORY_PATHS.put("Brädspel", "src/DB/boardgame");
        CATEGORY_PATHS.put("Film", "src/DB/film");
        CATEGORY_PATHS.put("Musik", "src/DB/music");
        CATEGORY_PATHS.put("TV", "src/DB/tv");
        CATEGORY_PATHS.put("TV Spel", "src/DB/videogame");
    }

    // public static getCategories hämta alla kategorier


    public static String getCategory(String category) {
        String path = CATEGORY_PATHS.get(category.trim());
        if (path == null) {
            throw new IllegalArgumentException("Ogiltig kategori: " + category);
        }
        return path;
    }
}