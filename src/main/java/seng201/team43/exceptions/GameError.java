package seng201.team43.exceptions;

import javafx.scene.control.Alert;

public class GameError extends Exception {
    private final String message;

    public GameError(String message) {
        super();

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void displayError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Tower Dynasty");
        alert.setContentText(this.message);
        alert.showAndWait();
    }
}
