package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team43.models.GameManager;

import java.io.IOException;

/**
 * Class that manages the different JavaFX scenes
 *
 * @author seng201 teaching team, Luke Hallett, Riley Jeffcote
 */
public class FXWrapper {
    @FXML
    private Pane pane;

    private Stage stage;

    /**
     * Initialise the window with the given stage,
     * and create the GameManger instance.
     *
     * @param stage stage
     */
    public void init(Stage stage) {
        this.stage = stage;

        new GameManager(this::launchSetupScreen, this::launchGameScreen, this::launchInventoryScreen, this::launchShopScreen, this::launchPauseScreen, this::launchEndScreen, this::clearPane);
    }

    /**
     * Launches the setup screen and defines basic properties,
     * e.g. title, fullscreen
     *
     * @param gameManager current game manager instance
     */
    public void launchSetupScreen(GameManager gameManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            setupLoader.setControllerFactory(param -> new SetupScreenController(gameManager));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Dynasty");
            stage.setFullScreenExitHint("");
            stage.setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the game screen and defines basic properties.
     * @param gameManager game manager to parse into screen
     */
    public void launchGameScreen(GameManager gameManager) {
        try {
            FXMLLoader gameScreenLoader = new FXMLLoader(getClass().getResource("/fxml/game_screen.fxml"));
            gameScreenLoader.setControllerFactory(param -> new GameScreenController(gameManager));
            Parent setupParent  = gameScreenLoader.load();
            pane.getChildren().add(setupParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches inventory screen and defines basic properties.
     * @param gameManager game manager to parse into screen
     */
    public void launchInventoryScreen(GameManager gameManager) {
        try {
            FXMLLoader inventoryScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inventory_screen.fxml"));
            inventoryScreenLoader.setControllerFactory(param -> new InventoryScreenController(gameManager));
            Parent setupParent  = inventoryScreenLoader.load();
            pane.getChildren().add(setupParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches shop screen and defines basic properties.
     * @param gameManager game manager to parse into screen
     */
    public void launchShopScreen(GameManager gameManager) {
        try {
            FXMLLoader shopScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shop_screen.fxml"));
            shopScreenLoader.setControllerFactory(param -> new ShopScreenController(gameManager));
            Parent setupParent  = shopScreenLoader.load();
            pane.getChildren().add(setupParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches pause screen and defines basic properties.
     * @param gameManager game manager to parse into screen
     */
    public void launchPauseScreen(GameManager gameManager) {
        try {
            FXMLLoader pauseScreenLoader = new FXMLLoader(getClass().getResource("/fxml/pause_screen.fxml"));
            pauseScreenLoader.setControllerFactory(param -> new PauseScreenController(gameManager));
            Parent setupParent  = pauseScreenLoader.load();
            pane.getChildren().add(setupParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches end screen and defines basic properties.
     * @param gameManager game manager to parse into screen
     */
    public void launchEndScreen(GameManager gameManager) {
        try {
            FXMLLoader endScreenLoader = new FXMLLoader(getClass().getResource("/fxml/end_screen.fxml"));
            endScreenLoader.setControllerFactory(param -> new EndScreenController(gameManager));
            Parent setupParent  = endScreenLoader.load();
            pane.getChildren().add(setupParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the current screen.
     */
    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }
}
