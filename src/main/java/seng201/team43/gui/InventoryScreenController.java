package seng201.team43.gui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import seng201.team43.exceptions.GameException;
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
    private final InventoryService inventoryService;
    private final GUIManager guiManager;

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

    @FXML
    private Button sellButton;

    @FXML
    private Button assignUpgradeButton;

    /**
     * Initialises the inventory screen controller
     * @param gameManager persistent game manager to use
     * @param guiManager Class for managing JavaFX scenes
     */
    public InventoryScreenController(GameManager gameManager, GUIManager guiManager) {
        this.inventoryService = new InventoryService(gameManager);
        this.guiManager = guiManager;
    }

    /**
     * Initialises the JavaFX scene, sets visuals and actions
     */
    public void initialize() {
        backButton.setOnAction(event -> this.guiManager.openGameScreen());
        shopButton.setOnAction(event -> this.guiManager.openShopScreen());

        activeTowersListView.setCellFactory(new TowerCellFactory());
        reserveTowersListView.setCellFactory(new TowerCellFactory());
        upgradesListView.setCellFactory(new UpgradeCellFactory());

        this.updateListViewItems();

        this.setupTowerListViewListener(activeTowersListView);
        this.setupTowerListViewListener(reserveTowersListView);

        upgradesListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Upgrade>) u -> {
            this.sellButton.setVisible(true);

            if(this.inventoryService.getSelectedTower() != null) {
                this.assignUpgradeButton.setVisible(true);
            }

            this.inventoryService.setSelectedUpgrade(upgradesListView.getSelectionModel().getSelectedItem());
            this.inventoryService.setLastSelectedItem(upgradesListView.getSelectionModel().getSelectedItem());
        });

        moveTowerButton.setOnAction(event -> {
            try {
                this.inventoryService.moveTower();
                this.updateListViewItems();

                activeTowersListView.getSelectionModel().clearSelection();
                reserveTowersListView.getSelectionModel().clearSelection();

                moveTowerButton.setVisible(false);
            } catch (GameException e) {
                e.displayError(moveTowerButton);
            }
        });

        assignUpgradeButton.setOnAction(event -> {
            try {
                this.inventoryService.applyUpgrade();
            } catch(GameException e) {
                e.displayError(assignUpgradeButton);
            }

            this.updateListViewItems();

            activeTowersListView.getSelectionModel().clearSelection();
            reserveTowersListView.getSelectionModel().clearSelection();
            upgradesListView.getSelectionModel().clearSelection();

            moveTowerButton.setVisible(false);
            sellButton.setVisible(false);
            assignUpgradeButton.setVisible(false);
        });

        sellButton.setOnAction(event -> {
            try {
                this.inventoryService.sellItem();
            } catch (GameException e) {
                e.displayError(sellButton);
            }
            this.updateListViewItems();

            activeTowersListView.getSelectionModel().clearSelection();
            reserveTowersListView.getSelectionModel().clearSelection();
            upgradesListView.getSelectionModel().clearSelection();

            moveTowerButton.setVisible(false);
            sellButton.setVisible(false);
            assignUpgradeButton.setVisible(false);
        });
    }

    /**
     * Updates the active towers, reserve towers, and upgrades list views.
     */
    private void updateListViewItems() {
        activeTowersListView.setItems(FXCollections.observableArrayList(this.inventoryService.getActiveTowers()));
        reserveTowersListView.setItems(FXCollections.observableArrayList(this.inventoryService.getReserveTowers()));
        upgradesListView.setItems(FXCollections.observableArrayList(this.inventoryService.getUpgrades()));
    }

    /**
     * Listener for active and reserve towers and calls logic based on selection
     * @param listView list view to set up listener for
     */
    private void setupTowerListViewListener(ListView<Tower> listView) {
        listView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Tower>) t -> {
            this.moveTowerButton.setVisible(true);
            this.sellButton.setVisible(true);

            if(this.inventoryService.getSelectedUpgrade() != null) {
                this.assignUpgradeButton.setVisible(true);
            }

            this.inventoryService.setSelectedTower(listView.getSelectionModel().getSelectedItem());
            this.inventoryService.setLastSelectedItem(listView.getSelectionModel().getSelectedItem());
        });
    }
}
