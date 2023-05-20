package Model;

import ServerSide.Board;
import ServerSide.Word;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GuestModel implements Model {

    Socket socket;
    private Board board;
    private String playerName;

    public GuestModel(String playerName) {
        this.board=Board.getBoard();
        this.playerName=playerName;
    }


    public void start_game(String host) {
        try {
            socket = new Socket(host, 3001);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // connect
        //"<guestName>,<function>,<data>,<data>,<data>.."
        String command = playerName+",connect,"+host+",3001";
        String response = sendCommand(command);

    }

    public String sendCommand(String command) {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            //"<guestName>,<function>,<data>,<data>,<data>.."
            writer.println(command);
            writer.flush();

            Scanner scanner = new Scanner(new InputStreamReader(socket.getInputStream()));
            String response = scanner.nextLine();

            writer.close();
            scanner.close();

            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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


}
