package seng201.team43.models;

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

    public void addActiveTower(Tower tower) {
        this.activeTowers.add(tower);
    }

    public void removeActiveTower(Tower tower) {
        this.activeTowers.remove(tower);
    }

    public Integer getActiveTowerCount() {
        return this.activeTowers.size();
    }

    public ArrayList<Tower> getReserveTowers() {
        return this.reserveTowers;
    }

    public void addReserveTower(Tower tower) {
        this.reserveTowers.add(tower);
    }

    public Integer getReserveTowerCount() {
        return this.reserveTowers.size();
    }

    public void removeReserveTower(Tower tower) {
        this.reserveTowers.remove(tower);
    }

    /**
     * Returns all unused upgrades the player has
     * @return all upgrades
     */
    public ArrayList<Upgrade> getUpgrades() {
        return this.upgrades;
    }

    /**
     * Adds an upgrade to the player's inventory
     * @param upgrade upgrade to add
     */
    public void addUpgrade(Upgrade upgrade) {
        this.upgrades.add(upgrade);
    }
}
