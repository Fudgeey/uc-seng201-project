package seng201.team43.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team43.models.GameManager;
import seng201.team43.services.SetupService;

class SetupServiceTest {
    private GameManager gameManager;
    private SetupService setupService;

    @BeforeEach
    void setupTest() {
        this.gameManager = new GameManager();
        this.setupService = new SetupService(this.gameManager);
    }

//    @Test
//    void testFindNextSlot() {
//        int nextSlot = this.setupService.findNextSlot()
//    }
}
