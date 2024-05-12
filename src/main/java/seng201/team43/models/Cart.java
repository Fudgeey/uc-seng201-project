package seng201.team43.models;
/**
 * Class for Carts
 *
 * @author Riley Jeffcote, Luke Hallett
 */
public class Cart {
    private int size;
    private int speed;
    private Resource type;
    private int currentFilled;

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
}
