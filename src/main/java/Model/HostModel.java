package Model;

import ServerSide.Board;
import ServerSide.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HostModel implements Model {
    private List<Player> players;
    private Board board;
    private Tile.Bag bag;
    private int currentPlayerIndex;
    public HostModel(){
        this.players = new ArrayList<>();
        this.board = null;
        this.bag = null;
        this.currentPlayerIndex = 0;
    }
    @Override
    public void start_game() {
        this.board = Board.getBoard();
        this.bag = Tile.Bag.getBag();
        create_players_order();

        /*
        // Give each player 7 tiles from the bag
        for (Player player : players) {
            List<Tile> tiles = bag.drawTiles(7);
            player.addTiles(tiles);
        }
        */

    }
    public void create_players_order(){
        for (Player player : players) {
            player.add_tile();
        }
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Character.compare(p1.getPlayer_tiles().get(0).letter, p2.getPlayer_tiles().get(0).letter);
            }
        });
        for (Player player : players){
            Tile.Bag.getBag().put(player.getPlayer_tiles().get(0));
            player.getPlayer_tiles().remove(0);
        }
    }
    public void end_turn() {

    }

    @Override
    public void pass_turn() {

    }

    @Override
    public void go_back() {

    }

    @Override
    public void challenge(Player challenger) {

    }
    //declared the winner
    public void end_game(){

    }
    //help function for end turn
    public int update_score(){
        return 0;
    }
    public void time_out(){}
    public void add_player(int gameId,Player player){}
}
