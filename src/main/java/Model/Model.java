package Model;

import ServerSide.Word;

public interface Model {
    public void start_game();

    public void end_turn(Word word,boolean chal);//try place word..update score..nextPlayer..complete 7 tiles from the bag

    public void pass_turn();// gets a one tile from the bag and nextPlayer
/*
    public void challenge(Player challenger);
*/
}
