package Model;

import ServerSide.Tile;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Tile> player_tiles;
    private String player_name;
    private int score;
    private boolean isGuest;
    private Socket guestSocket;


    public Player(String name) {
        this.player_tiles = new ArrayList<>();
        this.player_name = name;
        this.isGuest = false;
        this.score = 0;
    }


    public void update_score(int score) {
        this.score += score;
    }

    public void add_tile() {
        Tile tile = Tile.Bag.getBag().getRand();
        this.player_tiles.add(tile);
    }

    public List<Tile> getPlayer_tiles() {
        return player_tiles;
    }

    //find tile in player_tiles remove tile from player_tiles
    public void removeWordFromTiles(String word) {
        if (word == null) return;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            for(Tile tile :player_tiles){
                if(tile.getLetter() == c){
                    player_tiles.remove(tile);
                }
            }
        }
    }
    public List<Tile> getWordTiles(String word) {
        if (word == null) return null;
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Tile tile= Tile.Bag.getBag().getTile(c);
            tiles.add(tile);
        }
        return tiles;
    }


    public String getPlayer_name() {
        return player_name;
    }

    public int getScore() {
        return score;
    }


    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

    public Socket getGuestSocket() {
        return guestSocket;
    }

    public void setGuestSocket(Socket guestSocket) {
        this.guestSocket = guestSocket;
    }
}
