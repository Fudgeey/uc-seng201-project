package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team43.models.GameManager;
import seng201.team43.services.InventoryService;
import seng201.team43.services.ShopService;
/**
 * Controller for the shop_screen.fxml window
 *
 * @author Riley Jeffcote
 */
public class ShopScreenController {
    private final GameManager gameManager;
    private final ShopService shopService;

    @FXML
    private Button backButton;

    public ShopScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.shopService = new ShopService(this.gameManager);
    }
    public void initialize() {
        backButton.setOnAction(event -> {
            gameManager.closeShopScreen();
        });

    }
}
