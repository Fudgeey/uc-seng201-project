package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team43.exceptions.GameError;
import seng201.team43.models.*;
import seng201.team43.services.ShopService;

import java.util.List;

/**
 * Controller for the shop_screen.fxml window
 *
 * @author Riley Jeffcote, Luke Hallett
 */

public class ShopScreenControllerOld {
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

    public ShopScreenControllerOld(GameManager gameManager) {
        this.gameManager = gameManager;
        this.shopService = new ShopService(this.gameManager);
    }

    public void initialize() {
        List<Button> itemButtons = List.of(productionUpgradeButton, reloadUpgradeButton, changeTypeUpgradeButton, waterTowerButton, woodTowerButton, foodTowerButton);

        for(Button button : itemButtons) {
            button.setOnAction(event -> {
                Purchasable item = switch (button.getText()) {
                    case "Production +10" -> new ProductionUpgrade(10);
                    case "Reload -1s" -> new ReloadUpgrade();
                    case "Change Type" -> new ResourceTypeUpgrade();
                    case "Water Tower" -> new Tower(Resource.WATER);
                    case "Wood Tower" -> new Tower(Resource.WOOD);
                    case "Food Tower" -> new Tower(Resource.FOOD);
                    default -> null;
                };

                try {
                    this.shopService.buyItem(item);
                } catch (GameError e) {
                    e.displayError(button);
                }
            });
        }

        backButton.setOnAction(event -> gameManager.closeShopScreen());
    }
}
