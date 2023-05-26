package tests;

import Model.HostModel;
import Model.Player;
import ServerSide.Tile;

import java.util.List;

public class HostModelTest {
    public static void main(String[] args) {
        HostModelTest t = new HostModelTest();
        t.HostModelTest();
        System.out.println("HostModelTest-done");
    }


    static HostModel hostModel;

    public void HostModelTest() {
        HostModel hostModel = new HostModel();

        // Add some players
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        hostModel.getPlayers().add(player1);
        hostModel.getPlayers().add(player2);

        getPlayerByName();
        //getTilesFromPlayerBag();
    }


    public void getPlayerByName() {
        if(hostModel.getPlayerByName("Alice") == null)
            System.out.println("HostModelTest.getPlayerByName: failed");
    }


    /*public void getTilesFromPlayerBag() {
        List<Tile> tiles = hostModel.getTilesFromPlayerBag("Bob", "BLOOM");
        if(tiles == null)
            System.out.println("HostModelTest.getTilesFromPlayerBag: failed");
    }*/


}