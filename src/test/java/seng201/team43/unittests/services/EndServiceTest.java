package seng201.team43.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team43.exceptions.GameException;
import seng201.team43.models.GameManager;
import seng201.team43.services.EndService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EndServiceTest {
    private GameManager gameManager;
    private EndService endService;

    @BeforeEach
    void setupTest() {
        this.gameManager = new GameManager();
        this.endService = new EndService(this.gameManager);
    }

    @Test
    void testEndService() throws GameException {
        this.gameManager.setName("Test");
        this.gameManager.setRoundCount(5);
        this.gameManager.prepareRound();

        assertEquals("Test", this.endService.getName());
        assertEquals(200, this.endService.getMoneyGained());
        assertFalse(this.endService.isGameWon());
        assertEquals(1, this.endService.getLevel());
        assertEquals(0, this.endService.getExperienceGained());
        assertEquals(5, this.endService.getRoundCount());
        assertEquals(0, this.endService.getRoundsWon());
    }
}
