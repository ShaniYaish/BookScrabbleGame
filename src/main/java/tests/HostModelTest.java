package tests;

import Model.HostModel;
import Model.Player;
import ServerSide.Tile;
import ServerSide.Word;

import java.util.ArrayList;
import java.util.List;

public class HostModelTest {
    public static void main(String[] args) {
        HostModelTest t = new HostModelTest();
        t.HostModelTest();
        System.out.println("HostModelTest-done");
    }


    static HostModel hostModel = new HostModel();

    public void HostModelTest() {

        // Add some players
        Player player1 = new Player("Alice" , true);
        Player player2 = new Player("Bob" , true);
        hostModel.getPlayers().add(player1);
        hostModel.getPlayers().add(player2);

        getPlayerByName();
        getTilesFromPlayerBag();
        createMessage();
        //sendDataToBookScrabbleServer();
        //receiveDataFromBookScrabbleServer();
        divide_tiles();
        pass_turn();
        end_game();

        closeConnection();
    }


    public void getPlayerByName() {
        if(hostModel.getPlayerByName("Alice") == null)
            System.out.println("HostModelTest.getPlayerByName: failed");
    }


    public void getTilesFromPlayerBag() {
        List<Tile> tiles = hostModel.getTilesFromPlayerBag("Bob", "AB");
        if(tiles == null)
            System.out.println("HostModelTest.getTilesFromPlayerBag: failed");
    }

    public void createMessage(){
        Tile tile1= Tile.Bag.getBag().getTile('D');
        Tile tile2= Tile.Bag.getBag().getTile('O');
        Tile tile3= Tile.Bag.getBag().getTile('G');
        Tile[] tiles = new Tile[]{tile1 , tile2 ,tile3};

        Word word = new Word(tiles , 1 ,1 ,true);
        String message = hostModel.createMessage(word ,true);
        if(!message.equals("C,book1,book2,book3,DOG"))
            System.out.println("HostModelTest.createMessage: failed");
    }

    public void sendDataToBookScrabbleServer(){
        hostModel.sendDataToBookScrabbleServer("C,book1,book2,book3,DOG");

    }

    public void receiveDataFromBookScrabbleServer(){
        String bool = hostModel.receiveDataFromBookScrabbleServer();
        if(bool.equals(true))
            System.out.println("HostModelTest.receiveDataFromBookScrabbleServer: failed");
    }

    public void divide_tiles(){
        Player player3 = new Player("Eli" , true);
        hostModel.getPlayers().add(player3);
        hostModel.divide_tiles(2);
        if(player3.getPlayer_tiles().size() != 7)
            System.out.println("HostModelTest.divide_tiles: failed");
    }

    public void pass_turn(){
        hostModel.setCurrentPlayerIndex(2);
        hostModel.pass_turn();
        if(hostModel.getPlayers().get(2).getPlayer_tiles().size() != 8)
            System.out.println("HostModelTest.pass_turn: failed");
    }

    public void end_game(){
        int score = 100;
        for(Player player: hostModel.getPlayers())
        {
            player.update_score(score);
            score+=50;
        }

        hostModel.end_game();
        if(!hostModel.getPlayers().get(0).getPlayer_name().equals("Eli"))
            System.out.println("HostModelTest.end_game: failed");
    }

    public void closeConnection(){
        hostModel.closeConnection();
    }
}