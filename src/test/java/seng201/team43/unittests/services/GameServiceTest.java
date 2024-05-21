package seng201.team43.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team43.exceptions.GameException;
import seng201.team43.models.GameManager;
import seng201.team43.services.GameService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameServiceTest {
    private GameManager gameManager;
    private GameService gameService;

    @BeforeEach
    void setupTest() {
        this.gameManager = new GameManager();
        this.gameService = new GameService(this.gameManager);
    }

//    @Test
//    void testRunRandomEvents() {
//        List<String> events = this.gameService.runRandomEvents();
//    }

    @Test
    void testGetRemainingRounds() throws GameException {
        this.gameManager.setRoundCount(5);
        this.gameManager.prepareRound();
        this.gameManager.startRound();
        this.gameManager.prepareRound();

        assertEquals(4, this.gameService.getRemainingRounds());
    }
}
