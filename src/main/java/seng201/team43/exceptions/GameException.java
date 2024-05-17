package seng201.team43.exceptions;

import javafx.scene.control.Control;
import seng201.team43.helpers.PopupHelper;

/**
 * Custom error class with a custom popup
 * @author Luke Hallett, Riley Jeffcote
 */
public class GameException extends Exception {
    private final String message;

    public GameException(String message) {
        super();

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Displays the error in a popup screen.
     * @param control
     */
    public void displayError(Control control) {
        PopupHelper.display(control, this.getMessage());
    }
}
