package seng201.team43.services;

import seng201.team43.exceptions.GameException;
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

    /**
     * Initialises inventory service and deselects selected items.
     * @param gameManager uses game manager for data collection.
     */
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

    /**
     * Moves a tower from selected inventory to the other. e.g. reserve to active or vise versa.
     * @throws GameException if the inventory is full.
     */
    public void moveTower() throws GameException {
        try {
            this.gameManager.getInventory().moveTower(this.getSelectedTower());
            this.setSelectedTower(null);
        } catch(GameException e) {
            throw new GameException(e.getMessage());
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

    /**
     * Applies an upgrade to a selected tower.
     * @throws GameException if there is an invalid upgrade. e.g. reload speed cannot decrease below one.
     */
    public void applyUpgrade() throws GameException {
        this.selectedTower.applyUpgrade(this.selectedUpgrade);
        this.gameManager.getInventory().removeUpgrade(this.selectedUpgrade);

        this.setSelectedUpgrade(null);
        this.setSelectedTower(null);
    }

    /**
     * Sells selected item.
     * @throws GameException if there is invalid amount of towers in inventory. e.g. active towers must have
     * at least one tower in the list because must be at least one tower playable in game.
     */
    public void sellItem() throws GameException {
        Purchasable item = this.getLastSelectedItem();
        this.gameManager.addMoney(item.getSellPrice());

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
