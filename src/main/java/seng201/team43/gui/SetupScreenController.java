package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import seng201.team43.models.Difficulty;
import seng201.team43.models.GameManager;

import java.util.List;

/**
 * Controller for the setup_screen.fxml window
 * @author seng201 teaching team
 */
public class SetupScreenController {
    private GameManager gameManager;

    @FXML
    private TextField inputName;

    @FXML
    private Button difficultyEasyButton;

    @FXML
    private Button difficultyMediumButton;

    @FXML
    private Button difficultyHardButton;

    SetupScreenController(GameManager initialGameManager) {
        this.gameManager = initialGameManager;
    }

    public void initialize() {
        List<Button> difficultyButtons = List.of(difficultyEasyButton, difficultyMediumButton, difficultyHardButton);

        difficultyEasyButton.setOnAction(event -> {
            this.gameManager.setDifficulty(Difficulty.EASY);
        });

        difficultyMediumButton.setOnAction(event -> {
            this.gameManager.setDifficulty(Difficulty.MEDIUM);
        });

        difficultyHardButton.setOnAction(event -> {
            this.gameManager.setDifficulty(Difficulty.HARD);
        });
    }

    @FXML
    private void onNameInput() {
        this.gameManager.setName(inputName.getText());
    }
}
