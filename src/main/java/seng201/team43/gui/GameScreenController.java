package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import seng201.team43.components.TowerCard;
import seng201.team43.exceptions.GameError;
import seng201.team43.helpers.ButtonHelper;
import seng201.team43.models.*;
import seng201.team43.services.GameService;

import java.util.List;
import java.util.Objects;

/**
 * Controller for the game_screen.fxml window
 *
 * @author Luke Hallett, Riley Jeffcote
 */
public class GameScreenController {
    private final GameManager gameManager;
    private final GameService gameService;

    @FXML
    private Button inventoryButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button startButton;

    @FXML
    private Label statsLabel;

    @FXML
    private Label currentRoundLabel;

    @FXML
    private Label cartCountLabel;

    @FXML
    private Pane towerPaneOne;

    @FXML
    private Pane towerPaneTwo;

    @FXML
    private Pane towerPaneThree;

    @FXML
    private Pane towerPaneFour;

    @FXML
    private Pane towerPaneFive;

    @FXML
    private Button easyDifficultyButton;

    @FXML
    private Button mediumDifficultyButton;

    @FXML
    private Button hardDifficultyButton;

    public GameScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.gameService = new GameService(this.gameManager);
    }

    public void initialize() {
        List<Button> difficultyButtons = List.of(easyDifficultyButton, mediumDifficultyButton, hardDifficultyButton);

        this.displayTowers();
        this.updateStats();

        inventoryButton.setOnAction(event -> gameManager.openInventoryScreen());
        pauseButton.setOnAction(event -> gameManager.openPauseScreen());

//        startButton.setOnAction(event -> {
//            gameManager.startRound();
//            this.updateStats();
//        });

        difficultyButtons.forEach(button -> {
            button.setOnAction(event -> {
                try {
                    this.setRoundDifficulty(button, difficultyButtons);
                } catch (GameError e) {
                    e.displayError(button);
                }

                this.updateStats();
            });
        });
    }

    private void updateStats() {
        Integer remainingRounds = this.gameManager.getRoundCount() - this.gameManager.getCurrentRound() + 1;

        statsLabel.setTextAlignment(TextAlignment.CENTER);
        statsLabel.setFont(new Font(20));
        statsLabel.setText(String.format("Rounds Won: %s\nRounds Remaining: %s\nMoney: $%s\nTrack Distance: %sm", 0, remainingRounds, this.gameManager.getMoney(), this.gameManager.getTrackDistance()));

        currentRoundLabel.setText(String.format("Round: %s", this.gameManager.getCurrentRound()));

        int woodCartCount = 0;
        int foodCartCount = 0;
        int waterCartCount = 0;

        for(Cart cart : this.gameManager.getCats()) {
            switch (cart.getType().label) {
                case "Water" -> waterCartCount += 1;
                case "Wood" -> woodCartCount += 1;
                case "Food" -> foodCartCount += 1;
                default -> {}
            };
        }

        cartCountLabel.setText(String.format("Wood: %s\nFood: %s\nWater: %s", woodCartCount, foodCartCount, waterCartCount));
    }

    private void setRoundDifficulty(Button button, List<Button> difficultyButtons) throws GameError {
        RoundDifficulty roundDifficulty = switch(button.getText()) {
            case "Easy" -> RoundDifficulty.EASY;
            case "Medium" -> RoundDifficulty.MEDIUM;
            case "Hard" -> RoundDifficulty.HARD;
            default -> null;
        };

        if(roundDifficulty == null) {
            throw new GameError("Invalid difficulty selected.");
        }

        this.gameService.setRoundDifficulty(roundDifficulty);

        difficultyButtons.forEach(otherButton -> otherButton.setStyle(""));
        ButtonHelper.setBackground(button, roundDifficulty.colour);
    }

    private void displayTowers() {
        List<Pane> towerPanes = List.of(towerPaneOne, towerPaneTwo, towerPaneThree, towerPaneFour, towerPaneFive);

        for(int i = 0; i < this.gameService.getActiveTowers().size(); i++) {
            Tower tower = this.gameService.getActiveTowers().get(i);
            Pane towerPane = towerPanes.get(i);

            TowerCard towerCard = new TowerCard(tower);
            FlowPane towerFlowPane = towerCard.buildGame();

            towerPane.getChildren().add(towerFlowPane);
        }
    }
}
