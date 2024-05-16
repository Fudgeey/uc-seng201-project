package seng201.team43.models;

import seng201.team43.exceptions.GameError;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private ArrayList<Tower> activeTowers;
    private final ArrayList<Tower> reserveTowers;
    private final ArrayList<Upgrade> upgrades;

    /**
     * Initialises an empty inventory.
     */
    public Inventory() {
        this.activeTowers = new ArrayList<>();
        this.reserveTowers = new ArrayList<>();
        this.upgrades = new ArrayList<>();
    }

    public ArrayList<Tower> getActiveTowers() {
        return this.activeTowers;
    }

    public void setActiveTowers(List<Tower> towers) {
        this.activeTowers = new ArrayList<>(towers);
    }

    /**
     * Adds a tower to active tower inventory. Only can do this if active towers is not full.
     * @param tower tower to add to active inventory
     * @throws GameError error if already have more than five active towers.
     */
    public void addActiveTower(Tower tower) throws GameError {
        if(this.getActiveTowers().size() >= 5) {
            throw new GameError("You cannot have more than five active towers.");
        }

        this.activeTowers.add(tower);
    }

    /**
     * Removes tower from active inventory. There must always be at least one tower in active inventory.
     * @param tower tower to remove from active inventory.
     * @throws GameError error if removing last tower from inventory.
     */
    public void removeActiveTower(Tower tower) throws GameError {
        if(this.getActiveTowers().size() <= 1) {
            throw new GameError("You cannot have less than one active tower.");
        }

        this.activeTowers.remove(tower);
    }

    public ArrayList<Tower> getReserveTowers() {
        return this.reserveTowers;
    }

    /**
     * Adds a tower to reserve inventory. Checks if it is full first.
     * @param tower tower to add to reserve inventory.
     * @throws GameError error if already five reserve towers.
     */
    public void addReserveTower(Tower tower) throws GameError {
        if(this.getReserveTowers().size() >= 5) {
            throw new GameError("You cannot have more than five reserve towers.");
        }

        this.reserveTowers.add(tower);
    }

    /**
     * Removes a tower from reserve inventory.
     * @param tower tower to remove from reserve inventory.
     */
    public void removeReserveTower(Tower tower) {
        this.reserveTowers.remove(tower);
    }

    public ArrayList<Upgrade> getUpgrades() {
        return this.upgrades;
    }

    /**
     * Adds an upgrade item to upgrade inventory.
     * @param upgrade
     */
    public void addUpgrade(Upgrade upgrade) {
        this.upgrades.add(upgrade);
    }

    /**
     * Removes an upgrade item from upgrades inventory.
     * @param upgrade
     */
    public void removeUpgrade(Upgrade upgrade) {
        this.upgrades.remove(upgrade);
    }

    /**
     * Adds an item to inventory. Checks what kind of item it is. i.e. tower or upgrade. If tower, checks if active is
     * full, if it is then it goes to reserve inventory, otherwise defaults to active inventory.
     * @param item
     */
    public void addItem(Purchasable item) throws GameError {
        if(item.getClass() == Tower.class) {
            try {
                this.addActiveTower((Tower) item);
            } catch (GameError e) {
                try {
                    this.addReserveTower((Tower) item);
                } catch (GameError error) {
                    throw new GameError("Both your tower inventories are full and you cannot add any more.");
                }
            }
        } else {
            this.addUpgrade((Upgrade) item);
        }
    }

    /**
     * Moves a tower between active and reserve lists.
     * @param tower tower to move
     */
    public void moveTower(Tower tower) throws GameError {
        if(this.getActiveTowers().contains(tower)) {
            this.addReserveTower(tower);
            this.removeActiveTower(tower);
        } else {
            this.addActiveTower(tower);
            this.removeReserveTower(tower);
        }
    }
}
