package Model;

import ServerSide.Board;
import ServerSide.Tile;
import ServerSide.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HostModel implements Model {//hostmodele
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


    public List<Player> getPlayers() {
        return players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    @Override
    public void start_game() {
        this.board = Board.getBoard();
        this.bag = Tile.Bag.getBag();
        create_players_order();

        // Give each player 7 tiles from the bag
        for (int indexPlayer=0 ; indexPlayer < 4 ; indexPlayer++) {
            divide_tiles(indexPlayer);
        }
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
            bag.put(player.getPlayer_tiles().get(0));
            player.getPlayer_tiles().remove(0);
        }
    }

    public void divide_tiles(int indexOfPlayer) {
        int numOfTiles = players.get(indexOfPlayer).getPlayer_tiles().size();
        if (numOfTiles < 7) {
            for (int i = 0; i < 7 - numOfTiles; i++) {
                players.get(indexOfPlayer).add_tile();
            }
        }
    }


    @Override
    public void end_turn(Word word) {
       int score = board.tryPlaceWord(word);
       players.get(currentPlayerIndex).update_score(score);
       divide_tiles(currentPlayerIndex);

       setCurrentPlayerIndex((this.currentPlayerIndex+1)%3);
    }

    @Override
    public void pass_turn() {
        players.get(currentPlayerIndex).add_tile();
        setCurrentPlayerIndex((this.currentPlayerIndex+1)%3);
    }

    @Override
    public void challenge(Player challenger) {

    }
    //declared the winner
    public void end_game(){

    }
    //help function for end turn

    public void time_out(){}
    public void add_player(int gameId,Player player){}
}
