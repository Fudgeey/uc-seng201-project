package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import seng201.team43.models.Difficulty;
import seng201.team43.models.GameManager;
import seng201.team43.models.Resource;
import seng201.team43.models.Tower;

import java.util.List;

/**
 * Controller for the setup_screen.fxml window
 * @author seng201 teaching team
 */
public class SetupScreenController {
    private final GameManager gameManager;

    @FXML
    private TextField inputName;

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
    private Label inventoryLabel;

    SetupScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void initialize() {
        List<Button> difficultyButtons = List.of(difficultyEasyButton, difficultyMediumButton, difficultyHardButton);
        List<Button> towerButtons = List.of(waterTowerButton, woodTowerButton, foodTowerButton);

        roundCountSlider.setOnDragDone(event -> {
            this.gameManager.setRounds((int) roundCountSlider.getValue());
        });

        difficultyButtons.forEach(button -> {
            button.setOnAction(event -> {
                difficultyButtons.forEach(otherButton -> {
                    otherButton.setStyle("");
                });

                switch(button.getText()) {
                    case "Easy":
                        this.gameManager.setDifficulty(Difficulty.EASY);
                        button.setStyle("-fx-background-color: #22a359; -fx-background-radius: 5;");
                        break;
                    case "Medium":
                        this.gameManager.setDifficulty(Difficulty.MEDIUM);
                        button.setStyle("-fx-background-color: #ff9c1c; -fx-background-radius: 5;");
                        break;
                    case "Hard":
                        this.gameManager.setDifficulty(Difficulty.HARD);
                        button.setStyle("-fx-background-color: #ff3737; -fx-background-radius: 5;");
                        break;
                }
            });
        });

        towerButtons.forEach(button -> {
            button.setOnAction(event -> {
                switch(button.getText()) {
                    case "Water":
                        this.gameManager.getInventory().addActiveTower(new Tower(Resource.WATER));
                        break;
                    case "Wood":
                        this.gameManager.getInventory().addActiveTower(new Tower(Resource.WOOD));
                        break;
                    case "Food":
                        this.gameManager.getInventory().addActiveTower(new Tower(Resource.FOOD));
                        break;
                }

                StringBuilder activeTowers = new StringBuilder();
                this.gameManager.getInventory().getActiveTowers().forEach(tower -> {
                    activeTowers.append(tower.getResourceType().label);
                });

                inventoryLabel.setText(activeTowers.toString());
            });
        });
    }

    @FXML
    private void onNameInput() {
        this.gameManager.setName(inputName.getText());
    }
}
