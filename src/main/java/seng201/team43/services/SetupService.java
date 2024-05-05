package seng201.team43.services;

import javafx.scene.layout.GridPane;
import seng201.team43.components.TowerCard;
import seng201.team43.exceptions.GameError;
import seng201.team43.models.GameDifficulty;
import seng201.team43.models.GameManager;
import seng201.team43.models.Resource;
import seng201.team43.models.Tower;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetupService {
    private final GameManager gameManager;
    private final Tower[] startingTowers = new Tower[3];

    public SetupService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setName(String name) {
        this.gameManager.setName(name);
    }

    public void setRoundCount(Integer roundCount) throws GameError {
        this.gameManager.setRoundCount(roundCount);
    }

    public GameDifficulty setGameDifficulty(String difficultyText) throws GameError {
        GameDifficulty gameDifficulty = switch(difficultyText) {
            case "Easy" -> GameDifficulty.EASY;
            case "Medium" -> GameDifficulty.MEDIUM;
            case "Hard" -> GameDifficulty.HARD;
            default -> null;
        };

        if (gameDifficulty == null) {
            throw new GameError("Invalid difficulty selected.");
        }

        this.gameManager.setGameDifficulty(gameDifficulty);
        return gameDifficulty;
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

        TowerCard towerCard = new TowerCard(newTower);
        GridPane towerCardPane = towerCard.buildSetup(this, slot);

        currentPane.getChildren().add(towerCardPane);

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
        }

        Pattern specialCharacterPattern = Pattern.compile("[^a-zA-Z ]");
        Matcher specialCharacterMatcher = specialCharacterPattern.matcher(this.gameManager.getName());

        if(specialCharacterMatcher.find()) {
            throw new GameError("Your name must not contains special characters.");
        }

        if (gameManager.getName().length() > 15 || gameManager.getName().length() < 3) {
            throw new GameError("Your name must be between 3-15 characters.");
        }

        for(Tower tower : this.startingTowers) {
            if(tower != null) {
                this.gameManager.getInventory().addActiveTower(tower);
            }
        }

        this.gameManager.getInventory().setActiveTowers(Arrays.stream(startingTowers).filter((Objects::nonNull)).toList());

        if(this.gameManager.getInventory().getActiveTowers().isEmpty()) {
            throw new GameError("At least one starting tower is required.");
        }

        this.gameManager.prepareRound();
        this.gameManager.closeSetupScreen();
    }

    /**
     * Helper function to find next empty slot in array.
     *
     * @param array array to find next empty slot
     *
     * @return the index of the next empty slot
     */
    private int findNextSlot(Tower[] array) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == null) {
                return i;
            }
        }

        return -1;
    }
}
