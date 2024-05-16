package seng201.team43.models;

import seng201.team43.exceptions.GameError;

/**
 * Generic upgrade class
 * @author Riley Jeffcote, Luke Hallett
 */
public abstract class Upgrade implements Purchasable {
    private final int cost;
    private final String name;
    private Boolean purchased;

    protected Upgrade(int cost, String name) {
        this.cost = cost;
        this.name = name;
        this.purchased = false;
    }

    public abstract void apply(Tower tower) throws GameError;

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
