package seng201.team43.unittests.services.models;

import org.junit.jupiter.api.Test;
import seng201.team43.exceptions.GameException;
import seng201.team43.models.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryTest {
    @Test
    void emptyInventoryTest() {
        Inventory inventory = new Inventory();

        assertEquals(0, inventory.getActiveTowers().size());
        assertEquals(0, inventory.getReserveTowers().size());
        assertEquals(0, inventory.getUpgrades().size());
    }

    @Test
    void setActiveTowersTest() throws GameException {
        Inventory inventory = new Inventory();
        inventory.addActiveTower(new Tower(Resource.FOOD));

        inventory.setActiveTowers(new ArrayList<>());

        assertEquals(0, inventory.getActiveTowers().size());
    }

    @Test
    void moreThanFiveActiveTowersTest() {
        Inventory inventory = new Inventory();

        GameException error = assertThrows(GameException.class, () -> {
            inventory.addActiveTower(new Tower(Resource.FOOD));
            inventory.addActiveTower(new Tower(Resource.WOOD));
            inventory.addActiveTower(new Tower(Resource.WATER));
            inventory.addActiveTower(new Tower(Resource.FOOD));
            inventory.addActiveTower(new Tower(Resource.WOOD));
            inventory.addActiveTower(new Tower(Resource.WATER));
        });

        assertEquals("You cannot have more than five active towers.", error.getMessage());
    }

    @Test
    void lessThanOneActiveTowersTest() throws GameException {
        Inventory inventory = new Inventory();
        Tower tower = new Tower(Resource.FOOD);

        inventory.addActiveTower(tower);

        GameException error = assertThrows(GameException.class, () -> {
            inventory.removeActiveTower(tower);
        });

        assertEquals("You cannot have less than one active tower.", error.getMessage());
    }

    @Test
    void testMoveTower() throws GameException {
        Inventory inventory = new Inventory();
        Tower towerOne = new Tower(Resource.WOOD);
        Tower towerTwo = new Tower(Resource.FOOD);

        inventory.addActiveTower(towerOne);
        inventory.addActiveTower(towerTwo);
        assertEquals(towerOne, inventory.getActiveTowers().get(0));
        assertEquals(towerTwo, inventory.getActiveTowers().get(1));

        inventory.moveTower(towerOne);
        assertEquals(towerOne, inventory.getReserveTowers().get(0));
        assertEquals(towerTwo, inventory.getActiveTowers().get(0));

        inventory.moveTower(towerOne);
        inventory.moveTower(towerTwo);
        assertEquals(towerTwo, inventory.getReserveTowers().get(0));
        assertEquals(towerOne, inventory.getActiveTowers().get(0));
    }

    @Test
    void addItemTest() throws GameException {
        Inventory inventory = new Inventory();
        ResourceTypeUpgrade resourceTypeUpgrade = new ResourceTypeUpgrade();
        Tower towerOne = new Tower(Resource.WOOD);
        Tower towerTwo = new Tower(Resource.WOOD);
        Tower towerThree = new Tower(Resource.WOOD);
        Tower towerFour = new Tower(Resource.WOOD);
        Tower towerFive = new Tower(Resource.WOOD);
        Tower towerSix = new Tower(Resource.WOOD);
        Tower towerSeven = new Tower(Resource.WOOD);
        Tower towerEight = new Tower(Resource.WOOD);
        Tower towerNine = new Tower(Resource.WOOD);
        Tower towerTen = new Tower(Resource.WOOD);
        Tower towerEleven = new Tower(Resource.WOOD);

        inventory.addItem(resourceTypeUpgrade);
        assertEquals(resourceTypeUpgrade, inventory.getUpgrades().get(0));

        inventory.addItem(towerOne);
        assertEquals(towerOne, inventory.getActiveTowers().get(0));

        inventory.addItem(towerTwo);
        inventory.addItem(towerThree);
        inventory.addItem(towerFour);
        inventory.addItem(towerFive);

        inventory.addItem(towerSix);
        assertEquals(towerSix, inventory.getReserveTowers().get(0));

        inventory.addItem(towerSeven);
        inventory.addItem(towerEight);
        inventory.addItem(towerNine);
        inventory.addItem(towerTen);

        GameException error = assertThrows(GameException.class, () -> {
            inventory.addItem(towerEleven);
        });

        assertEquals("Both your tower inventories are full and you cannot add any more.", error.getMessage());
    }

    @Test
    void removeUpgradeTest() throws GameException {
        Inventory inventory = new Inventory();
        ReloadUpgrade reloadUpgrade = new ReloadUpgrade();

        inventory.addItem(reloadUpgrade);
        inventory.removeUpgrade(reloadUpgrade);

        assertEquals(0, inventory.getUpgrades().size());
    }
}
