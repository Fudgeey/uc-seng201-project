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
 * @author seng201 teaching team, Luke Hallett
 */
public class FXWrapper {
    @FXML
    private Pane pane;

    private Stage stage;


    public void init(Stage stage) {
        this.stage = stage;

        new GameManager(this::launchSetupScreen, this::clearPane);
    }

    public void launchSetupScreen(GameManager gameManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            setupLoader.setControllerFactory(param -> new SetupScreenController(gameManager));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Dynasty");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }
}
