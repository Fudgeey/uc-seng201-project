package seng201.team43.components;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import seng201.team43.models.Tower;

import java.util.Objects;

public class TowerCard {
    Tower tower;

    public TowerCard(Tower tower) {
        this.tower = tower;
    }

    public FlowPane build() {
        FlowPane flowPane = new FlowPane();

        flowPane.setAlignment(Pos.CENTER);
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setColumnHalignment(HPos.CENTER);

        Label nameLabel = new Label(tower.getName());
        nameLabel.setFont(new Font(30));

        Label resourceTypeLabel = new Label(tower.getResourceType().label);
        resourceTypeLabel.setFont(new Font(25));

        ImageView resourceImage = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(String.format("/images/towers/%s.png", tower.getResourceType().label.toLowerCase())))));
        resourceImage.setFitWidth(180);
        resourceImage.setPreserveRatio(true);

        flowPane.getChildren().addAll(nameLabel, resourceTypeLabel, resourceImage);

        return flowPane;
    }

    public FlowPane buildGame() {
        FlowPane flowPane = new FlowPane();

        flowPane.setAlignment(Pos.CENTER);
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setColumnHalignment(HPos.CENTER);
        flowPane.setPrefSize(150.0, 150.0);
        flowPane.setBackground(Background.fill(Paint.valueOf("#ffffff")));

        Label nameLabel = new Label(tower.getName());
        nameLabel.setFont(new Font(15));

        ImageView resourceImage = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(String.format("/images/towers/%s.png", tower.getResourceType().label.toLowerCase())))));
        resourceImage.setFitWidth(100);
        resourceImage.setPreserveRatio(true);

        flowPane.getChildren().addAll(nameLabel, resourceImage);

        return flowPane;
    }
}
