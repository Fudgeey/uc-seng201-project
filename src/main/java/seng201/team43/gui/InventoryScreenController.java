package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team43.models.GameManager;

/**
 * Controller for the inventory_screen.fxml window
 *
 * @author Riley Jeffcote
 */
public class InventoryScreenController {
    private final GameManager gameManager;
    @FXML
    private Button backButton;
    @FXML
    private Button shopButton;

    public InventoryScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void initialize() {
        backButton.setOnAction(event -> {
            gameManager.closeInventoryScreen();
        });
        shopButton.setOnAction(event -> {
            gameManager.openShopScreen();
        });
    }
}
