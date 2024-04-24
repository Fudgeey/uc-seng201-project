package seng201.team43.models;

import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Tower> activeTowers;
    private ArrayList<Tower> reservedTowers;
    private ArrayList<Upgrade> upgrades;

    public Inventory() {
        activeTowers = new ArrayList<>();
    }

    public ArrayList<Tower> getActiveTowers() {
        return activeTowers;
    }

    public void addActiveTower(Tower tower) {
        activeTowers.add(tower);
    }
}
