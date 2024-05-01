package seng201.team43.gui.factories;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import seng201.team43.models.Upgrade;

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
                    Label testLabel = new Label("Test Upgrade");
                    setGraphic(testLabel);
                }
            }
        };
    }
}
