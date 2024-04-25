package seng201.team43.components;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import seng201.team43.models.Tower;

import java.util.Objects;

public class TowerCard {
    Tower tower;

    public TowerCard(Tower tower) {
        this.tower = tower;
    }

    public GridPane build() {
        GridPane outerGridPane = new GridPane();
        FlowPane towerCard = new FlowPane();

        outerGridPane.addColumn(1);
        outerGridPane.addRow(1);

        GridPane.setValignment(towerCard, VPos.CENTER);
        GridPane.setHalignment(towerCard, HPos.CENTER);

        towerCard.setOrientation(Orientation.VERTICAL);
        towerCard.setAlignment(Pos.CENTER);
        towerCard.setColumnHalignment(HPos.CENTER);

        Label nameLabel = new Label(tower.getName());
        nameLabel.setFont(new Font(30));

        Label resourceTypeLabel = new Label(tower.getResourceType().label);
        resourceTypeLabel.setFont(new Font(25));

        ImageView resourceImage = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(String.format("/images/towers/%s.jpg", tower.getResourceType().label.toLowerCase())))));
        resourceImage.setFitWidth(180);
        resourceImage.setPreserveRatio(true);

        towerCard.getChildren().add(nameLabel);
        towerCard.getChildren().add(resourceTypeLabel);
        towerCard.getChildren().add(resourceImage);

        outerGridPane.getChildren().add(towerCard);

        return outerGridPane;
    }
}
