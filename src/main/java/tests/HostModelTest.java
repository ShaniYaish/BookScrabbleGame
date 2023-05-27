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


    static HostModel hostModel = new HostModel();

    public void HostModelTest() {

        // Add some players
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        hostModel.getPlayers().add(player1);
        hostModel.getPlayers().add(player2);

        getPlayerByName();
        getTilesFromPlayerBag();
        removeTilesFromPlayerBag();
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

    // Test removeTilesFromPlayerBag method
    public void removeTilesFromPlayerBag() {
        hostModel.removeTilesFromPlayerBag("Bob", "AB");
        if(hostModel.getPlayerByName("Bob").getPlayer_tiles().size() != 0)
            System.out.println("HostModelTest.removeTilesFromPlayerBag: failed");
    }


}