package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import seng201.team43.exceptions.GameError;
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

    @FXML
    private Button backButton;

    @FXML
    private Button pauseButton;

    @FXML
    private GridPane outerGrid;

    public ShopScreenController(GameManager gameManager) {
        this.shopService = new ShopService(gameManager);
    }

    public void initialize() {
        backButton.setOnAction(event -> shopService.close());
        pauseButton.setOnAction(event -> shopService.pause());

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
                } catch (GameError e) {
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

        descriptionLabel.setFont(new Font(20));

        buyButton.setFont(new Font(25));

        priceLabel.setFont(new Font(20));

        towerPane.getChildren().addAll(descriptionLabel, buyButton, priceLabel);
        return towerPane;
    }
}
