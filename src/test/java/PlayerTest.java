import Model.Player;
import ServerSide.Tile;

import java.util.ArrayList;
import java.util.List;


class PlayerTest {

    public static void update_score() {
        Player player= new Player("Alice");
        player.update_score(100);
        if(player.getScore() != 100){
            System.out.println("Failed");
        }
    }

    public static void add_tile() {
        Player player= new Player("Alice");
        player.add_tile();
        if(player.getPlayer_tiles().isEmpty()){
            System.out.println("Failed");
        }
    }

    public static void removeWordFromTiles() {
        Player player= new Player("Alice");
        String word= "AB";
        player.getPlayer_tiles().add(Tile.Bag.getBag().getTile('A'));
        player.getPlayer_tiles().add(Tile.Bag.getBag().getTile('B'));
        player.removeWordFromTiles(word);
        if(!player.getPlayer_tiles().isEmpty()){
            System.out.println("Failed");
        }

    }


    public static void getWordTiles() {
        String word= "AB";
        Tile tile1= Tile.Bag.getBag().getTile('A');
        Tile tile2= Tile.Bag.getBag().getTile('B');
        Player player= new Player("Alic");
        List<Tile> tiles = new ArrayList<>();
        tiles= player.getWordTiles(word);
        if(!(tiles.contains(tile1) && tiles.contains(tile2))){
            System.out.println("Failed");
        }
    }

    public static void main(String[] args) {
        update_score();
        add_tile();
        removeWordFromTiles();
        getWordTiles();
        System.out.println("testPlayer-done");
    }
}