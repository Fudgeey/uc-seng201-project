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
import seng201.team43.models.Tower;

import java.util.Objects;

/**
 * Cell factory for tower to use in ListView
 * @author seng201 teaching team, Luke Hallett
 */
public class TowerCellFactory implements Callback<ListView<Tower>, ListCell<Tower>> {
    @Override
    public ListCell<Tower> call(ListView<Tower> towerListView) {
        return new ListCell<>() {
            @Override
            public void updateItem(Tower tower, boolean empty) {
                super.updateItem(tower, empty);

                if(empty || tower == null) {
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

                    Label nameLabel = new Label(tower.getName());
                    nameLabel.setFont(new Font(30));

                    Label resourceTypeLabel = new Label(tower.getResourceType().label);
                    resourceTypeLabel.setFont(new Font(25));

                    ImageView resourceImage = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(String.format("/images/towers/%s.png", tower.getResourceType().label.toLowerCase())))));
                    resourceImage.setFitWidth(140);
                    resourceImage.setPreserveRatio(true);

                    Label statsLabel = new Label(String.format("Production Speed: %s units\nReload Speed: %s seconds\nSell Price: $%s", tower.getProductionUnits(), tower.getReloadSpeed(), tower.getSellPrice()));
                    statsLabel.setFont(new Font(15));

                    GridPane.setConstraints(statsLabel, 1, 0);

                    flowPane.getChildren().addAll(nameLabel, resourceTypeLabel, resourceImage);
                    gridPane.getChildren().addAll(flowPane, statsLabel);

                    setGraphic(gridPane);
                }
            }
        };
    }
}
