package seng201.team43.services;

import seng201.team43.exceptions.GameError;
import seng201.team43.models.GameManager;
import seng201.team43.models.Purchasable;
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
    private Upgrade selectedUpgrade;
    private Purchasable lastSelectedItem;

    public InventoryService(GameManager gameManager) {
        this.gameManager = gameManager;
        this.selectedTower = null;
        this.selectedUpgrade = null;
        this.lastSelectedItem = null;
    }

    public void setSelectedTower(Tower tower) {
        this.selectedTower = tower;
    }

    public Tower getSelectedTower() {
        return this.selectedTower;
    }

    public void moveTower() throws GameError {
        try {
            this.gameManager.getInventory().moveTower(this.getSelectedTower());
            this.setSelectedTower(null);
        } catch(GameError e) {
            throw new GameError(e.getMessage());
        }
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

    public Upgrade getSelectedUpgrade() {
        return this.selectedUpgrade;
    }

    public void setSelectedUpgrade(Upgrade upgrade) {
        this.selectedUpgrade = upgrade;
    }

    public void applyUpgrade() throws GameError {
        this.selectedTower.applyUpgrade(this.selectedUpgrade);
        this.gameManager.getInventory().removeUpgrade(this.selectedUpgrade);

        this.setSelectedUpgrade(null);
        this.setSelectedTower(null);
    }

    public void sellItem() throws GameError {
        Purchasable item = this.getLastSelectedItem();
        this.gameManager.addMoney((double) item.getSellPrice());

        if(item.getClass() == Tower.class) {
            if(this.gameManager.getInventory().getActiveTowers().contains(item)) {
                this.gameManager.getInventory().removeActiveTower((Tower) item);
            } else {
                this.gameManager.getInventory().removeReserveTower((Tower) item);
            }
        } else {
            this.gameManager.getInventory().removeUpgrade((Upgrade) item);
        }
    }

    public void setLastSelectedItem(Purchasable item) {
        this.lastSelectedItem = item;
    }

    public Purchasable getLastSelectedItem() {
        return this.lastSelectedItem;
    }
}
