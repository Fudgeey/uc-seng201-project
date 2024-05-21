package seng201.team43.helpers;

public class ArrayHelper {
    /**
     * Helper function to find next empty slot in array.
     * @param array array to find next empty slot
     * @return the index of the next empty slot
     */
    public static <T> int findNextSlot(T[] array) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == null) {
                return i;
            }
        }

        return -1;
    }
}
