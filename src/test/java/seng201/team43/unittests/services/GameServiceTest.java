package seng201.team43.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team43.exceptions.GameException;
import seng201.team43.models.*;
import seng201.team43.models.enums.Resource;
import seng201.team43.services.GameService;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {
    private GameManager gameManager;
    private GameService gameService;

    @BeforeEach
    void setupTest() {
        Random random = new Random(1);
        this.gameManager = new GameManager();
        this.gameService = new GameService(this.gameManager, random);
    }

    @Test
    void testRunRandomEvents() throws GameException {
        this.gameManager.getInventory().addActiveTower(new Tower(Resource.WATER));
        this.gameManager.addCart(new Cart(100, 10, Resource.WATER));

        List<String> events = this.gameService.runRandomEvents();

        assertEquals("A water tower was broken.", events.getFirst());
    }

    @Test
    void testGetRemainingRounds() throws GameException {
        this.gameManager.setRoundCount(5);
        this.gameManager.prepareRound();
        this.gameManager.startRound();
        this.gameManager.prepareRound();

        assertEquals(4, this.gameService.getRemainingRounds());
    }

    @Test
    void testGameEnded() throws GameException {
        this.gameManager.setRoundCount(5);

        this.gameManager.prepareRound();
        this.gameManager.startRound();

        assertFalse(this.gameService.gameEnded());

        this.gameManager.prepareRound();
        this.gameManager.startRound();
        this.gameManager.prepareRound();
        this.gameManager.startRound();
        this.gameManager.prepareRound();
        this.gameManager.startRound();
        this.gameManager.prepareRound();
        this.gameManager.startRound();

        assertTrue(this.gameService.gameEnded());
    }

    @Test
    void testGetNonBrokenActiveTowers() throws GameException {
        this.gameManager.getInventory().addActiveTower(new Tower(Resource.WATER));
        this.gameManager.getInventory().addActiveTower(new Tower(Resource.FOOD));

        this.gameManager.getInventory().getActiveTowers().get(0).setBroken(true);

        assertEquals(1, this.gameService.getActiveTowers().size());
    }

    @Test
    void testPrepareRound() {
        this.gameManager.addCart(new Cart(100, 10, Resource.WATER));
        this.gameService.getCarts().getFirst().setCurrentFilled(100);

        this.gameService.prepareRound();

        assertEquals(0, this.gameService.getCarts().getFirst().getCurrentFilled());
    }
}
