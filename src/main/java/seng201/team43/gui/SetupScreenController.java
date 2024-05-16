package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import seng201.team43.components.TowerCard;
import seng201.team43.exceptions.GameError;
import seng201.team43.helpers.ButtonHelper;
import seng201.team43.models.GameDifficulty;
import seng201.team43.models.GameManager;
import seng201.team43.models.Resource;
import seng201.team43.models.Tower;
import seng201.team43.services.SetupService;

import java.util.List;

/**
 * Controller for the setup_screen.fxml window
 * @author Riley Jeffcote, Luke Hallett
 */
public class SetupScreenController {
    private final SetupService setupService;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Slider roundCountSlider;

    @FXML
    private Button difficultyEasyButton;

    @FXML
    private Button difficultyMediumButton;

    @FXML
    private Button difficultyHardButton;

    @FXML
    private Button waterTowerButton;

    @FXML
    private Button woodTowerButton;

    @FXML
    private Button foodTowerButton;

    @FXML
    private GridPane startingTowerOnePane;

    @FXML
    private GridPane startingTowerTwoPane;

    @FXML
    private GridPane startingTowerThreePane;

    @FXML
    private Button startButton;

    public SetupScreenController(GameManager gameManager) {
        this.setupService = new SetupService(gameManager);
    }

    public void initialize() {
        List<Button> difficultyButtons = List.of(difficultyEasyButton, difficultyMediumButton, difficultyHardButton);
        List<Button> towerButtons = List.of(waterTowerButton, woodTowerButton, foodTowerButton);
        List<GridPane> startingTowerPanes = List.of(startingTowerOnePane, startingTowerTwoPane, startingTowerThreePane);

        nameField.setOnKeyReleased(event -> {
            this.setupService.setName(nameField.getText());
        });

        roundCountSlider.setStyle("-fx-tick-label-fill: white; -fx-tick-label-font: 20px 'System';");
        roundCountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                this.setupService.setRoundCount(newValue.intValue());
            } catch (GameError e) {
                e.displayError(roundCountSlider);
            }
        });

        difficultyButtons.forEach(button -> {
            button.setOnAction(event -> {
                try {
                    GameDifficulty gameDifficulty = this.setupService.setGameDifficulty(button.getText());

                    difficultyButtons.forEach(otherButton -> otherButton.setStyle(""));
                    ButtonHelper.setBackground(button, gameDifficulty.colour);
                } catch (GameError e) {
                    e.displayError(button);
                }
            });
        });

        towerButtons.forEach(button -> {
            button.setOnAction(event -> {
                try {
                    this.addStartingTower(button.getText(), startingTowerPanes);
                } catch (GameError e) {
                    e.displayError(button);
                }
            });
        });

        startButton.setOnAction(event -> {
            try {
                this.setupService.startGame();
            } catch (GameError e) {
                e.displayError(startButton);
            }
        });
    }

    /**
     * Takes resourseText as a parameter to determine the resource type of the tower. Then it finds the next available
     * slot and places the tower into it.
     * @param resourceText
     * @param startingTowerPanes
     * @throws GameError
     */
    /*
    TODO: write more on this if needed.
     */
    public void addStartingTower(String resourceText, List<GridPane> startingTowerPanes) throws GameError {
        Resource resource = switch (resourceText) {
            case "Water" -> Resource.WATER;
            case "Wood" -> Resource.WOOD;
            case "Food" -> Resource.FOOD;
            default -> null;
        };

        int slot = this.setupService.findNextSlot(this.setupService.getStartingTowers());

        if(slot == -1) {
            throw new GameError("You can only have three starting towers.");
        }

        Tower newTower = new Tower(resource);
        this.setupService.setStartingTower(slot, newTower);

        GridPane currentPane = startingTowerPanes.get(slot);

        TowerCard towerCard = new TowerCard(newTower);
        GridPane towerCardPane = towerCard.buildSetup(this, slot);

        currentPane.getChildren().add(towerCardPane);

        currentPane.setVisible(true);
    }

    /**
     * Removes a starting tower. Sets the pane visibility to false and clears the pane.
     * @param currentPane
     * @param slot
     */
    public void removeStartingTower(GridPane currentPane, Integer slot) {
        currentPane.setVisible(false);
        currentPane.getChildren().clear();

        this.setupService.setStartingTower(slot, null);
    }
}
