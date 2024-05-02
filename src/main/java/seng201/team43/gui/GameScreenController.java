package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import seng201.team43.components.TowerCard;
import seng201.team43.exceptions.GameError;
import seng201.team43.models.GameDifficulty;
import seng201.team43.models.GameManager;
import seng201.team43.models.Inventory;
import seng201.team43.models.Tower;
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
                    this.gameService.setDifficulty(button, difficultyButtons);
                } catch (GameError e) {
                    e.displayError();
                }

                this.updateStats();
            });
        });
    }

    private void updateStats() {
        this.gameService.updateStats(statsLabel, currentRoundLabel, cartCountLabel);
    }

    private void displayTowers() {
        List<Pane> towerPanes = List.of(towerPaneOne, towerPaneTwo, towerPaneThree, towerPaneFour, towerPaneFive);
        Inventory inventory = gameManager.getInventory();

        for(int i = 0; i < inventory.getActiveTowers().size(); i++) {
            Tower tower = inventory.getActiveTowers().get(i);
            Pane towerPane = towerPanes.get(i);

            TowerCard towerCard = new TowerCard(tower);
            FlowPane towerFlowPane = towerCard.buildGame();

            towerPane.getChildren().add(towerFlowPane);
        }
    }
}
