package Model;

import ServerSide.Board;
import ServerSide.MyServer;
import ServerSide.Word;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class GuestModel implements Model {


    private Socket Socket;
    private MyServer guestServer;
    private Board board;
    private String playerName;
    private GameState gameState;
   // private String serverIP;


    public GuestModel(String playerName) {
        this.board=Board.getBoard();
        this.playerName=playerName;
        this.gameState= null;
       // this.serverIP="localhost";

        guestServer = new MyServer(3002, new HostHandler(this));
        guestServer.start();
    }

    //send orders to the hostServer
    public String sendCommand(String command) {
        try {
            PrintWriter writer = new PrintWriter(Socket.getOutputStream());
            //"<guestName>,<function>,<data>,<data>,<data>.."
            writer.println(command);
            writer.flush();

            Scanner scanner = new Scanner(new InputStreamReader(Socket.getInputStream()));
            String response = scanner.nextLine();

            writer.close();
            scanner.close();

            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void start_game(String host) {
        try {
            Socket = new Socket(host, 3001);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // connect
        //"<guestName>,<function>,<data>,<data>,<data>.."
        String command = playerName+",connect,"+host+",3001";
        String response = sendCommand(command);

    }
    @Override
    public boolean end_turn(Word word, boolean challenge) {
        // endTurn
        String command = playerName+","+"endTurn,"+word+","+word.getRow()+","+word.getCol()+","+word.isVertical()+","+challenge;
        String response = sendCommand(command);
        boolean isOK = Boolean.parseBoolean(response);
        return isOK;
    }

    @Override
    public void pass_turn() {
        //passTurn
        String command = playerName+","+"passTurn";
        String response = sendCommand(command);
    }

    public void updateGameState(String currentNamePlayer, Map<String,Integer> playerScore, String lastWord){
        //connection to the view
      //  GameState gameState = new GameState();

    //    Word word = new Word(lastWord);

       // this.board.insertWord(word);
    }

    //connection to the hostServer to listen to updates
    /*
    public void connectToHostServer(String serverAddress, int serverPort) {
        try {
            hostSocket = new Socket(serverAddress, serverPort);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    public String receiveDataFromHostServer() {
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

     */


}
