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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Window;

public class PopupHelper {
    public static void display(Control control, String message) {
        Popup popup = new Popup();
        GridPane popupContent = new GridPane();
        ImageView popupBackground = new ImageView(new Image(String.valueOf(PopupHelper.class.getResource("/images/popup.png"))));

        Label testLabel = new Label(message);
        testLabel.setFont(new Font(20));
        testLabel.setStyle("-fx-text-fill: white;");

        GridPane.setConstraints(popupBackground, 0, 0);
        GridPane.setConstraints(testLabel, 0, 0);
        GridPane.setHalignment(popupBackground, HPos.CENTER);
        GridPane.setHalignment(testLabel, HPos.CENTER);

        Button removeButton = new Button("X");
        GridPane.setValignment(removeButton, VPos.TOP);
        GridPane.setHalignment(removeButton, HPos.RIGHT);
        GridPane.setMargin(removeButton, new Insets(75, 50, 10, 10));
        removeButton.setStyle("-fx-background-color: red; -fx-background-radius: 100%");
        removeButton.setTextFill(Paint.valueOf("white"));

        removeButton.setOnAction(event -> popup.hide());

        popupContent.getChildren().addAll(popupBackground, testLabel, removeButton);

        popup.getContent().add(popupContent);
        Window stage = control.getScene().getWindow();

        double anchorX = (stage.getWidth() - popup.getWidth()) / 2.0;
        double anchorY = (stage.getHeight() - popup.getHeight()) / 2.0;

        popup.show(stage, anchorX, anchorY);
    }
}