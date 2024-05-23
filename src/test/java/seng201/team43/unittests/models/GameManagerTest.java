package seng201.team43.unittests.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team43.exceptions.GameException;
import seng201.team43.helpers.RoundInformation;
import seng201.team43.models.Cart;
import seng201.team43.models.GameManager;
import seng201.team43.models.enums.Resource;
import seng201.team43.models.Tower;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {
    private GameManager gameManager;

    @BeforeEach
    void setupTest() {
        this.gameManager = new GameManager();
    }

    @Test
    void testPrepareRound() {
        this.gameManager.prepareRound();

        assertEquals(1, this.gameManager.getCurrentRound());
        assertEquals(1, this.gameManager.getCarts().size());
        assertEquals(5, this.gameManager.getShopItems().size());

        this.gameManager.prepareRound();

        assertEquals(2, this.gameManager.getCurrentRound());
        assertEquals(1, this.gameManager.getCarts().size());
    }

    @Test
    void testStartRound() throws GameException {
        this.gameManager.prepareRound();
        this.gameManager.getInventory().addActiveTower(new Tower(Resource.WATER));
        this.gameManager.getInventory().addActiveTower(new Tower(Resource.WOOD));
        this.gameManager.getInventory().addActiveTower(new Tower(Resource.FOOD));

        RoundInformation roundOneInformation = this.gameManager.startRound();
        assertTrue(roundOneInformation.getWon());

        this.gameManager.addCart(new Cart(100, 10, Resource.WATER));

        RoundInformation roundTwoInformation = this.gameManager.startRound();
        assertTrue(roundTwoInformation.getWon());

    }

    @Test
    void testSetRoundCount() throws GameException {
        GameException errorOne = assertThrows(GameException.class, () -> {
            this.gameManager.setRoundCount(20);
        });

        assertEquals("Amount of rounds is not between 5 and 15.", errorOne.getMessage());

        GameException errorTwo = assertThrows(GameException.class, () -> {
            this.gameManager.setRoundCount(1);
        });

        assertEquals("Amount of rounds is not between 5 and 15.", errorTwo.getMessage());

        this.gameManager.setRoundCount(7);
        assertEquals(7, this.gameManager.getRoundCount());
    }

    @Test
    void testRemoveMoney() throws GameException {
        this.gameManager.removeMoney(10);
        assertEquals(190, this.gameManager.getMoney());

        GameException error = assertThrows(GameException.class, () -> {
            this.gameManager.removeMoney(200);
        });

        assertEquals("You do not have enough money to buy this.", error.getMessage());
    }

    @Test
    void testLevelUp() {
        this.gameManager.addExperience(15);

        assertEquals(2, this.gameManager.getLevel());
    }
}
