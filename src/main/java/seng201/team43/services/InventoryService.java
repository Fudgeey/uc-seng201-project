package seng201.team43.services;

import seng201.team43.models.GameManager;
import seng201.team43.models.Tower;

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

    public void moveTower() {
        this.gameManager.getInventory().moveTower(this.getSelectedTower());
        this.setSelectedTower(null);
    }
}
