package Model;

import ServerSide.Board;
import ServerSide.MyServer;
import ServerSide.Tile;
import ServerSide.Word;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HostModel implements Model {
    private List<Player> players;
    private Board board;
    private Tile.Bag bag;
    private int currentPlayerIndex;
    private MyServer host_server;
    private Socket socket;
    private String my_IP;
    private int port;

    public HostModel(int port, String IP){
        this.players = new ArrayList<>();
        this.board = null;
        this.bag = null;
        this.currentPlayerIndex = 0;
    }
    public void run_socket(){
        //open the socket, connect to the port from myserver last semester
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
        while (bag.size() !=0 ){
            int score = board.tryPlaceWord(word);
            if(score==0){
                //send message try again
                //return the tiles for the first position
            }
            else {
                //handelClient func(?)
                //the host need to notify to the guests to put the word on the bord
                players.get(currentPlayerIndex).update_score(score);
                divide_tiles(currentPlayerIndex);
                setCurrentPlayerIndex((this.currentPlayerIndex+1)%3);
            }
        }
        end_game();
    }

    @Override
    public void pass_turn() {
        players.get(currentPlayerIndex).add_tile();
        setCurrentPlayerIndex((this.currentPlayerIndex+1)%3);
    }
    /*
    @Override
    public void challenge(Player challenger) {
    }*/
    //declared the winner
    public void end_game(){
        Collections.sort(players, (p1,p2)-> p2.getScore()-p1.getScore());
    }

    public void time_out(){
        //is it func of the view layer ?
    }
    public void add_player(int gameId,Player player){
        players.add(player);
    }
}
