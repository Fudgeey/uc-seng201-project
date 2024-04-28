package seng201.team43.components;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        FlowPane innerFlowPlane = new FlowPane();

        GridPane.setValignment(innerFlowPlane, VPos.CENTER);
        GridPane.setHalignment(innerFlowPlane, HPos.CENTER);
        GridPane.setConstraints(innerFlowPlane, 0, 0);
        GridPane.setVgrow(innerFlowPlane, Priority.ALWAYS);
        GridPane.setHgrow(innerFlowPlane, Priority.ALWAYS);

        innerFlowPlane.setAlignment(Pos.CENTER);
        innerFlowPlane.setOrientation(Orientation.VERTICAL);
        innerFlowPlane.setColumnHalignment(HPos.CENTER);

        Label nameLabel = new Label(tower.getName());
        nameLabel.setFont(new Font(30));

        Label resourceTypeLabel = new Label(tower.getResourceType().label);
        resourceTypeLabel.setFont(new Font(25));

        ImageView resourceImage = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(String.format("/images/towers/%s.jpg", tower.getResourceType().label.toLowerCase())))));
        resourceImage.setFitWidth(180);
        resourceImage.setPreserveRatio(true);

        innerFlowPlane.getChildren().addAll(nameLabel, resourceTypeLabel, resourceImage);
        outerGridPane.getChildren().add(innerFlowPlane);

        return outerGridPane;
    }
}
