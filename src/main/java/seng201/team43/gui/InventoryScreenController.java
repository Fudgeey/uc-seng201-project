package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import seng201.team43.models.GameManager;
import seng201.team43.models.Tower;
import seng201.team43.models.Upgrade;
import seng201.team43.services.InventoryService;

/**
 * Controller for the inventory_screen_old.fxml window
 *
 * @author Riley Jeffcote, Luke Hallett
 */
public class InventoryScreenController {
    private final GameManager gameManager;
    private InventoryService inventoryService;

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
    }

    public void initialize() {
        this.inventoryService = new InventoryService(this.gameManager, moveTowerButton);

        backButton.setOnAction(event -> gameManager.closeInventoryScreen());
        shopButton.setOnAction(event -> gameManager.openShopScreen());

        this.updateViews();

        moveTowerButton.setOnAction(event -> {
            this.inventoryService.moveTower();
            moveTowerButton.setVisible(false);
            this.updateViews();
        });
    }

    public void updateViews() {
        this.inventoryService.updateViews(activeTowersListView, reserveTowersListView, upgradesListView);
    }
}
