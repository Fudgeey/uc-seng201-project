package seng201.team43.services;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import seng201.team43.models.GameManager;
import seng201.team43.models.Tower;

/**
 * Inventory service to deal with logic for inventory
 *
 * @author Luke Hallett
 */
public class InventoryService {
    private final GameManager gameManager;

    public InventoryService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void updateTowers(GridPane mainGridPane) {
        for(int i = 0; i < this.gameManager.getInventory().getActiveTowers().size(); i++) {
            Tower currentTower = this.gameManager.getInventory().getActiveTowers().get(i);
            Integer currentRow = i + 2;

//            mainGridPane.setStyle("-fx-border-style: solid none none none; -fx-border-width: 2; -fx-border-color: black;");

            Label towerNameLabel = new Label(String.format("Name: %s\nType: %s", currentTower.getName(), currentTower.getResourceType().label));
            towerNameLabel.setFont(Font.font(25));
            towerNameLabel.setTextAlignment(TextAlignment.CENTER);
            GridPane.setColumnIndex(towerNameLabel, 0);
            GridPane.setRowIndex(towerNameLabel, currentRow);
            GridPane.setHalignment(towerNameLabel, HPos.CENTER);

            Label reloadSpeedLabel = new Label(String.format("Reload Speed: %s seconds\nProduction Units: %s units", currentTower.getReloadSpeed(), currentTower.getProductionUnits()));
            GridPane.setColumnIndex(reloadSpeedLabel, 3);
            GridPane.setRowIndex(reloadSpeedLabel, currentRow);
            GridPane.setHalignment(reloadSpeedLabel, HPos.CENTER);

            Button reserveButton = new Button("Move");
            reserveButton.setFont(Font.font(20));
            GridPane.setColumnIndex(reserveButton, 4);
            GridPane.setRowIndex(reserveButton, currentRow);
            GridPane.setHalignment(reserveButton, HPos.CENTER);
            GridPane.setValignment(reserveButton, VPos.CENTER);

            mainGridPane.getChildren().add(towerNameLabel);
            mainGridPane.getChildren().add(reloadSpeedLabel);
            mainGridPane.getChildren().add(reserveButton);
        }
    }
}
