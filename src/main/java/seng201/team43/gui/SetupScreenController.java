package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import seng201.team43.exceptions.GameError;
import seng201.team43.helpers.ButtonHelper;
import seng201.team43.models.GameDifficulty;
import seng201.team43.models.GameManager;
import seng201.team43.services.SetupService;

import java.util.List;

/**
 * Controller for the setup_screen.fxml window
 * @author Riley Jeffcote, Luke Hallett
 */
public class SetupScreenController {
    private final SetupService setupService;

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
                    this.setupService.addStartingTower(button.getText(), startingTowerPanes);
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
}
