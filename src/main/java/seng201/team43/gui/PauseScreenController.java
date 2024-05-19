package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team43.models.GameManager;

/**
 * Controller for the pause_screen.fxml window
 *
 * @author Riley Jeffcote
 */

public class PauseScreenController {
    private final GameManager gameManager;

    @FXML
    private Button resumeButton;

    @FXML
    private Button quitButton;

    /**
     * Initialises the pause screen controller
     * @param gameManager persistent game manager to use
     */
    public PauseScreenController(GameManager gameManager)  {
        this.gameManager = gameManager;
    }

    /**
     * Initialises the JavaFX scene, sets visuals and actions
     */
    public void initialize() {
        resumeButton.setOnAction(event -> gameManager.openGameScreen());
        quitButton.setOnAction(event -> gameManager.quitGame());
    }
}
