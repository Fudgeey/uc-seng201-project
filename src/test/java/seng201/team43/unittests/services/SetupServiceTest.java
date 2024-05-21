package seng201.team43.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team43.exceptions.GameException;
import seng201.team43.models.GameManager;
import seng201.team43.models.Resource;
import seng201.team43.models.Tower;
import seng201.team43.services.SetupService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SetupServiceTest {
    private GameManager gameManager;
    private SetupService setupService;

    @BeforeEach
    void setupTest() {
        this.gameManager = new GameManager();
        this.setupService = new SetupService(this.gameManager);
    }

    @Test
    void testStartGame() throws GameException {
        GameException errorOne = assertThrows(GameException.class, () -> this.setupService.startGame());
        assertEquals("A name is required.", errorOne.getMessage());

        this.setupService.setName("Test!");

        GameException errorTwo = assertThrows(GameException.class, () -> this.setupService.startGame());
        assertEquals("Your name must not contains special characters.", errorTwo.getMessage());

        this.setupService.setName("T");

        GameException errorThree = assertThrows(GameException.class, () -> this.setupService.startGame());
        assertEquals("Your name must be between 3-15 characters.", errorThree.getMessage());

        this.setupService.setName("TestTestTestTest");

        GameException errorFour = assertThrows(GameException.class, () -> this.setupService.startGame());
        assertEquals("Your name must be between 3-15 characters.", errorFour.getMessage());

        this.setupService.setName("Test");

        GameException errorFive = assertThrows(GameException.class, () -> this.setupService.startGame());
        assertEquals("At least one starting tower is required.", errorFive.getMessage());

        this.setupService.setStartingTower(0, new Tower(Resource.WATER));

        this.setupService.startGame();
    }
}
