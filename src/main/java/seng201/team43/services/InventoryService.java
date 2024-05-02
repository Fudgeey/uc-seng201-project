package seng201.team43.services;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import seng201.team43.gui.factories.TowerCellFactory;
import seng201.team43.gui.factories.UpgradeCellFactory;
import seng201.team43.models.GameManager;
import seng201.team43.models.Inventory;
import seng201.team43.models.Tower;
import seng201.team43.models.Upgrade;

/**
 * Inventory service to deal with logic for inventory.
 *
 * @author Luke Hallett, Riley Jeffcote
 */
public class InventoryService {
    private final GameManager gameManager;
    private Tower selectedTower;
    private final Button moveTowerButton;

    public InventoryService(GameManager gameManager, Button moveTowerButton) {
        this.gameManager = gameManager;
        this.moveTowerButton = moveTowerButton;
    }

    public void updateViews(ListView<Tower> activeTowersListView, ListView<Tower> reserveTowersListView, ListView<Upgrade> upgradesListView) {
        activeTowersListView.setCellFactory(new TowerCellFactory());
        activeTowersListView.setItems(FXCollections.observableArrayList(this.gameManager.getInventory().getActiveTowers()));

        reserveTowersListView.setCellFactory(new TowerCellFactory());
        reserveTowersListView.setItems(FXCollections.observableArrayList(this.gameManager.getInventory().getReserveTowers()));

        upgradesListView.setCellFactory(new UpgradeCellFactory());
        upgradesListView.setItems(FXCollections.observableArrayList(this.gameManager.getInventory().getUpgrades()));

        activeTowersListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Tower>) t -> {
            this.moveTowerButton.setVisible(true);
            this.selectedTower = activeTowersListView.getSelectionModel().getSelectedItem();
        });

        reserveTowersListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Tower>) t -> {
            this.moveTowerButton.setVisible(true);
            this.selectedTower = reserveTowersListView.getSelectionModel().getSelectedItem();
        });
    }

    public void moveTower() {
        Inventory inventory = this.gameManager.getInventory();

        // Check if tower is in active or reserve towers.
        if(inventory.getActiveTowers().contains(this.selectedTower)) {
            inventory.removeActiveTower(this.selectedTower);
            inventory.addReserveTower(this.selectedTower);
        } else {
            inventory.removeReserveTower(this.selectedTower);
            inventory.addActiveTower(this.selectedTower);
        }

        this.moveTowerButton.setVisible(false);
    }
}
