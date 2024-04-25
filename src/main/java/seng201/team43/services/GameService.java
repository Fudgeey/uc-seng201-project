package seng201.team43.services;

import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import seng201.team43.models.GameManager;

public class GameService {
    private final GameManager gameManager;

    public GameService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void updateStats(Label statsLabel, Label currentRoundLabel) {
        statsLabel.setTextAlignment(TextAlignment.CENTER);
        statsLabel.setText(String.format("Rounds Won: %s\nMoney: $%s", 0, this.gameManager.getMoney()));

        currentRoundLabel.setText(String.format("Round: %s", this.gameManager.getCurrentRound()));
    }
}
