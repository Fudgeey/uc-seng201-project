package seng201.team43.helpers;

import javafx.scene.control.Button;

/**
 * Helper class for useful button functions
 * @author Luke Hallett, Rileyjeffcote
 */
public class ButtonHelper {
    /**
     * Function to set the background colour of a button
     * @param button button to set background colour of
     * @param colour colour to set
     */
    public static void setBackground(Button button, String colour) {
        button.setStyle(String.format("-fx-background-color: %s; -fx-background-radius: 5;", colour));
    }
}
