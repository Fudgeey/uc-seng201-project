package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    @FXML
    private Button closeButton;

    public EndScreenController(GameManager gameManager) {
        this.endService = new EndService(gameManager);
    }

    public void initialize() {
        String titleString;
        int roundsWon = this.endService.getRoundsWon();

        if(this.endService.isGameWon()) {
            titleString = String.format("Well done %s! You won!", this.endService.getName());
            roundsWon += 1;
        } else {
            titleString = String.format("Better luck next time %s! You lost!", this.endService.getName());
        }

        String statsString = String.format("Overall Money Gained: $%.2f\n", this.endService.getMoneyGained()) +
                String.format("Rounds Survived: %s of %s\n", roundsWon, this.endService.getRoundCount()) +
                String.format("Level: %s", this.endService.getLevel());

        Label titleLabel = new Label(titleString);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 40.0));
        titleLabel.setStyle("-fx-text-fill: white;");

        Label statsLabel = new Label(statsString);
        statsLabel.setFont(Font.font(20.0));
        statsLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-text-alignment: center;");

        innerPane.getChildren().addAll(titleLabel, statsLabel);

        closeButton.setOnAction(event -> {
            this.endService.closeGame();
        });
    }
}
