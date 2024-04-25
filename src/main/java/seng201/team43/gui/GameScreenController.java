package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team43.models.GameManager;
import seng201.team43.services.GameService;

/**
 * Controller for the game_screen.fxml window
 *
 * @author Luke Hallett and Riley Jeffcote
 */
public class GameScreenController {
    private final GameManager gameManager;
    private final GameService gameService;

    @FXML
    private Button inventoryButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Label statsLabel;

    @FXML
    private Label currentRoundLabel;

    @FXML
    private Label cartCountLabel;

    public GameScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.gameService = new GameService(this.gameManager);
    }

    public void initialize() {
        inventoryButton.setOnAction(event -> {
            gameManager.openInventoryScreen();
        });

        pauseButton.setOnAction(event -> {
            gameManager.openPauseScreen();
        });

        this.gameService.updateStats(statsLabel, currentRoundLabel, cartCountLabel);
    }
}
