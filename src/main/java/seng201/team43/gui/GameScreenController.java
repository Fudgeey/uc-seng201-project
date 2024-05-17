package seng201.team43.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import seng201.team43.exceptions.GameException;
import seng201.team43.gui.factories.CartCellFactory;
import seng201.team43.helpers.ButtonHelper;
import seng201.team43.helpers.PopupHelper;
import seng201.team43.helpers.RoundInformation;
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

    /**
     * Creates the game screen controller with the game manager
     * @param gameManager persistent game manager instance
     */
    public GameScreenController(GameManager gameManager) {
        this.gameService = new GameService(gameManager);
    }

    /**
     * Initialises the JavaFX scene, sets visuals and actions
     */
    public void initialize() {
        this.updateVisuals();

        cartsListView.setCellFactory(new CartCellFactory());

        inventoryButton.setOnAction(event -> this.gameService.openInventoryScreen());
        pauseButton.setOnAction(event -> this.gameService.openPauseScreen());
        startButton.setOnAction(event -> this.startGame());

        List<Button> difficultyButtons = List.of(easyDifficultyButton, mediumDifficultyButton, hardDifficultyButton);
        difficultyButtons.forEach(button -> button.setOnAction(event -> {
            this.setRoundDifficulty(button, difficultyButtons);
            this.updateVisuals();
        }));
    }

    /**
     * Updates all the game's visuals: towers, carts, stats, difficulty buttons
     */
    private void updateVisuals() {
        this.displayTowers();
        this.updateStats();
        this.updateRoundDifficultyButtons();
        cartsListView.setItems(FXCollections.observableArrayList(this.gameService.getCarts()));
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
    }

    /**
     * Sets the round difficulty depending on the button selected
     * @param button button that was pressed
     * @param difficultyButtons all three difficulty buttons
     */
    private void setRoundDifficulty(Button button, List<Button> difficultyButtons) {
        RoundDifficulty roundDifficulty = switch(button.getText()) {
            case "Easy" -> RoundDifficulty.EASY;
            case "Medium" -> RoundDifficulty.MEDIUM;
            default -> RoundDifficulty.HARD;
        };

        this.gameService.setRoundDifficulty(roundDifficulty);

        difficultyButtons.forEach(otherButton -> otherButton.setStyle(""));
        ButtonHelper.setBackground(button, roundDifficulty.colour);
    }

    /**
     * Updates the round difficulty buttons.
     */
    private void updateRoundDifficultyButtons() {
        RoundDifficulty roundDifficulty = this.gameService.getRoundDifficulty();

        Button difficultyButton = switch(roundDifficulty) {
            case EASY -> easyDifficultyButton;
            case MEDIUM -> mediumDifficultyButton;
            default -> hardDifficultyButton;
        };

        ButtonHelper.setBackground(difficultyButton, roundDifficulty.colour);
    }

    /**
     * Displays the towers on the main track on game screen. Has 5 set positions for the active tower inventory.
     */
    private void displayTowers() {
        List<Pane> towerPanes = List.of(towerPaneOne, towerPaneTwo, towerPaneThree, towerPaneFour, towerPaneFive);

        for(Pane pane : towerPanes) {
            pane.getChildren().clear();
        }

        for(int i = 0; i < this.gameService.getActiveTowers().size(); i++) {
            Tower tower = this.gameService.getActiveTowers().get(i);
            Pane towerPane = towerPanes.get(i);

            FlowPane towerFlowPane = new FlowPane();
            towerFlowPane.setStyle("-fx-background-color: white; -fx-pref-height: 150; -fx-pref-width: 150; -fx-column-halignment: center; -fx-alignment: center; -fx-orientation: vertical;");

            Label nameLabel = new Label(tower.getName());
            nameLabel.setStyle("-fx-font-size: 15");

            ImageView resourceImage = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(String.format("/images/towers/%s.png", tower.getResourceType().label.toLowerCase())))));
            resourceImage.setFitWidth(100);
            resourceImage.setPreserveRatio(true);

            towerFlowPane.getChildren().addAll(nameLabel, resourceImage);
            towerPane.getChildren().add(towerFlowPane);
        }
    }

    private void startGame() {
        RoundInformation roundInformation = this.gameService.startRound();

        if(roundInformation.getWon()) {
            if(this.gameService.gameEnded()) {
                this.gameService.setGameWon();
                this.gameService.openEndScreen();
            } else {
                if(!roundInformation.levelledUpTowers.isEmpty()) {
                    for(Purchasable item : roundInformation.levelledUpTowers) {
                        Tower tower = (Tower) item;
                        PopupHelper.display(startButton, String.format("One of your %s towers upgraded and its production increased by 25!", tower.getResourceType().label.toLowerCase()));
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
                this.updateVisuals();
            }
        } else {
            this.gameService.openEndScreen();
        }

        this.gameService.setPreviousRoundInformation(roundInformation);
        this.displayTowers();
    }
}
