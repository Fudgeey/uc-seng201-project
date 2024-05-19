package seng201.team43.gui.factories;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.util.Callback;
import seng201.team43.models.Upgrade;

/**
 * Cell factory for tower to use in ListView
 * @author seng201 teaching team, Luke Hallett, Riley Jeffcote
 */
public class UpgradeCellFactory implements Callback<ListView<Upgrade>, ListCell<Upgrade>>  {
    /**
     * Updates the list view with the required list
     * @param upgradeListView the list view to update
     * @return cells for the list view
     */
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

                    gridPane.setMaxHeight(150);

                    GridPane.setValignment(flowPane, VPos.CENTER);
                    GridPane.setHalignment(flowPane, HPos.CENTER);
                    GridPane.setConstraints(flowPane, 0, 0);
                    GridPane.setVgrow(flowPane, Priority.ALWAYS);
                    GridPane.setHgrow(flowPane, Priority.ALWAYS);

                    flowPane.setAlignment(Pos.CENTER);
                    flowPane.setOrientation(Orientation.VERTICAL);
                    flowPane.setColumnHalignment(HPos.CENTER);

                    Label nameLabel = new Label(upgrade.getName());
                    nameLabel.setFont(new Font(30));

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
