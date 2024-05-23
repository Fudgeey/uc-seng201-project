package seng201.team43.unittests.models;

import org.junit.jupiter.api.Test;
import seng201.team43.models.Cart;
import seng201.team43.models.enums.Resource;
import seng201.team43.models.Tower;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest {
    @Test
    void testSuccessfulFill() {
        Cart cart = new Cart(100, 10, Resource.FOOD);
        Tower tower = new Tower(Resource.FOOD);

        cart.fill(tower, 100);

        assertEquals(100, cart.getCurrentFilled());
    }

    @Test
    void testWrongTowerFill() {
        Cart cart = new Cart(100, 10, Resource.FOOD);
        Tower tower = new Tower(Resource.WOOD);

        cart.fill(tower, 100);

        assertEquals(0, cart.getCurrentFilled());
    }

    @Test
    void testUnsuccessfulFill() {
        Cart cart = new Cart(200, 20, Resource.FOOD);
        Tower tower = new Tower(Resource.FOOD);

        cart.fill(tower, 50);

        assertEquals(50, cart.getCurrentFilled());
    }

    @Test
    void testBrokenTowerFill() {
        Cart cart = new Cart(100, 10, Resource.FOOD);
        Tower tower = new Tower(Resource.FOOD);

        tower.setBroken(true);
        cart.fill(tower, 100);

        assertEquals(0, cart.getCurrentFilled());
    }

    @Test
    void testFullFill() {
        Cart cart = new Cart(100, 10, Resource.FOOD);
        Tower tower = new Tower(Resource.FOOD);

        cart.addCurrentFilled(100);
        cart.fill(tower, 100);

        assertEquals(100, cart.getCurrentFilled());
    }
}
