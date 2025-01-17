package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import seng201.team43.exceptions.GameException;
import seng201.team43.models.*;
import seng201.team43.services.ShopService;

import java.util.List;

/**
 * Controller for the shop_screen.fxml window
 *
 * @author Riley Jeffcote, Luke Hallett
 */

public class ShopScreenController {
    private final ShopService shopService;
    private final GUIManager guiManager;

    @FXML
    private Button backButton;

    @FXML
    private Label moneyLabel;

    @FXML
    private GridPane outerGrid;

    /**
     * Initialises the shop screen controller
     * @param gameManager persistent game manager to use
     * @param guiManager Class for managing JavaFX scenes
     */
    public ShopScreenController(GameManager gameManager, GUIManager guiManager) {
        this.shopService = new ShopService(gameManager);
        this.guiManager = guiManager;
    }

    /**
     * JavaFX initialise function. Sets up all actions and displays all shop items.
     */
    public void initialize() {
        backButton.setOnAction(event -> this.guiManager.openInventoryScreen());
        this.updateMoney();

        List<Purchasable> items = this.shopService.getShopItems();

        int i = 0;
        for(Purchasable item : items) {
            FlowPane itemPane = getItemPane(item);
            outerGrid.add(itemPane, i, 1);

            Button buyButton = (Button) itemPane.getChildren().get(1);

            if(item.getPurchased()) {
                buyButton.setDisable(true);
            }

            buyButton.setOnAction(event -> {
                try {
                    this.shopService.buyItem(item);
                    buyButton.setDisable(true);
                    this.updateMoney();
                } catch (GameException e) {
                    e.displayError(buyButton);
                }
            });

            i++;
        }
    }

    private FlowPane getItemPane(Purchasable item) {
        FlowPane towerPane = new FlowPane();
        Label descriptionLabel = new Label(item.getDescription());
        Button buyButton = new Button(item.getName());
        Label priceLabel = new Label(String.format("Price: $%s", item.getCost()));

        towerPane.setOrientation(Orientation.VERTICAL);
        towerPane.setAlignment(Pos.CENTER);
        towerPane.setColumnHalignment(HPos.CENTER);

        descriptionLabel.setStyle("-fx-font-size: 20; -fx-text-alignment: center; -fx-text-fill: white;");
        descriptionLabel.setWrapText(true);
        descriptionLabel.setPrefWidth(380);
        descriptionLabel.setAlignment(Pos.CENTER);

        buyButton.setStyle("-fx-font-size: 25");
        priceLabel.setStyle("-fx-font-size: 20; -fx-text-fill: white;");

        towerPane.getChildren().addAll(descriptionLabel, buyButton, priceLabel);
        return towerPane;
    }

    /**
     * Updates the money to a new amount by calling this.shopService.getMoney().
     */
    private void updateMoney() {
        moneyLabel.setText(String.format("Money: $%.2f", this.shopService.getMoney()));
    }
}
