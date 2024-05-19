package seng201.team43.models;

/**
 * Class for Carts
 *
 * @author Riley Jeffcote, Luke Hallett
 */
public class Cart {
    private final int size;
    private final int speed;
    private final Resource type;
    private int currentFilled;

    /**
     * Initialise cart with given parameters
     * @param size size of cart
     * @param speed speed of cart
     * @param type resource type of cart
     */
    public Cart(int size, int speed, Resource type) {
        this.size = size;
        this.speed = speed;
        this.type = type;
        this.currentFilled = 0;
    }

    public int getSize() {
        return this.size;
    }

    public int getSpeed() {
        return this.speed;
    }

    public Resource getType() {
        return this.type;
    }

    public int getCurrentFilled() {
        return this.currentFilled;
    }

    public void addCurrentFilled(int amount) {
        this.currentFilled += amount;
    }

    /**
     * Fills the cart for the round
     * @param tower tower that the cart is getting filled from
     * @param trackDistance distance of the track's round
     * @return the money earned by filling the cart
     */
    public int fill(Tower tower, int trackDistance) {
        if(tower.getResourceType() == this.getType() && !tower.isBroken()) {
            if (this.getSize() > this.getCurrentFilled()) {
                int cartTimeOnTrack = trackDistance / this.getSpeed();
                int towerFillTimes = Math.floorDiv(cartTimeOnTrack, tower.getReloadSpeed()) + 1;

                int unitsToAdd = towerFillTimes * tower.getProductionUnits();
                if(unitsToAdd >= this.getSize()) {
                    unitsToAdd = this.getSize();
                }

                this.addCurrentFilled(unitsToAdd);
                tower.addExperience((int) (unitsToAdd * 0.1));

                return towerFillTimes * 10;
            }
        }

        return 0;
    }
}
