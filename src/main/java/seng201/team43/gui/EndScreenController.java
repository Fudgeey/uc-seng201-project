package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import seng201.team43.models.GameManager;
import seng201.team43.services.EndService;

public class EndScreenController {
    private final EndService endService;

    @FXML
    private FlowPane innerPane;

    public EndScreenController(GameManager gameManager) {
        this.endService = new EndService(gameManager);
    }

    public void initialize() {
        String titleString;
        int roundCount = this.endService.getRoundCount();

        if(this.endService.isGameWon()) {
            titleString = String.format("Well done %s! You won!", this.endService.getName());
            roundCount += 1;
        } else {
            titleString = String.format("Better luck next time %s! You lost!", this.endService.getName());
        }

        String statsString = String.format("Overall Money Gained: $%.2f\n", this.endService.getMoneyGained()) +
                String.format("Rounds Survived: %s of %s\n", this.endService.getRoundsWon(), roundCount) +
                String.format("Level: %s", this.endService.getLevel());

        Label titleLabel = new Label(titleString);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 40.0));
        titleLabel.setStyle("-fx-text-fill: white;");

        Label statsLabel = new Label(statsString);
        statsLabel.setFont(Font.font(20.0));
        statsLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        innerPane.getChildren().addAll(titleLabel, statsLabel);
    }
}
