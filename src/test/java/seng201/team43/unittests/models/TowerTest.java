package seng201.team43.unittests.models;

import org.junit.jupiter.api.Test;
import seng201.team43.models.Resource;
import seng201.team43.models.Tower;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TowerTest {
    @Test
    void testLevelDownBelowOne() {
        Tower tower = new Tower(Resource.WOOD);
        tower.levelDown();

        assertEquals(1, tower.getLevel());
        assertEquals(50, tower.getProductionUnits());
    }

    @Test
    void testLevelDown() {
        Tower tower = new Tower(Resource.WOOD);

        tower.addExperience(20);
        tower.levelDown();

        assertEquals(2, tower.getLevel());
        assertEquals(75, tower.getProductionUnits());
    }
}
