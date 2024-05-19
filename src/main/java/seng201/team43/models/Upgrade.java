package seng201.team43.models;

import seng201.team43.exceptions.GameException;

/**
 * Generic upgrade class
 * @author Riley Jeffcote, Luke Hallett
 */
public abstract class Upgrade implements Purchasable {
    private final int cost;
    private final String name;
    private Boolean purchased;

    /**
     * Initialises Upgrade class.
     * @param cost initialises cost of upgrade.
     * @param name initialises name of upgrade.
     */
    protected Upgrade(int cost, String name) {
        this.cost = cost;
        this.name = name;
        this.purchased = false;
    }

    /**
     * Applies upgrade to given tower.
     * @param tower passed in which the upgrade will be applied to.
     * @throws GameException if invalid properties. e.g. reload speed cannot go below 1.
     */
    public abstract void apply(Tower tower) throws GameException;

    public int getCost() {
        return this.cost;
    }

    public String getName() {
        return this.name;
    }

    public abstract String getDescription();

    public void setPurchased() {
        this.purchased = true;
    }

    public Boolean getPurchased() {
        return this.purchased;
    }

    public int getSellPrice() {
        return (int) Math.round((this.getCost() * 0.8));
    }
}
