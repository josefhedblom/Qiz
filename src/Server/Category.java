package Server;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Category {
    private static final String BASE_PATH = "src" + File.separator + "DB" + File.separator;
    private static final Map<String, String> CATEGORY_PATHS = new HashMap<>();

    static {
        addCategories("Brädspel", "Film", "Musik", "TV", "TV Spel");
    }

    private static void addCategories(String... categories) {
        for (String category : categories) {
            String subPath = category.toLowerCase().replace(" ", "");
            CATEGORY_PATHS.put(category, BASE_PATH + subPath);
        }
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
