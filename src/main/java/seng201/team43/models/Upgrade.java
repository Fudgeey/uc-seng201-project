package seng201.team43.models;

/**
 * Generic upgrade class
 * @author Riley Jeffcote, Luke Hallett
 */
public abstract class Upgrade {
    private final int cost;

    protected Upgrade(int cost) {
        this.cost = cost;
    }

    public abstract void apply(Tower tower);

    public int getCost() {
        return this.cost;
    }
}
