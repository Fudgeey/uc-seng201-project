package seng201.team43.models;

/**
 * Generic upgrade class
 * @author Riley Jeffcote, Luke Hallett
 */
public abstract class Upgrade implements Purchasable {
    private final int cost;
    private final String name;

    protected Upgrade(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public abstract void apply(Tower tower);

    public int getCost() {
        return this.cost;
    }

    public String getName() {
        return this.name;
    }

    public abstract String getDescription();
}
