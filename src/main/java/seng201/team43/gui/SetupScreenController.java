package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    private final GameManager gameManager;
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
        this.gameManager = gameManager;
        this.setupService = new SetupService(this.gameManager);
    }

    public void initialize() {
        List<Button> difficultyButtons = List.of(difficultyEasyButton, difficultyMediumButton, difficultyHardButton);
        List<Button> towerButtons = List.of(waterTowerButton, woodTowerButton, foodTowerButton);
        List<GridPane> startingTowerPanes = List.of(startingTowerOnePane, startingTowerTwoPane, startingTowerThreePane);

        nameField.setOnAction(event -> {
            this.setupService.setName(nameField.getText());
        });

        roundCountSlider.setOnDragDone(event -> {
            this.gameManager.setRoundCount((int) roundCountSlider.getValue());
        });

        difficultyButtons.forEach(button -> {
            button.setOnAction(event -> {
                difficultyButtons.forEach(otherButton -> {
                    otherButton.setStyle("");
                });

                switch(button.getText()) {
                    case "Easy":
                        this.gameManager.setDifficulty(Difficulty.EASY);
                        ButtonHelper.setBackground(button, "#22a359");

                        break;
                    case "Medium":
                        this.gameManager.setDifficulty(Difficulty.MEDIUM);
                        ButtonHelper.setBackground(button, "#ff9c1c");

                        break;
                    case "Hard":
                        this.gameManager.setDifficulty(Difficulty.HARD);
                        ButtonHelper.setBackground(button, "#ff3737");

                        break;
                }
            });
        });

        towerButtons.forEach(button -> {
            button.setOnAction(event -> {
                this.setupService.addStartingTower(button.getText(), startingTowerPanes);
            });
        });

        startButton.setOnAction(event -> {
            this.setupService.startGame();
        });
    }
}
