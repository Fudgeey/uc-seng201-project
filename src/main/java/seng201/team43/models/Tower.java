package seng201.team43.models;

public class Tower {
    private Resource resourceType;

    public Tower(Resource resourceType) {
        this.resourceType = resourceType;
    }

    public Resource getResourceType() {
        return resourceType;
    }

    public void setResourceType(Resource resourceType) {
        this.resourceType = resourceType;
    }
}
