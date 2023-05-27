package Model;

import ServerSide.*;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class HostModel implements Model {
    private List<Player> players;
    private Board board;
    private Tile.Bag bag;
    private int currentPlayerIndex;

    //client of server - members
    private Socket bookScrabbleSocket;
    private MyServer bookScrabbleServer;


    //server of guests - members
    private MyServer hostServer;


    public HostModel() {
        bookScrabbleServer = new MyServer(3000, new BookScrabbleHandler());
        bookScrabbleServer.start();
        connectToBookScrabbleServer("localhost", 3000);

        hostServer = new MyServer(3001, new GuestHandler(this));
        hostServer.start();


        this.players = new ArrayList<>();
        this.board = null;
        this.bag = null;
        this.currentPlayerIndex = 0;
    }


    public void connectToBookScrabbleServer(String serverAddress, int serverPort) {
        //Connect to the server
        try {
            bookScrabbleSocket = new Socket(serverAddress, serverPort);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayerByName(String playerName) {
        Player player = players.stream()
                .filter(item -> item.getPlayer_name().equals(playerName))
                .collect(Collectors.toList()).get(0);
        return player;
    }

    public List<Tile> getTilesFromPlayerBag(String playerName, String word) {
        Player player = getPlayerByName(playerName);
        return player.getWordTiles(word);
    }

    public void removeTilesFromPlayerBag(String playerName, String word) {
        Player player = getPlayerByName(playerName);
        player.removeWordFromTiles(word);
    }

    public void sendDataToBookScrabbleServer(String message) {
        try {
            PrintWriter outToServer = new PrintWriter(bookScrabbleSocket.getOutputStream());
            outToServer.println(message);
            outToServer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String receiveDataFromBookScrabbleServer() {
        try {
            Scanner in = new Scanner(bookScrabbleSocket.getInputStream());
            String response = in.next();
            in.close();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void closeConnection() {
        try {
            bookScrabbleSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void start_game() {
        this.board = Board.getBoard();
        this.bag = Tile.Bag.getBag();
        create_players_order();

        // Give each player 7 tiles from the bag
        for (int indexPlayer = 0; indexPlayer < 4; indexPlayer++) {
            divide_tiles(indexPlayer);
        }
    }

    public void addPlayer(Player player) { //(button + / guest connected)
        players.add(player);
    }

    public void create_players_order() {
        for (Player player : players) {
            player.add_tile();
        }
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Character.compare(p1.getPlayer_tiles().get(0).letter, p2.getPlayer_tiles().get(0).letter);
            }
        });
        for (Player player : players) {
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
    public boolean end_turn(Word word, boolean challenge) {
        String message = createMessage(word, challenge);
        sendDataToBookScrabbleServer(message);
        String result = receiveDataFromBookScrabbleServer();
        boolean isInDictionary = result == "true" ? true : false;
        if (!isInDictionary) {
            //return message try again
            //return the tiles for the first position
            return false;
        }
        int score = board.tryPlaceWord(word);
        if (score == 0) {
            //send message try again
            return false;
        }

        // call notifyState
        notifyState(word);

        players.get(currentPlayerIndex).update_score(score);
        divide_tiles(currentPlayerIndex);
        setCurrentPlayerIndex((this.currentPlayerIndex + 1) % 3);
        if (bag.size() == 0) {
            end_game();
        }
        return true;
    }

    @Override
    public void pass_turn() {
        players.get(currentPlayerIndex).add_tile();
        setCurrentPlayerIndex((this.currentPlayerIndex + 1) % 3);
    }

    public void notifyState(Word word) {
        //the host need to notify to the guests for updates after every turn
        GameState gameState=new GameState(players,players.get(currentPlayerIndex).getPlayer_name(),word);
        //loop of players socket to send them massege
        for(Player player : players)
        {

        }
        //"<function>,<data>,<data>,<data>.."
        //"updateGameState,gameState.toString()"

        // loop on Guest sockets
        for(Player p :players){
            if(p.isGuest()){
                //p.getGuestSocket()
            }
        }

    }


    public void updateGameState(){


    }


    //declared the winner
    public void end_game() {
        Collections.sort(players, (p1, p2) -> p2.getScore() - p1.getScore());
    }



    public String createMessage(Word word, boolean challenge) {
        String message = challenge ? "C," : "Q,";
        String w = "";

        for (int i = 0; i < word.getTiles().length; i++) {
            w += word.getTiles()[i].letter;
        }

        //add the name of the books

        message += "book1,book2,book3," + w;

        return message;
    }
}
