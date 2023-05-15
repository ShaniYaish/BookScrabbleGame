package Model;

import ServerSide.Tile;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Tile> player_tiles;
    private String player_name;
    private int score;

    public Player(String name){
        this.player_tiles= new ArrayList<>();
        this.player_name= name;
        this.score=0;
    }

    public void add_tile(){
        Tile tile= Tile.Bag.getBag().getRand();
        this.player_tiles.add(tile);
    }

    public List<Tile> getPlayer_tiles() {
        return player_tiles;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public int getScore() {
        return score;
    }
}
