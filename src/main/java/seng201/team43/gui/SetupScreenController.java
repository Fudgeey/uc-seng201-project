package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import seng201.team43.exceptions.GameError;
import seng201.team43.helpers.ButtonHelper;
import seng201.team43.models.Difficulty;
import seng201.team43.models.GameManager;
import seng201.team43.models.Resource;
import seng201.team43.models.Tower;
import seng201.team43.services.SetupService;

import java.util.List;

/**
 * Controller for the setup_screen.fxml window
 * @author Riley Jeffcote and Luke Hallet
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

        nameField.setOnKeyTyped(event -> {
            this.setupService.setName(nameField.getText());
        });

        roundCountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                this.setupService.setRoundCount(newValue.intValue());
            } catch (GameError e) {
                e.displayError();
            }
        });

        difficultyButtons.forEach(button -> {
            button.setOnAction(event -> {
                try {
                    this.setupService.setDifficulty(button, difficultyButtons);
                } catch (GameError e) {
                    e.displayError();
                }
            });
        });

        towerButtons.forEach(button -> {
            button.setOnAction(event -> {
                try {
                    this.setupService.addStartingTower(button.getText(), startingTowerPanes);
                } catch (GameError e) {
                    e.displayError();
                }
            });
        });

        startButton.setOnAction(event -> {
            try {
                this.setupService.startGame();
            } catch (GameError e) {
                e.displayError();
            }
        });
    }
}
