package seng201.team43.gui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import seng201.team43.gui.factories.TowerCellFactory;
import seng201.team43.gui.factories.UpgradeCellFactory;
import seng201.team43.models.GameManager;
import seng201.team43.models.Tower;
import seng201.team43.models.Upgrade;
import seng201.team43.services.InventoryService;

/**
 * Controller for the inventory_screen.fxml window
 *
 * @author Riley Jeffcote, Luke Hallett
 */
public class InventoryScreenController {
    private final GameManager gameManager;
    private final InventoryService inventoryService;

    @FXML
    private Button backButton;

    @FXML
    private Button shopButton;

    @FXML
    private ListView<Tower> activeTowersListView;

    @FXML
    private ListView<Tower> reserveTowersListView;

    @FXML
    private ListView<Upgrade> upgradesListView;

    @FXML
    private Button moveTowerButton;

    public InventoryScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.inventoryService = new InventoryService(this.gameManager);
    }

    public void initialize() {
        backButton.setOnAction(event -> gameManager.closeInventoryScreen());
        shopButton.setOnAction(event -> gameManager.openShopScreen());

        activeTowersListView.setCellFactory(new TowerCellFactory());
        reserveTowersListView.setCellFactory(new TowerCellFactory());
        upgradesListView.setCellFactory(new UpgradeCellFactory());

        this.updateListViewItems();

        activeTowersListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Tower>) t -> {
            this.moveTowerButton.setVisible(true);
            this.inventoryService.setSelectedTower(activeTowersListView.getSelectionModel().getSelectedItem());
        });

        reserveTowersListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Tower>) t -> {
            this.moveTowerButton.setVisible(true);
            this.inventoryService.setSelectedTower(reserveTowersListView.getSelectionModel().getSelectedItem());
        });

        moveTowerButton.setOnAction(event -> {
            this.inventoryService.moveTower();
            this.updateListViewItems();

            activeTowersListView.getSelectionModel().clearSelection();
            reserveTowersListView.getSelectionModel().clearSelection();

            moveTowerButton.setVisible(false);
        });
    }

    private void updateListViewItems() {
        activeTowersListView.setItems(FXCollections.observableArrayList(this.gameManager.getInventory().getActiveTowers()));
        reserveTowersListView.setItems(FXCollections.observableArrayList(this.gameManager.getInventory().getReserveTowers()));
        upgradesListView.setItems(FXCollections.observableArrayList(this.gameManager.getInventory().getUpgrades()));
    }
}
