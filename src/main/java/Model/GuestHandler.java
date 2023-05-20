package Model;

import ServerSide.ClientHandler;
import ServerSide.Tile;
import ServerSide.Word;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class GuestHandler implements ClientHandler {
    HostModel host;

    public GuestHandler(HostModel host) {
        this.host = host;
    }

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        // implement
        Scanner scanner = new Scanner(new InputStreamReader(inFromclient));
        PrintWriter writer = new PrintWriter(outToClient);
        String[] command = scanner.nextLine().split(","); //"<guestName>,<function>,<data>,<data>,<data>.."
        String playerName = command[0];
        String function = command[1];

        if (function == "connect") { // selected guest mode
            Player newPlayer = new Player(playerName);
            newPlayer.setGuest(true);
            String guestHost = command[2]; // ip
            int port = Integer.parseInt(command[3]);
            try {
                newPlayer.setGuestSocket(new Socket(guestHost, port));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            host.addPlayer(newPlayer);
            return;
        }
        if (function == "endTurn") {
            //word, int row, int col, boolean vertical
            String word = command[2];
            int row = Integer.parseInt(command[3]);
            int col = Integer.parseInt(command[4]);
            boolean vertical = Boolean.parseBoolean(command[5]);
            boolean challenge = Boolean.parseBoolean(command[6]);

            List<Tile> wordTiles = host.getTilesFromPlayerBag(playerName, word);
            boolean isTurnSuccess = host.end_turn(new Word(wordTiles.toArray(new Tile[0]), row, col, vertical), challenge);
            if (!isTurnSuccess) {
                writer.println(false);
                writer.flush();
                return;
            }
            host.removeTilesFromPlayerBag(playerName, word);
            writer.println(true);
            writer.flush();
            return;
        }

        if (function == "passTurn") {
            host.pass_turn();
            Player player = host.getPlayerByName(playerName);
            player.getPlayer_tiles();
           // writer.println(tile.getLetter());//send all tiles
            writer.flush();
        }
        scanner.close();
        writer.close();
    }

    @Override
    public void close() {

    }
}
