package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import seng201.team43.exceptions.GameError;
import seng201.team43.models.GameDifficulty;
import seng201.team43.models.GameManager;
import seng201.team43.services.GameService;

import java.util.List;
import java.util.Objects;

/**
 * Controller for the game_screen.fxml window
 *
 * @author Luke Hallett and Riley Jeffcote
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
        List<Pane> towerPanes = List.of(towerPaneOne, towerPaneTwo, towerPaneThree, towerPaneFour, towerPaneFive);
        List<Button> difficultyButtons = List.of(easyDifficultyButton, mediumDifficultyButton, hardDifficultyButton);

        this.gameService.displayTowers(towerPanes);
        this.gameService.updateStats(statsLabel, currentRoundLabel, cartCountLabel);

        inventoryButton.setOnAction(event -> gameManager.openInventoryScreen());
        pauseButton.setOnAction(event -> gameManager.openPauseScreen());

        startButton.setOnAction(event -> {
            gameManager.startRound();
            this.gameService.updateStats(statsLabel, currentRoundLabel, cartCountLabel);
        });

        difficultyButtons.forEach(button -> {
            button.setOnAction(event -> {
                try {
                    this.gameService.setDifficulty(button, difficultyButtons);
                } catch (GameError e) {
                    e.displayError();
                }
            });
        });
    }
}
