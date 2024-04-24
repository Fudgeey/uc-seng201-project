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
 * @author seng201 teaching team, Luke Hallett
 */
public class FXWrapper {
    @FXML
    private Pane pane;

    private Stage stage;

    /**
     * Initialise the window with the given stage,
     * and create the GameManger instance.
     *
     * @param stage
     *
     * @author seng201 teaching team, Luke Hallett
     */
    public void init(Stage stage) {
        this.stage = stage;

        new GameManager(this::launchSetupScreen, this::clearPane);
    }

    /**
     * Launches the setup screen and defines basic properties,
     * e.g. title, fullscreen
     *
     * @param gameManager
     *
     * @author seng201 teaching team, Luke Hallett
     */
    public void launchSetupScreen(GameManager gameManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            setupLoader.setControllerFactory(param -> new SetupScreenController(gameManager));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Dynasty");

            /* Sets game to be fullscreen and disables the hint */
            stage.setFullScreenExitHint("");
            stage.setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the current screen.
     *
     * @author Luke Hallett
     */
    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }
}
