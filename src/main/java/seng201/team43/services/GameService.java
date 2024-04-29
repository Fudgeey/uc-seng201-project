package seng201.team43.services;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import seng201.team43.components.TowerCard;
import seng201.team43.models.GameManager;
import seng201.team43.models.Inventory;
import seng201.team43.models.Tower;

import java.util.List;

public class GameService {
    private final GameManager gameManager;

    public GameService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void displayTowers(List<Pane> towerPanes) {
        Inventory inventory = gameManager.getInventory();

        for(int i = 0; i < inventory.getActiveTowers().size(); i++) {
            Tower tower = inventory.getActiveTowers().get(i);
            Pane towerPane = towerPanes.get(i);

            TowerCard towerCard = new TowerCard(tower);
            FlowPane towerFlowPane = towerCard.buildGame();

            towerPane.getChildren().add(towerFlowPane);
        }
    }

    public void updateStats(Label statsLabel, Label currentRoundLabel, Label cartCountLabel) {
        statsLabel.setTextAlignment(TextAlignment.CENTER);
        statsLabel.setText(String.format("Rounds Won: %s\nMoney: $%s", 0, this.gameManager.getMoney()));

        currentRoundLabel.setText(String.format("Round: %s", this.gameManager.getCurrentRound()));
    }
}
