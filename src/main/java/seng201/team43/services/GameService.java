package seng201.team43.services;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import seng201.team43.components.TowerCard;
import seng201.team43.exceptions.GameError;
import seng201.team43.helpers.ButtonHelper;
import seng201.team43.models.GameDifficulty;
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
        Integer remainingRounds = this.gameManager.getRoundCount() - this.gameManager.getCurrentRound() + 1;

        statsLabel.setTextAlignment(TextAlignment.CENTER);
        statsLabel.setFont(new Font(20));
        statsLabel.setText(String.format("Rounds Won: %s\nRounds Remaining: %s\nMoney: $%s\nTrack Distance: %sm", 0, remainingRounds, this.gameManager.getMoney(), this.gameManager.getTrackDistance()));

        currentRoundLabel.setText(String.format("Round: %s", this.gameManager.getCurrentRound()));
    }

    public void setDifficulty(Button button, List<Button> difficultyButtons) throws GameError {
        RoundDifficulty roundDifficulty = switch(button.getText()) {
            case "Easy" -> RoundDifficulty.EASY;
            case "Medium" -> RoundDifficulty.MEDIUM;
            case "Hard" -> RoundDifficulty.HARD;
            default -> null;
        };

        if(roundDifficulty == null) {
            throw new GameError("Invalid difficulty selected.");
        }

        difficultyButtons.forEach(otherButton -> otherButton.setStyle(""));

        ButtonHelper.setBackground(button, roundDifficulty.colour);
    }

    /**
     * Will start and run the current round.
     * @author Riley Jeffcote, Luke Hallett
     */
    public void startRound() {
        // Add between 1 and 3 carts per round - each of a random type
        //
    }
}
