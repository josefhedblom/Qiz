package Server;

import java.util.HashMap;

public class AddPlayerId {
    private HashMap<String, Player> players = new HashMap<String, Player>();

    public void addPlayer(String id, String name) {
        players.put(id, new Player(id, name));
    }
    public Player getPlayer(String id) {
        return players.get(id);
    }
    public void removePlayer(String id) {
        players.remove(id);
    }
}
