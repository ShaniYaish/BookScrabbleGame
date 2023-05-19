package Model;

import ServerSide.Word;

public interface Model {

    public void start_game();//build the board,give one give for everyone 7 tiles , build the list of the players in order.
    public void end_turn(Word word);//try place word..update score..nextPlayer..complete 7 tiles from the bag

    public void pass_turn();// gets a one tile from the bag and nextPlayer
/*
    public void challenge(Player challenger);
*/
}
