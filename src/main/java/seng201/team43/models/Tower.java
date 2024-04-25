package seng201.team43.models;

public class Tower {
    private Resource resourceType;
    private String name;
    private Integer reloadSpeed;
    private Integer productionUnits;

    public Tower(Resource resourceType) {
        this(resourceType, String.format("%s Tower", resourceType.label));
    }

    public Tower(Resource resourceType, String name) {
        this.resourceType = resourceType;
        this.name = name;
        this.reloadSpeed = 2;
        this.productionUnits = 10;
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

    public Integer getReloadSpeed() {
        return this.reloadSpeed;
    }

    public Integer getProductionUnits() {
        return this.productionUnits;
    }
}
