package seng201.team43.models;

/**
 * Class for Towers
 *
 * @author Riley Jeffcote, Luke Hallett
 */
public class Tower implements Purchasable {
    private Resource resourceType;
    private String name;
    private int reloadSpeed;
    private int productionUnits;
    private int experience;
    private int cost;
    private boolean purchased;
    private boolean broken;

    public Tower(Resource resourceType) {
        this(resourceType, String.format("%s Tower", resourceType.label));
    }

    public Tower(Resource resourceType, String name) {
        this.resourceType = resourceType;
        this.name = name;
        this.reloadSpeed = 5;
        this.productionUnits = 50;
        this.experience = 0;
        this.cost = 100;
        this.purchased = false;
    }

    public Resource getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(Resource resourceType) {
        this.resourceType = resourceType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReloadSpeed() {
        return this.reloadSpeed;
    }

    public int getProductionUnits() {
        return this.productionUnits;
    }

    public int getLevel() {
        return (Math.floorDiv(this.experience, 10) + 1);
    }

    public void addExperience(int experience) {
        int oldLevel = this.getLevel();
        this.experience += experience;
        int newLevel = this.getLevel();

        if(newLevel > oldLevel) {
            this.productionUnits += 25;
        }
    }

    /**
     * Upon levelling down, tower looses 10 experience and 25 production units.
     */
    public void levelDown() {
        if(this.getLevel() > 1) {
            this.experience -= 10;
            this.productionUnits -= 25;
        }
    }

    /**
     * Apply an upgrade to the tower.
     * @param upgrade upgrade to apply
     */
    public void applyUpgrade(Upgrade upgrade) {
        upgrade.apply(this);
        this.cost += upgrade.getCost();
    }

    /**
     * Increases tower's production units
     * @param productionUnits number of production units
     */
    public void increaseProductionUnits(Integer productionUnits) {
        this.productionUnits += productionUnits;
    }

    /**
     * Decreases the reload speed of the tower.
     * @param reloadSpeed
     */
    public void decreaseReloadSpeed(Integer reloadSpeed) {
        this.reloadSpeed -= reloadSpeed;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public boolean isBroken() {
        return this.broken;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    public int getSellPrice() {
        return (int) Math.round((getCost() * 0.8));
    }

    @Override
    public String getDescription() {
        return String.format("A tower that generates %s.", resourceType.label);
    }

    @Override
    public void setPurchased() {
        this.purchased = true;
    }

    @Override
    public Boolean getPurchased() {
        return this.purchased;
    }
}
