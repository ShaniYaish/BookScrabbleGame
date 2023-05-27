package tests;

import Model.Player;
import ServerSide.Tile;

import java.util.ArrayList;
import java.util.List;


class PlayerTest {

    public static void main(String[] args) {
        PlayerTest t = new PlayerTest();
        t.PlayerTest();
        System.out.println("testPlayer-done");
    }

    static Player player;

    private void PlayerTest() {
        player = new Player("Alice");
        update_score();
        removeWordFromTiles();
        add_tile();
        getWordTiles();
    }


    public static void update_score() {
        player.update_score(100);
        if(player.getScore() != 100){
            System.out.println("Failed");
        }
    }

    public static void add_tile() {
        player.add_tile();
        if(player.getPlayer_tiles().isEmpty()){
            System.out.println("Failed");
        }
    }

    public static void removeWordFromTiles() {
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
        //Player player= new Player("Alic");
        List<Tile> tiles = new ArrayList<>();
        tiles= player.getWordTiles(word);
        if(!(tiles.contains(tile1) && tiles.contains(tile2))){
            System.out.println("Failed");
        }
    }

}
