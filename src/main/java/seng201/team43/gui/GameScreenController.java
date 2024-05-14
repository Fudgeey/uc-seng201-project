package seng201.team43.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import seng201.team43.components.TowerCard;
import seng201.team43.exceptions.GameError;
import seng201.team43.gui.factories.CartCellFactory;
import seng201.team43.helpers.ButtonHelper;
import seng201.team43.helpers.PopupHelper;
import seng201.team43.helpers.RoundInformation;
import seng201.team43.models.*;
import seng201.team43.services.GameService;

import java.util.List;

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

    @FXML
    private ListView<Cart> cartsListView;

    public GameScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.gameService = new GameService(gameManager);
    }

    public void initialize() {
        List<Button> difficultyButtons = List.of(easyDifficultyButton, mediumDifficultyButton, hardDifficultyButton);

        cartsListView.setCellFactory(new CartCellFactory());

        this.displayTowers();
        this.updateStats();

        inventoryButton.setOnAction(event -> gameManager.openInventoryScreen());
        pauseButton.setOnAction(event -> gameManager.openPauseScreen());

        startButton.setOnAction(event -> {
            RoundInformation roundInformation = this.gameService.startRound();

            if(roundInformation.getWon()) {
                if(this.gameService.gameEnded()) {
                    this.gameService.setGameWon();
                    this.gameManager.launchEndScreen();
                } else {
                    if(!roundInformation.levelledUpTowers.isEmpty()) {
                        for(Purchasable item : roundInformation.levelledUpTowers) {
                            Tower tower = (Tower) item;
                            PopupHelper.display(startButton, String.format("One of your %s towers upgraded and production increased by 50!!", tower.getResourceType().label));
                        }
                    }

                    try {
                        List<String> randomEventsMessage = this.gameService.runRandomEvents();

                        if(!randomEventsMessage.isEmpty()) {
                            for(String eventMessage : randomEventsMessage) {
                                PopupHelper.display(startButton, eventMessage);
                            }
                        }
                    } catch(GameError e) {
                        e.displayError(startButton);
                    }

                    PopupHelper.display(startButton, String.format("You Won!\nMoney Earned: $%.2f", roundInformation.moneyEarned));

                    this.gameService.prepareRound();
                    this.updateStats();
                }
            } else {
                this.gameManager.launchEndScreen();
            }

            this.gameService.setPreviousRoundInformation(roundInformation);
            this.displayTowers();
        });

        try {
            this.updateRoundDifficultyButtons();
        } catch (GameError e) {
            e.displayError(easyDifficultyButton);
        }

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
        statsLabel.setText(String.format("Current Round: %s\nRounds Won: %s\nRounds Remaining: %s\nMoney: $%.2f\nTrack Distance: %sm", this.gameManager.getCurrentRound(), this.gameManager.getCurrentRound() - 1, remainingRounds, this.gameManager.getMoney(), this.gameManager.getTrackDistance()));

        cartsListView.setItems(FXCollections.observableArrayList(this.gameService.getCarts()));
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

    private void updateRoundDifficultyButtons() throws GameError {
        RoundDifficulty roundDifficulty = this.gameService.getRoundDifficulty();

        Button difficultyButton = switch(roundDifficulty) {
            case EASY -> easyDifficultyButton;
            case MEDIUM -> mediumDifficultyButton;
            case HARD -> hardDifficultyButton;
        };

        if(difficultyButton == null) {
            throw new GameError("Invalid round difficulty selected.");
        }

        ButtonHelper.setBackground(difficultyButton, roundDifficulty.colour);
    }

    private void displayTowers() {
        List<Pane> towerPanes = List.of(towerPaneOne, towerPaneTwo, towerPaneThree, towerPaneFour, towerPaneFive);

        for(Pane pane : towerPanes) {
            pane.getChildren().clear();
        }

        for(int i = 0; i < this.gameService.getActiveTowers().size(); i++) {
            Tower tower = this.gameService.getActiveTowers().get(i);
            Pane towerPane = towerPanes.get(i);

            TowerCard towerCard = new TowerCard(tower);
            FlowPane towerFlowPane = towerCard.buildGame();

            towerPane.getChildren().add(towerFlowPane);
        }
    }

    public void leveUpPopupScreen(Tower tower) {
        PopupHelper.display(startButton, String.format("A %s tower levelled up and production is increased by +50!!", tower.getResourceType()));
    }
}
