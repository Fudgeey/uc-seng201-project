package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team43.models.GameManager;
import seng201.team43.services.SetupService;

public class PauseScreenController {
    public PauseScreenController(GameManager gameManager)  {
        this.gameManager = gameManager;
    }
    private final GameManager gameManager;
    @FXML
    private Button resumeButton;
    @FXML
    private Button quitButton;
    public void initialize() {
        resumeButton.setOnAction(event -> {
            gameManager.closePauseScreen();
        });
        quitButton.setOnAction(event -> {
            gameManager.quitGame();
        });
    }

}
