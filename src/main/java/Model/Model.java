package Model;

import ServerSide.Word;

public interface Model {

    public boolean end_turn(Word word, boolean chal);

    public void pass_turn();// gets a one tile from the bag and nextPlayer

}
