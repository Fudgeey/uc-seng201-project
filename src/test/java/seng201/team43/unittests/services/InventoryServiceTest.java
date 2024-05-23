package seng201.team43.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team43.exceptions.GameException;
import seng201.team43.models.*;
import seng201.team43.models.enums.Resource;
import seng201.team43.services.InventoryService;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {
    private GameManager gameManager;
    private InventoryService inventoryService;

    @BeforeEach
    void setupTest() {
        this.gameManager = new GameManager();
        this.inventoryService = new InventoryService(this.gameManager);
    }

    @Test
    void testSellItem() throws GameException {
        Tower towerOne = new Tower(Resource.WATER);
        Tower towerTwo = new Tower(Resource.WATER);
        Tower towerThree = new Tower(Resource.WATER);
        this.gameManager.getInventory().addActiveTower(towerOne);
        this.gameManager.getInventory().addActiveTower(towerTwo);
        this.gameManager.getInventory().addReserveTower(towerThree);

        this.inventoryService.setSelectedTower(towerTwo);
        this.inventoryService.setLastSelectedItem(towerTwo);

        this.inventoryService.sellItem();
        assertEquals(1, this.inventoryService.getActiveTowers().size());

        this.inventoryService.setSelectedTower(towerThree);
        this.inventoryService.setLastSelectedItem(towerThree);

        this.inventoryService.sellItem();
        assertEquals(0, this.inventoryService.getReserveTowers().size());

        RepairTowerUpgrade repairTowerUpgrade = new RepairTowerUpgrade(50);
        this.gameManager.getInventory().addUpgrade(repairTowerUpgrade);

        this.inventoryService.setSelectedUpgrade(repairTowerUpgrade);
        this.inventoryService.setLastSelectedItem(repairTowerUpgrade);

        this.inventoryService.sellItem();
        assertEquals(0, this.inventoryService.getUpgrades().size());
    }

    @Test
    void testApplyUpgrade() throws GameException {
        Tower tower = new Tower(Resource.WATER);
        ReloadUpgrade upgrade = new ReloadUpgrade(100);
        this.gameManager.getInventory().addActiveTower(tower);
        this.gameManager.getInventory().addUpgrade(upgrade);

        this.inventoryService.setSelectedTower(tower);
        this.inventoryService.setSelectedUpgrade(upgrade);
        this.inventoryService.setLastSelectedItem(upgrade);

        this.inventoryService.applyUpgrade();
        assertEquals(0, this.inventoryService.getUpgrades().size());
        assertNull(this.inventoryService.getSelectedUpgrade());
        assertNull(this.inventoryService.getSelectedTower());
    }

    @Test
    void testMoveTower() throws GameException {
        Tower towerOne = new Tower(Resource.WATER);
        Tower towerTwo = new Tower(Resource.WATER);
        Tower towerThree = new Tower(Resource.WATER);
        this.gameManager.getInventory().addActiveTower(towerOne);
        this.gameManager.getInventory().addActiveTower(towerTwo);
        this.gameManager.getInventory().addActiveTower(towerThree);

        this.inventoryService.setSelectedTower(towerOne);
        this.inventoryService.moveTower();

        assertEquals(2, this.inventoryService.getActiveTowers().size());
        assertEquals(1, this.inventoryService.getReserveTowers().size());

        this.inventoryService.setSelectedTower(towerTwo);
        this.inventoryService.moveTower();

        this.inventoryService.setSelectedTower(towerThree);

        GameException error = assertThrows(GameException.class, () -> {
            this.inventoryService.moveTower();
        });

        assertEquals("You cannot have less than one active tower.", error.getMessage());
    }
}
