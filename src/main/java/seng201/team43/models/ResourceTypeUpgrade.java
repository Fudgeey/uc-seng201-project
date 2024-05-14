package seng201.team43.models;

public class ResourceTypeUpgrade extends Upgrade {
    public ResourceTypeUpgrade() {
        super(100, "Resource Type Upgrade");
    }

    public void apply(Tower tower) {
        tower.setResourceType(Resource.WOOD);
    }

    public String getDescription() {
        return "Changes the tower's resource type.";
    }
}
