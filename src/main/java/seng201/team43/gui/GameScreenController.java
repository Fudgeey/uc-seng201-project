package seng201.team43.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
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
        this.gameService = new GameService(gameManager);
    }

    public void initialize() {
        List<Button> difficultyButtons = List.of(easyDifficultyButton, mediumDifficultyButton, hardDifficultyButton);

        cartsListView.setCellFactory(new CartCellFactory());

        this.displayTowers();
        this.updateStats();

        inventoryButton.setOnAction(event -> this.gameService.openInventoryScreen());
        pauseButton.setOnAction(event -> this.gameService.openPauseScreen());

        startButton.setOnAction(event -> {
            RoundInformation roundInformation = this.gameService.startRound();

            if(roundInformation.getWon()) {
                if(this.gameService.gameEnded()) {
                    this.gameService.setGameWon();
                    this.gameService.launchEndScreen();
                } else {
                    if(!roundInformation.levelledUpTowers.isEmpty()) {
                        for(Purchasable item : roundInformation.levelledUpTowers) {
                            Tower tower = (Tower) item;
                            PopupHelper.display(startButton, String.format("One of your %s towers upgraded and its production increased by 50!", tower.getResourceType().label.toLowerCase()));
                        }
                    }

                    List<String> randomEventsMessage = this.gameService.runRandomEvents();

                    if(!randomEventsMessage.isEmpty()) {
                        for(String eventMessage : randomEventsMessage) {
                            PopupHelper.display(startButton, eventMessage);
                        }
                    }

                    PopupHelper.display(startButton, String.format("You completed the round!\nMoney Earned: $%.2f", roundInformation.moneyEarned));

                    this.gameService.prepareRound();
                    this.updateStats();
                }
            } else {
                this.gameService.launchEndScreen();
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

    /**
     * Update the stats label and update carts list view
     */
    private void updateStats() {
        String statsText = String.format("Current Round: %s\n", this.gameService.getCurrentRound()) +
                String.format("Rounds Won: %s\n", this.gameService.getRoundsWon()) +
                String.format("Rounds Remaining: %s\n", this.gameService.getRemainingRounds()) +
                String.format("Money: $%.2f\n", this.gameService.getMoney()) +
                String.format("Level: %s\n", this.gameService.getLevel()) +
                String.format("Track Distance: %sm", this.gameService.getTrackDistance());

        statsLabel.setText(statsText);
        statsLabel.setStyle("-fx-text-alignment: center; -fx-font-size: 20;");

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
}
