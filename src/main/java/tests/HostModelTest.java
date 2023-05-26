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


    HostModel hostModel;

    public void HostModelTest() {
        HostModel hostModel = new HostModel();

        // Add some players
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        hostModel.getPlayers().add(player1);
        hostModel.getPlayers().add(player2);
    }

    // Test getPlayerByName method
    public void getPlayerByName() {
        Player foundPlayer = hostModel.getPlayerByName("Alice");
        if(foundPlayer == null)
            System.out.println("HostModelTest.getPlayerByName: failed");
    }

    // Test getTilesFromPlayerBag method
    public void getTilesFromPlayerBag() {
        List<Tile> tiles = hostModel.getTilesFromPlayerBag("Bob", "BLOOM");
        if(tiles == null)
            System.out.println("HostModelTest.getTilesFromPlayerBag: failed");
    }
}