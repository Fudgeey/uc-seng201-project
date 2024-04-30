package seng201.team43.models;

import java.util.ArrayList;

public class Tower {
    private Resource resourceType;
    private String name;
    private Integer reloadSpeed;
    private Integer productionUnits;
    private ArrayList<Upgrade> upgrades;

    public Tower(Resource resourceType) {
        this(resourceType, String.format("%s Tower", resourceType.label));
    }

    public Tower(Resource resourceType, String name) {
        this.resourceType = resourceType;
        this.name = name;
        this.reloadSpeed = 2;
        this.productionUnits = 10;
        this.upgrades = new ArrayList<>();
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
}
