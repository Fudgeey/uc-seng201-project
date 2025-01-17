package seng201.team43.models;

import seng201.team43.models.enums.Resource;
import seng201.team43.models.enums.RoundDifficulty;

/**
 * Class for Carts
 *
 * @author Riley Jeffcote, Luke Hallett
 */
public class Cart {
    private final int size;
    private int speed;
    private final int baseSpeed;
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
        this.baseSpeed = speed;
        this.type = type;
        this.currentFilled = 0;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Sets cart speed based on round difficulty
     * @param roundDifficulty difficulty of current round
     */
    public void setSpeed(RoundDifficulty roundDifficulty) {
        this.speed = switch(roundDifficulty) {
            case EASY -> this.baseSpeed - 1;
            case MEDIUM -> this.baseSpeed;
            case HARD -> this.baseSpeed + 1;
        };
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

    public void setCurrentFilled(int size) {
        this.currentFilled = size;
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
