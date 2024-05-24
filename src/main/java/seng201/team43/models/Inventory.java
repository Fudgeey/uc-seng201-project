package seng201.team43.models;

import seng201.team43.exceptions.GameException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage inventory of player
 */
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
     * @throws GameException error if already have more than five active towers.
     */
    public void addActiveTower(Tower tower) throws GameException {
        if(this.getActiveTowers().size() >= 5) {
            throw new GameException("You cannot have more than five active towers.");
        }

        this.activeTowers.add(tower);
    }

    /**
     * Removes tower from active inventory. There must always be at least one tower in active inventory.
     * @param tower tower to remove from active inventory.
     * @throws GameException error if removing last tower from inventory.
     */
    public void removeActiveTower(Tower tower) throws GameException {
        if(this.getActiveTowers().size() <= 1) {
            throw new GameException("You cannot have less than one active tower.");
        }

        this.activeTowers.remove(tower);
    }

    public ArrayList<Tower> getReserveTowers() {
        return this.reserveTowers;
    }

    /**
     * Adds a tower to reserve inventory. Checks if it is full first.
     * @param tower tower to add to reserve inventory.
     * @throws GameException error if already five reserve towers.
     */
    public void addReserveTower(Tower tower) throws GameException {
        if(this.getReserveTowers().size() >= 5) {
            throw new GameException("You cannot have more than five reserve towers.");
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
     * @param upgrade upgrade to add to inventory
     */
    public void addUpgrade(Upgrade upgrade) {
        this.upgrades.add(upgrade);
    }

    /**
     * Removes an upgrade item from upgrades inventory.
     * @param upgrade upgrade to remove from inventory
     */
    public void removeUpgrade(Upgrade upgrade) {
        this.upgrades.remove(upgrade);
    }

    /**
     * Adds an item to inventory. Checks what kind of item it is. i.e. tower or upgrade. If tower, checks if active is
     * full, if it is then it goes to reserve inventory, otherwise defaults to active inventory.
     * @param item item to add to inventory
     * @throws GameException if there is an error adding item to inventory
     */
    public void addItem(Purchasable item) throws GameException {
        if(item.getClass() == Tower.class) {
            try {
                this.addActiveTower((Tower) item);
            } catch (GameException e) {
                try {
                    this.addReserveTower((Tower) item);
                } catch (GameException error) {
                    throw new GameException("Both your tower inventories are full and you cannot add any more.");
                }
            }
        } else {
            this.addUpgrade((Upgrade) item);
        }
    }

    /**
     * Moves a tower between active and reserve lists.
     * @param tower tower to move
     * @throws GameException if one tower inventory is full
     */
    public void moveTower(Tower tower) throws GameException {
        if(this.getActiveTowers().contains(tower)) {
            this.addReserveTower(tower);
            this.removeActiveTower(tower);
        } else {
            this.addActiveTower(tower);
            this.removeReserveTower(tower);
        }
    }
}
