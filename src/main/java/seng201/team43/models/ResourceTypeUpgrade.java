package seng201.team43.models;

import seng201.team43.models.enums.Resource;

import java.util.Random;

/**
 * Class for resource type upgrade.
 * @author Riley Jeffcote, Luke Hallett.
 */
public class ResourceTypeUpgrade extends Upgrade {
    /**
     * Initialises resource type upgrade.
     */
    public ResourceTypeUpgrade(int cost) {
        super(cost, "Resource Type Upgrade");
    }

    /**
     * Applies it to a tower.
     * @param tower passed in which the upgrade will be applied to.
     */
    @Override
    public void apply(Tower tower) {
        Random random = new Random();
        int chance = random.nextInt(0, 3);

        Resource resource = switch(chance){
            case 0 -> Resource.WATER;
            case 1 -> Resource.WOOD;
            default -> Resource.FOOD;
        };

        tower.setResourceType(resource);
        tower.setName(String.format("%s Tower", resource.label));
    }

    @Override
    public String getDescription() {
        return "Changes the tower's resource type to a random one.";
    }
}
