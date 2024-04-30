package seng201.team43.models;
/**
 * Class for Carts
 *
 * @author Riley Jeffcote, Luke Hallett
 */
public class Cart {
    private Integer size;
    private Integer speed;
    private String type;

    /**
     * Gets size of cart
     * @return cart size
     */
    public Integer getSize() {return this.size; }

    /**
     * Gets speed of cart
     * @return cart speed
     */
    public Integer getSpeed() {return this.speed; }

    /**
     * Gets the type of the cart
     * @return cart type
     */
    public String getType() {return this.type; }
}
