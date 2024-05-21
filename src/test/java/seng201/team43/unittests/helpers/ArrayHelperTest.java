package seng201.team43.unittests.helpers;

import org.junit.jupiter.api.Test;
import seng201.team43.helpers.ArrayHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayHelperTest {
    @Test
    void testFindNextSlot() {
        String[] testArray = new String[3];

        int nextFreeSlot = ArrayHelper.findNextSlot(testArray);
        assertEquals(0, nextFreeSlot);
    }

    @Test
    void testFindNextSlotFull() {
        String[] testArray = {"Test", "Test", "Test"};

        int nextFreeSlot = ArrayHelper.findNextSlot(testArray);
        assertEquals(-1, nextFreeSlot);
    }
}
