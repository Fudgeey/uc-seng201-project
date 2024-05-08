package seng201.team43.gui.factories;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.util.Callback;
import seng201.team43.models.Upgrade;

import java.util.Objects;

public class UpgradeCellFactory implements Callback<ListView<Upgrade>, ListCell<Upgrade>>  {
    @Override
    public ListCell<Upgrade> call(ListView<Upgrade> upgradeListView) {
        return new ListCell<>() {
            @Override
            public void updateItem(Upgrade upgrade, boolean empty) {
                super.updateItem(upgrade, empty);

                if (empty || upgrade == null) {
                    setGraphic(null);
                } else {
                    GridPane gridPane = new GridPane();
                    FlowPane flowPane = new FlowPane();

                    gridPane.setMaxHeight(240);

                    ColumnConstraints columnConstraintOne = new ColumnConstraints();
                    ColumnConstraints columnConstraintTwo = new ColumnConstraints();

                    columnConstraintOne.setPercentWidth(50);
                    columnConstraintTwo.setPercentWidth(50);

                    gridPane.getColumnConstraints().addAll(columnConstraintOne, columnConstraintTwo);

                    GridPane.setValignment(flowPane, VPos.CENTER);
                    GridPane.setHalignment(flowPane, HPos.CENTER);
                    GridPane.setConstraints(flowPane, 0, 0);
                    GridPane.setVgrow(flowPane, Priority.ALWAYS);
                    GridPane.setHgrow(flowPane, Priority.ALWAYS);

                    flowPane.setAlignment(Pos.CENTER_RIGHT);
                    flowPane.setOrientation(Orientation.VERTICAL);
                    flowPane.setColumnHalignment(HPos.CENTER);

                    Label nameLabel = new Label(upgrade.getName());
                    nameLabel.setFont(new Font(30));

//                    ImageView resourceImage = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(String.format("/images/towers/%s.png", tower.getResourceType().label.toLowerCase())))));
//                    resourceImage.setFitWidth(140);
//                    resourceImage.setPreserveRatio(true);

                    Label descriptionLabel = new Label(upgrade.getDescription());
                    descriptionLabel.setFont(new Font(15));

                    GridPane.setConstraints(descriptionLabel, 1, 0);

                    flowPane.getChildren().addAll(nameLabel, descriptionLabel);
                    gridPane.getChildren().addAll(flowPane);

                    setGraphic(gridPane);
                }
            }
        };
    }
}
