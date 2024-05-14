package seng201.team43.models;

import java.util.Random;

public class ResourceTypeUpgrade extends Upgrade {
    public ResourceTypeUpgrade() {
        super(75, "Resource Type Upgrade");
    }

    public void apply(Tower tower) {
        Random random = new Random();
        int chance = random.nextInt(0, 3);

        Resource resource = switch(chance){
            case 0 -> Resource.WATER;
            case 1 -> Resource.WOOD;
            case 2 -> Resource.FOOD;
            default -> null;
        };

        assert resource != null;

        tower.setResourceType(resource);
        tower.setName(String.format("%s Tower", resource.label));
    }

    public String getDescription() {
        return "Changes the tower's resource type to a random one.";
    }
}
