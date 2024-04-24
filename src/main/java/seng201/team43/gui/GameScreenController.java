package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team43.models.GameManager;

/**
 * Controller for the game_screen.fxml window
 *
 * @author Luke Hallett and Riley Jeffcote
 */
public class GameScreenController {
    private final GameManager gameManager;
    @FXML
    private Button inventoryButton;
    @FXML
    private Button pauseButton;

    public GameScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
    }


    public void initialize() {
        inventoryButton.setOnAction(event -> {
            gameManager.openInventoryScreen();
        });
        pauseButton.setOnAction(event -> {
            gameManager.openPauseScreen();
        });
    }
}
