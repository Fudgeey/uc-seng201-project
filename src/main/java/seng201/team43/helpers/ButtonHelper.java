package seng201.team43.helpers;

import javafx.scene.control.Button;

public class ButtonHelper {
    public static void setBackground(Button button, String colour) {
        button.setStyle(String.format("-fx-background-color: %s; -fx-background-radius: 5;", colour));
    }
}
