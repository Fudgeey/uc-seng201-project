package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team43.models.*;
import seng201.team43.services.ShopService;

/**
 * Controller for the shop_screen.fxml window
 *
 * @author Riley Jeffcote, Luke Hallett
 */

public class ShopScreenController {
    private final GameManager gameManager;
    private final ShopService shopService;

    @FXML
    private Button backButton;

    @FXML
    private Button productionUpgradeButton;

    @FXML
    private Button reloadUpgradeButton;

    @FXML
    private Button changeTypeUpgradeButton;

    @FXML
    private Button waterTowerButton;

    @FXML
    private Button woodTowerButton;

    @FXML
    private Button foodTowerButton;

    public ShopScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.shopService = new ShopService(this.gameManager);
    }

    public void initialize() {
        backButton.setOnAction(event -> gameManager.closeShopScreen());

        productionUpgradeButton.setOnAction(event -> this.shopService.buyItem(new ProductionUpgrade()));
        reloadUpgradeButton.setOnAction(event -> this.shopService.buyItem(new ReloadUpgrade()));
        changeTypeUpgradeButton.setOnAction(event -> this.shopService.buyItem(new ResourceTypeUpgrade()));

        waterTowerButton.setOnAction(event -> this.shopService.buyItem(new Tower(Resource.WATER)));
        woodTowerButton.setOnAction(event -> this.shopService.buyItem(new Tower(Resource.WOOD)));
        foodTowerButton.setOnAction(event -> this.shopService.buyItem(new Tower(Resource.FOOD)));
    }
}
