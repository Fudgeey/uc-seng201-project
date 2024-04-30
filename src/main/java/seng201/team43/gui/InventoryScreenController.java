package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import seng201.team43.models.GameManager;
import seng201.team43.models.Tower;
import seng201.team43.models.Upgrade;
import seng201.team43.services.InventoryService;

import java.util.List;

/**
 * Controller for the inventory_screen_old.fxml window
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


    public InventoryScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.inventoryService = new InventoryService(this.gameManager);
    }

    public void initialize() {
        backButton.setOnAction(event -> gameManager.closeInventoryScreen());
        shopButton.setOnAction(event -> gameManager.openShopScreen());

        this.inventoryService.updateViews(activeTowersListView, reserveTowersListView, upgradesListView);
    }
}
