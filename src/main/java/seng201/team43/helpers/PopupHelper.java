package seng201.team43.helpers;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Window;

/**
 * Helper class to build and display custom popups
 * @author Luke Hallett, Riley Jeffcote
 */

public class PopupHelper {
    /**
     * Displays the custom popup over the current scene.
     * @param control button / control element popup called from
     * @param message message to be displayed on popup
     */
    public static void display(Control control, String message) {
        Popup popup = new Popup();
        GridPane popupContent = new GridPane();
        ImageView popupBackground = new ImageView(new Image(String.valueOf(PopupHelper.class.getResource("/images/popup.png"))));

        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20; -fx-text-alignment: center");
        messageLabel.setWrapText(true);

        popupContent.setPrefWidth(500);
        GridPane.setConstraints(popupBackground, 0, 0);
        GridPane.setConstraints(messageLabel, 0, 0);
        GridPane.setHalignment(popupBackground, HPos.CENTER);
        GridPane.setHalignment(messageLabel, HPos.CENTER);
        GridPane.setMargin(messageLabel, new Insets(0, 50, 0, 50));

        Button removeButton = new Button("X");
        GridPane.setValignment(removeButton, VPos.TOP);
        GridPane.setHalignment(removeButton, HPos.RIGHT);
        GridPane.setMargin(removeButton, new Insets(75, 50, 10, 10));
        removeButton.setStyle("-fx-background-color: red; -fx-background-radius: 100%; -fx-text-fill: white;");
        removeButton.setOnAction(event -> popup.hide());

        popupContent.getChildren().addAll(popupBackground, messageLabel, removeButton);
        popup.getContent().add(popupContent);
        Window stage = control.getScene().getWindow();

        double anchorX = (stage.getWidth() - popup.getWidth()) / 2.0;
        double anchorY = (stage.getHeight() - popup.getHeight()) / 2.0;

        popup.show(stage, anchorX, anchorY);
    }
}
