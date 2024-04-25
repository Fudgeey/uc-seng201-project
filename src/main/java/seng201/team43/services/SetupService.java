package seng201.team43.services;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import seng201.team43.exceptions.GameError;
import seng201.team43.models.GameManager;
import seng201.team43.models.Resource;
import seng201.team43.models.Tower;

import java.util.List;

public class SetupService {
    private final GameManager gameManager;
    private final Tower[] startingTowers = new Tower[3];

    public SetupService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setName(String name) {
        this.gameManager.setName(name);
    }

    public void addStartingTower(String resourceText, List<GridPane> startingTowerPanes) throws GameError {
        Resource resource = switch (resourceText) {
            case "Water" -> Resource.WATER;
            case "Wood" -> Resource.WOOD;
            case "Food" -> Resource.FOOD;
            default -> null;
        };

        int slot = this.findNextSlot(this.startingTowers);

        if(slot == -1) {
            throw new GameError("You can only have three starting towers.");
        }

        Tower newTower = new Tower(resource);
        startingTowers[slot] = newTower;

        GridPane currentPane = startingTowerPanes.get(slot);

        Label towerNameLabel = new Label(newTower.getName());
        GridPane.setHalignment(towerNameLabel, HPos.CENTER);

        Button removeButton = new Button("X");
        GridPane.setValignment(removeButton, VPos.TOP);
        GridPane.setHalignment(removeButton, HPos.RIGHT);
        removeButton.setStyle("-fx-background-color: red; -fx-background-radius: 100%");
        removeButton.setTextFill(Paint.valueOf("white"));

        currentPane.getChildren().add(towerNameLabel);
        currentPane.getChildren().add(removeButton);

        removeButton.setOnAction(event -> {
            this.removeStartingTower(currentPane, slot);
        });

        currentPane.setVisible(true);
    }

    public void removeStartingTower(GridPane currentPane, Integer slot) {
        currentPane.setVisible(false);
        currentPane.getChildren().clear();

        this.startingTowers[slot] = null;
    }

    public void startGame() throws GameError {
        if(this.gameManager.getName() == null) {
            throw new GameError("A name is required.");
        } else {
            for(Tower tower : this.startingTowers) {
                if(tower != null) {
                    this.gameManager.getInventory().addActiveTower(tower);
                }
            }

            this.gameManager.setMoney((int) (100 * this.gameManager.getDifficulty().multiplier));
            this.gameManager.closeSetupScreen();
        }
    }

    private int findNextSlot(Tower[] array) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == null) {
                return i;
            }
        }

        return -1;
    }
}
