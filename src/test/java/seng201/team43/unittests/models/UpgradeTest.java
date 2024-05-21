package seng201.team43.unittests.models;

import org.junit.jupiter.api.Test;
import seng201.team43.exceptions.GameException;
import seng201.team43.models.*;

import static org.junit.jupiter.api.Assertions.*;

public class UpgradeTest {
    @Test
    void testRepairTower() throws GameException {
        Tower tower = new Tower(Resource.FOOD);
        RepairTowerUpgrade repairTowerUpgrade = new RepairTowerUpgrade();

        tower.setBroken(true);
        tower.applyUpgrade(repairTowerUpgrade);

        assertFalse(tower.isBroken());
    }

    @Test
    void testReloadUpgrade() throws GameException {
        Tower tower = new Tower(Resource.WATER);
        ReloadUpgrade reloadUpgrade = new ReloadUpgrade();

        tower.applyUpgrade(reloadUpgrade);

        assertEquals(4, tower.getReloadSpeed());
    }

    @Test
    void testReloadUpgradeBelowOne() {
        Tower tower = new Tower(Resource.WATER);
        ReloadUpgrade reloadUpgrade = new ReloadUpgrade();

        GameException error = assertThrows(GameException.class, () -> {
            tower.applyUpgrade(reloadUpgrade);
            tower.applyUpgrade(reloadUpgrade);
            tower.applyUpgrade(reloadUpgrade);
            tower.applyUpgrade(reloadUpgrade);
            tower.applyUpgrade(reloadUpgrade);
            tower.applyUpgrade(reloadUpgrade);
        });

        assertEquals("Cannot decrease reload speed below 1s.", error.getMessage());
    }

    @Test
    void testUpgradeSellPrice() {
        RepairTowerUpgrade repairTowerUpgrade = new RepairTowerUpgrade();

        assertEquals(40, repairTowerUpgrade.getSellPrice());
    }

    @Test
    void testProductionUpgrade() throws GameException {
        Tower tower = new Tower(Resource.WATER);
        ProductionUpgrade productionUpgrade = new ProductionUpgrade(25);

        tower.applyUpgrade(productionUpgrade);

        assertEquals(75, tower.getProductionUnits());
    }

    @Test
    void testResourceTypeUpgrade() throws GameException {
        Tower tower = new Tower(Resource.WATER);
        ResourceTypeUpgrade resourceTypeUpgrade = new ResourceTypeUpgrade();

        tower.applyUpgrade(resourceTypeUpgrade);

        assertEquals(Resource.class, tower.getResourceType().getClass());
    }
}
