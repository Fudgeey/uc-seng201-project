package seng201.team43.gui;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import seng201.team43.exceptions.GameException;
import seng201.team43.helpers.ArrayHelper;
import seng201.team43.helpers.ButtonHelper;
import seng201.team43.models.GameDifficulty;
import seng201.team43.models.GameManager;
import seng201.team43.models.Resource;
import seng201.team43.models.Tower;
import seng201.team43.services.SetupService;

import java.util.List;
import java.util.Objects;

/**
 * Controller for the setup_screen.fxml window
 * @author Riley Jeffcote, Luke Hallett
 */
public class SetupScreenController {
    private final SetupService setupService;
    private final GUIManager guiManager;

    @FXML
    private TextField nameField;

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
    private GridPane startingTowerOnePane;

    @FXML
    private GridPane startingTowerTwoPane;

    @FXML
    private GridPane startingTowerThreePane;

    @FXML
    private Button startButton;

    /**
     * Initialises the setup screen controller
     * @param gameManager persistent game manager to use
     */
    public SetupScreenController(GameManager gameManager, GUIManager guiManager) {
        this.setupService = new SetupService(gameManager);
        this.guiManager = guiManager;
    }

    /**
     * Initialises the JavaFX scene, sets visuals and actions and calls logic functions
     */
    public void initialize() {
        List<Button> difficultyButtons = List.of(difficultyEasyButton, difficultyMediumButton, difficultyHardButton);
        List<Button> towerButtons = List.of(waterTowerButton, woodTowerButton, foodTowerButton);

        nameField.setOnKeyReleased(event -> this.setupService.setName(nameField.getText()));

        roundCountSlider.setStyle("-fx-tick-label-fill: white; -fx-tick-label-font: 20px 'System';");
        roundCountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                this.setupService.setRoundCount(newValue.intValue());
            } catch (GameException e) {
                e.displayError(roundCountSlider);
            }
        });

        difficultyButtons.forEach(button -> {
            button.setOnAction(event -> {
                GameDifficulty gameDifficulty = this.setupService.setGameDifficulty(button.getText());

                difficultyButtons.forEach(otherButton -> otherButton.setStyle(""));
                ButtonHelper.setBackground(button, gameDifficulty.colour);
            });
        });

        towerButtons.forEach(button -> {
            button.setOnAction(event -> {
                try {
                    this.addStartingTower(button.getText());
                } catch (GameException e) {
                    e.displayError(button);
                }
            });
        });

        startButton.setOnAction(event -> {
            try {
                this.setupService.startGame();
                this.guiManager.openGameScreen();
            } catch (GameException e) {
                e.displayError(startButton);
            }
        });
    }

    /**
     * Displays the starting tower selected, then it finds the next available
     * slot and places the tower into it.
     * @param resourceText text from button of tower clicked
     * @throws GameException error if there are more than three starting towers
     */
    public void addStartingTower(String resourceText) throws GameException {
        List<GridPane> startingTowerPanes = List.of(startingTowerOnePane, startingTowerTwoPane, startingTowerThreePane);
        Resource resource = switch (resourceText) {
            case "Water" -> Resource.WATER;
            case "Wood" -> Resource.WOOD;
            default -> Resource.FOOD;
        };

        int slot = ArrayHelper.findNextSlot(this.setupService.getStartingTowers());

        if(slot == -1) {
            throw new GameException("You can only have three starting towers.");
        }

        Tower newTower = new Tower(resource);
        this.setupService.setStartingTower(slot, newTower);

        GridPane currentPane = startingTowerPanes.get(slot);

        GridPane towerGridPane = new GridPane();
        FlowPane towerFlowPane = new FlowPane();

        GridPane.setValignment(towerFlowPane, VPos.CENTER);
        GridPane.setHalignment(towerFlowPane, HPos.CENTER);
        GridPane.setVgrow(towerFlowPane, Priority.ALWAYS);
        GridPane.setHgrow(towerFlowPane, Priority.ALWAYS);
        towerFlowPane.setAlignment(Pos.CENTER);
        towerFlowPane.setOrientation(Orientation.VERTICAL);
        towerFlowPane.setColumnHalignment(HPos.CENTER);

        Label nameLabel = new Label(newTower.getName());
        nameLabel.setStyle("-fx-font-size: 30;");

        Label resourceTypeLabel = new Label(newTower.getResourceType().label);
        resourceTypeLabel.setStyle("-fx-font-size: 25;");

        ImageView resourceImage = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(String.format("/images/towers/%s.png", newTower.getResourceType().label.toLowerCase())))));
        resourceImage.setFitWidth(180);
        resourceImage.setPreserveRatio(true);

        Button removeButton = new Button("X");
        GridPane.setValignment(removeButton, VPos.TOP);
        GridPane.setHalignment(removeButton, HPos.RIGHT);
        removeButton.setStyle("-fx-background-color: red; -fx-background-radius: 100%; -fx-text-fill: white;");
        removeButton.setOnAction(event -> this.removeStartingTower((GridPane) towerGridPane.getParent(), slot));

        towerFlowPane.getChildren().addAll(nameLabel, resourceTypeLabel, resourceImage);
        towerGridPane.getChildren().addAll(towerFlowPane, removeButton);
        currentPane.getChildren().add(towerGridPane);

        currentPane.setVisible(true);
    }

    /**
     * Removes a starting tower. Sets the pane visibility to false and clears the pane.
     * @param currentPane tower pane to remove
     * @param slot slot in starting tower array
     */
    public void removeStartingTower(GridPane currentPane, Integer slot) {
        currentPane.setVisible(false);
        currentPane.getChildren().clear();

        this.setupService.setStartingTower(slot, null);
    }
}
