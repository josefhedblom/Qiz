package Server;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Category {
    private static final String BASE_PATH = "src" + File.separator + "DB" + File.separator;
    private static final Map<String, String> CATEGORY_PATHS = new HashMap<>();

    static {
        CATEGORY_PATHS.put("Brädspel", "boardgame");
        CATEGORY_PATHS.put("Film", "film");
        CATEGORY_PATHS.put("Musik", "music");
        CATEGORY_PATHS.put("TV", "tv");
        CATEGORY_PATHS.put("TV Spel", "videogame");
    }

    public static String getCategory(String category) {
        String path = BASE_PATH + CATEGORY_PATHS.get(category.trim());
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Ogiltig kategori: " + category);
        }
        return path;
    }
}
