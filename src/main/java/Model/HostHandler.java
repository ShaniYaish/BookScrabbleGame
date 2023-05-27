package Model;

import ServerSide.ClientHandler;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class HostHandler implements ClientHandler {

    GameState gameState;

    public HostHandler() {
    }
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
// implement
        Scanner scanner = new Scanner(new InputStreamReader(inFromclient));
        PrintWriter writer = new PrintWriter(outToClient);
        String[] command = scanner.nextLine().split("#");

        //"<currentPlayerName>#<playerName1:score1>,<playerName2:score2>#<lastWord, row, col, vertical>"
        String currentPlayerName = command[0]; //notify to viewModel about the current player
        String score = command[1];
        String lastAddedWord = command[2];

        String[] playerScore = score.split(",");
        for(int i=0 ; i< playerScore.length ; i++)
        {
            String[] updatePlayer= playerScore[i].split(":");
        }
    }

    @Override
    public void close() {

    }
}
