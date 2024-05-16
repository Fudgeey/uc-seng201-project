package seng201.team43.unittests.services.models;

import org.junit.jupiter.api.Test;
import seng201.team43.exceptions.GameError;
import seng201.team43.models.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test upgrades
 */
public class UpgradeTest {
    /**
     * Test repair tower upgrade.
     */
    @Test
    void testRepairTower() throws GameError {
        Tower tower = new Tower(Resource.FOOD);
        RepairTowerUpgrade repairTowerUpgrade = new RepairTowerUpgrade();

        tower.setBroken(true);
        tower.applyUpgrade(repairTowerUpgrade);

        assertFalse(tower.isBroken());
    }

    /**
     * Test reload speed upgrade decreases reload
     * speed by 1.
     */
    @Test
    void testReloadUpgrade() throws GameError {
        Tower tower = new Tower(Resource.WATER);
        ReloadUpgrade reloadUpgrade = new ReloadUpgrade();

        tower.applyUpgrade(reloadUpgrade);

        assertEquals(4, tower.getReloadSpeed());
    }

    /**
     * Test if reload speed can decrease below 1.
     */
    @Test
    void testReloadUpgradeBelowOne() {
        Tower tower = new Tower(Resource.WATER);
        ReloadUpgrade reloadUpgrade = new ReloadUpgrade();

        GameError error = assertThrows(GameError.class, () -> {
            tower.applyUpgrade(reloadUpgrade);
            tower.applyUpgrade(reloadUpgrade);
            tower.applyUpgrade(reloadUpgrade);
            tower.applyUpgrade(reloadUpgrade);
            tower.applyUpgrade(reloadUpgrade);
            tower.applyUpgrade(reloadUpgrade);
        });

        assertEquals("Cannot decrease reload speed below 1s.", error.getMessage());
    }

    /**
     * Test upgrades have correct sell price.
     */
    @Test
    void testUpgradeSellPrice() {
        RepairTowerUpgrade repairTowerUpgrade = new RepairTowerUpgrade();

        assertEquals(40, repairTowerUpgrade.getSellPrice());
    }

    /**
     * Test production upgrade.
     */
    @Test
    void testProductionUpgrade() throws GameError {
        Tower tower = new Tower(Resource.WATER);
        ProductionUpgrade productionUpgrade = new ProductionUpgrade(25);

        tower.applyUpgrade(productionUpgrade);

        assertEquals(75, tower.getProductionUnits());
    }

    /**
     * Test resource type upgrade.
     */
    @Test
    void testResourceTypeUpgrade() throws GameError {
        Tower tower = new Tower(Resource.WATER);
        ResourceTypeUpgrade resourceTypeUpgrade = new ResourceTypeUpgrade();

        tower.applyUpgrade(resourceTypeUpgrade);

        assertEquals(Resource.class, tower.getResourceType().getClass());
    }
}
