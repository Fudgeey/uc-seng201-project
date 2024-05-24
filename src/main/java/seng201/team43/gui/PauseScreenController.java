package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team43.models.GameManager;

/**
 * Controller for the pause_screen.fxml window
 *
 * @author Riley Jeffcote, Luke Hallett
 */

public class PauseScreenController {
    private final GUIManager guiManager;

    @FXML
    private Button resumeButton;

    @FXML
    private Button quitButton;

    /**
     * Initialises the pause screen controller class
     * @param guiManager class for JavaFX scene management
     */
    public PauseScreenController(GUIManager guiManager)  {
        this.guiManager = guiManager;
    }

    /**
     * Initialises the JavaFX scene, sets visuals and actions
     */
    public void initialize() {
        resumeButton.setOnAction(event -> this.guiManager.openGameScreen());
        quitButton.setOnAction(event -> this.guiManager.quitGame());
    }
}
