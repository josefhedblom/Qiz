package Server;

public class User {
    private String username = ""; // Sätt ett standardvärde för att undvika null
    private String category = ""; // Standardvärde för category
    private String difficulty = ""; // Standardvärde för difficulty

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
