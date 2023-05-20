package Model;

import ServerSide.Word;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GuestModel implements Model {

    Socket socket;


    GuestModel() {
        //update host that the guest is connected (send the name)

    }

    public void start_game(String host) {
        try {
            socket = new Socket(host, 3001);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // connect
        String command = "";
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
    public boolean end_turn(Word word, boolean chal) {
        // endTurn
        String command = "";
        String response = sendCommand(command);
        boolean isOK = Boolean.parseBoolean(response);
        return isOK;
    }

    @Override
    public void pass_turn() {
        //passTurn
        String command = "";
        String response = sendCommand(command);
    }


}
