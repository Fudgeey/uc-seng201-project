package seng201.team43.unittests.services.models;

import org.junit.jupiter.api.Test;
import seng201.team43.models.Cart;
import seng201.team43.models.Resource;
import seng201.team43.models.Tower;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Cart class test implementation
 * @author Luke Hallett, Riley Jeffcote
 */
public class CartTest {
    /**
     * Test filling a food cart which would overfill it
     * with the given track distance.
     */
    @Test
    void testSuccessfulFill() {
        Cart cart = new Cart(100, 10, Resource.FOOD);
        Tower tower = new Tower(Resource.FOOD);

        cart.fill(tower, 100);

        assertEquals(100, cart.getCurrentFilled());
    }

    /**
     * Test filling a cart with the wrong tower type.
     */
    @Test
    void testWrongTowerFill() {
        Cart cart = new Cart(100, 10, Resource.FOOD);
        Tower tower = new Tower(Resource.WOOD);

        cart.fill(tower, 100);

        assertEquals(0, cart.getCurrentFilled());
    }

    /**
     * Test filling a cart that will not get full
     * by the tower over the track distance.
     */
    @Test
    void testUnsuccessfulFill() {
        Cart cart = new Cart(200, 20, Resource.FOOD);
        Tower tower = new Tower(Resource.FOOD);

        cart.fill(tower, 50);

        assertEquals(50, cart.getCurrentFilled());
    }

    /**
     * Test filling a cart with a broken tower.
     */
    @Test
    void testBrokenTowerFill() {
        Cart cart = new Cart(100, 10, Resource.FOOD);
        Tower tower = new Tower(Resource.FOOD);

        tower.setBroken(true);
        cart.fill(tower, 100);

        assertEquals(0, cart.getCurrentFilled());
    }

    /**
     * Test filling an already full cart.
     */
    @Test
    void testFullFill() {
        Cart cart = new Cart(100, 10, Resource.FOOD);
        Tower tower = new Tower(Resource.FOOD);

        cart.addCurrentFilled(100);
        cart.fill(tower, 100);

        assertEquals(100, cart.getCurrentFilled());
    }
}
