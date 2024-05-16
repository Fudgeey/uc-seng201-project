package seng201.team43.unittests.services.models;

import org.junit.jupiter.api.Test;
import seng201.team43.models.Cart;
import seng201.team43.models.Resource;

public class CartTest {
    @Test
    void testFill() {
        Cart cart = new Cart(100, 10, Resource.FOOD);
    }
}
