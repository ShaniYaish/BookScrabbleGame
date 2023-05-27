package Model;

import ServerSide.ClientHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HostHandler implements ClientHandler {

    GuestModel guestModel;
    Scanner scanner;
    PrintWriter writer;

    public HostHandler(GuestModel guestModel) {
        this.guestModel= guestModel;
    }
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
// implement
        scanner = new Scanner(new InputStreamReader(inFromclient));
        writer = new PrintWriter(outToClient);
        String[] command = scanner.nextLine().split("#");
        Map<String,Integer> playerScoreMap = new HashMap<>();
        //"<currentPlayerName>#<playerName1:score1>,<playerName2:score2>#<lastWord, row, col, vertical>"
        String currentPlayerName = command[0]; //notify to viewModel about the current player
        String score = command[1];
        String lastAddedWord = command[2];

        String[] playerScore = score.split(",");
        for(int i=0 ; i< playerScore.length ; i++)
        {
            String[] updatePlayer= playerScore[i].split(":");
            playerScoreMap.put(updatePlayer[0], Integer.parseInt(updatePlayer[1]));
        }
        guestModel.updateGameState(currentPlayerName, playerScoreMap,lastAddedWord);

        //scanner.close();
        //writer.close();
    }

    @Override
    public void close() {
        scanner.close();
        writer.close();
    }
}
