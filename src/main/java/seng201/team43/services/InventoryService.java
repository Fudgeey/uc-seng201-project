package seng201.team43.services;

import seng201.team43.exceptions.GameError;
import seng201.team43.models.GameManager;
import seng201.team43.models.Tower;
import seng201.team43.models.Upgrade;

import java.util.ArrayList;

/**
 * Inventory service to deal with logic for inventory.
 *
 * @author Luke Hallett, Riley Jeffcote
 */
public class InventoryService {
    private final GameManager gameManager;
    private Tower selectedTower;

    public InventoryService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setSelectedTower(Tower tower) {
        this.selectedTower = tower;
    }

    public Tower getSelectedTower() {
        return this.selectedTower;
    }

    public void moveTower() throws GameError {
        this.gameManager.getInventory().moveTower(this.getSelectedTower());
        this.setSelectedTower(null);
    }

    public ArrayList<Tower> getActiveTowers() {
        return this.gameManager.getInventory().getActiveTowers();
    }

    public ArrayList<Tower> getReserveTowers() {
        return this.gameManager.getInventory().getReserveTowers();
    }

    public ArrayList<Upgrade> getUpgrades() {
        return this.gameManager.getInventory().getUpgrades();
    }
}
