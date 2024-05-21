package seng201.team43.unittests.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team43.exceptions.GameException;
import seng201.team43.helpers.RoundInformation;
import seng201.team43.models.GameManager;
import seng201.team43.models.Resource;
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

        RoundInformation roundInformation = this.gameManager.startRound();

        assertTrue(roundInformation.getWon());
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
}
