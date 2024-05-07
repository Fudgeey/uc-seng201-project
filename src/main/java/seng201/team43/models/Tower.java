package seng201.team43.models;

import java.util.ArrayList;
/**
 * Class for Towers
 *
 * @author Riley Jeffcote, Luke Hallett
 */
public class Tower implements Purchasable {
    private Resource resourceType;
    private String name;
    private Integer reloadSpeed;
    private Integer productionUnits;
    private ArrayList<Upgrade> upgrades;
    private Integer towerExp;
    private Integer cost;
    private Boolean purchased;

    public Tower(Resource resourceType) {
        this(resourceType, String.format("%s Tower", resourceType.label));
    }

    public Tower(Resource resourceType, String name) {
        this.resourceType = resourceType;
        this.name = name;
        this.reloadSpeed = 5;
        this.productionUnits = 50;
        this.upgrades = new ArrayList<>();
        this.cost = 100;
        this.purchased = false;
    }

    public Resource getResourceType() {
        return this.resourceType;
    }

    /**
     * Sets the resource type of the Tower.
     * @param resourceType the resource type of the tower
     */
    public void setResourceType(Resource resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Gets the name of the tower.
     * @return the name of the tower
     */
    public String getName() {
        return this.name;
    }

    public Integer getReloadSpeed() {
        return this.reloadSpeed;
    }

    public Integer getProductionUnits() {
        return this.productionUnits;
    }

    /**
     * Calculates tower level from towerExp
     * @return tower level
     */
    public Integer getTowerLevel() {
        return ((this.towerExp - this.towerExp%10)/10);
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

    public void decreaseReloadSpeed(Integer reloadSpeed) {
        this.reloadSpeed -= reloadSpeed;
    }

    public void changeResourceType(Resource resource) {
        this.resourceType = resource;
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
