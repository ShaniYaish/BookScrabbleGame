package Model;

import ServerSide.Word;

import java.net.Socket;

public class GuestModel implements Model {

    Socket socket;

    GuestModel(){
        //update host that the guest is connected (send the name)

    }
    @Override
    public void start_game() {
        // send

    }

    @Override
    public void end_turn(Word word, boolean chal) {

    }

    @Override
    public void pass_turn() {

    }

    /*
    @Override
    public void challenge(Player challenger) {

    }*/

    public void join_game() {

    }


}
