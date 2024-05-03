package seng201.team43.models;

import seng201.team43.exceptions.GameError;

import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Tower> activeTowers;
    private final ArrayList<Tower> reserveTowers;
    private final ArrayList<Upgrade> upgrades;

    public Inventory() {
        this.activeTowers = new ArrayList<>();
        this.reserveTowers = new ArrayList<>();
        this.upgrades = new ArrayList<>();
    }

    public ArrayList<Tower> getActiveTowers() {
        return this.activeTowers;
    }

    public void addActiveTower(Tower tower) throws GameError {
        if(this.getActiveTowerCount() == 5) {
            throw new GameError("You cannot have more than five active towers.");
        }

        this.activeTowers.add(tower);
    }

    public void removeActiveTower(Tower tower) throws GameError {
        if(this.getActiveTowerCount() == 1) {
            throw new GameError("You cannot have less than one active tower.");
        }

        this.activeTowers.remove(tower);
    }

    public Integer getActiveTowerCount() {
        return this.activeTowers.size();
    }

    public ArrayList<Tower> getReserveTowers() {
        return this.reserveTowers;
    }

    public void addReserveTower(Tower tower) throws GameError {
        if(this.getReserveTowerCount() == 5) {
            throw new GameError("You cannot have more than five reserve towers.");
        }

        this.reserveTowers.add(tower);
    }

    public Integer getReserveTowerCount() {
        return this.reserveTowers.size();
    }

    public void removeReserveTower(Tower tower) {
        this.reserveTowers.remove(tower);
    }

    public ArrayList<Upgrade> getUpgrades() {
        return this.upgrades;
    }

    public void addUpgrade(Upgrade upgrade) {
        this.upgrades.add(upgrade);
    }

    public void addItem(Purchasable item) {
        try {
            if(item.getClass() == Tower.class) {
                this.addActiveTower((Tower) item);
            } else {
                this.addUpgrade((Upgrade) item);
            }
        } catch(GameError e) {
            e.displayError();
        }
    }

    /**
     * Moves a tower between active and reserve lists.
     * @param tower tower to move
     */
    public void moveTower(Tower tower) {
        try {
            if(this.getActiveTowers().contains(tower)) {
                this.removeActiveTower(tower);
                this.addReserveTower(tower);
            } else {
                this.removeReserveTower(tower);
                this.addActiveTower(tower);
            }
        } catch(GameError e) {
            e.displayError();
        }
    }
}
