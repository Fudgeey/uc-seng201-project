package seng201.team43.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team43.exceptions.GameException;
import seng201.team43.models.GameManager;
import seng201.team43.models.Resource;
import seng201.team43.models.Tower;
import seng201.team43.services.ShopService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShopServiceTest {
    private GameManager gameManager;
    private ShopService shopService;

    @BeforeEach
    void setupTest() {
        this.gameManager = new GameManager();
        this.shopService = new ShopService(this.gameManager);
    }

    @Test
    void testBuyItem() throws GameException {
        Tower towerOne = new Tower(Resource.WATER);
        this.shopService.buyItem(towerOne);
        assertEquals(1, this.gameManager.getInventory().getActiveTowers().size());

        GameException errorOne = assertThrows(GameException.class, () -> {
            this.shopService.buyItem(null);
        });

        assertEquals("Invalid item was purchased.", errorOne.getMessage());

        this.gameManager.removeMoney(100);
        Tower towerTwo = new Tower(Resource.WATER);

        GameException errorTwo = assertThrows(GameException.class, () -> {
            this.shopService.buyItem(towerTwo);
        });

        assertEquals("You do not have enough money to buy this.", errorTwo.getMessage());
    }

    @Test
    void testGetShopItems() {
        this.gameManager.prepareRound();

        assertEquals(5, this.shopService.getShopItems().size());
    }
}
