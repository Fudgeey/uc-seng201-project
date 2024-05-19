package seng201.team43.services;

import seng201.team43.exceptions.GameException;
import seng201.team43.models.GameDifficulty;
import seng201.team43.models.GameManager;
import seng201.team43.models.Tower;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for logic behind set up screen.
 * @author Riley Jeffcote, Luke Hallett.
 */
public class SetupService {
    private final GameManager gameManager;
    private final Tower[] startingTowers = new Tower[3];

    /**
     * Initialises Set up service with data from game manager
     * @param gameManager pulls game manager through to link
     */
    public SetupService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setName(String name) {
        this.gameManager.setName(name);
    }

    public void setRoundCount(Integer roundCount) throws GameException {
        this.gameManager.setRoundCount(roundCount);
    }

    public GameDifficulty setGameDifficulty(String difficultyText) throws GameException {
        GameDifficulty gameDifficulty = switch(difficultyText) {
            case "Easy" -> GameDifficulty.EASY;
            case "Medium" -> GameDifficulty.MEDIUM;
            case "Hard" -> GameDifficulty.HARD;
            default -> null;
        };

        if (gameDifficulty == null) {
            throw new GameException("Invalid difficulty selected.");
        }

        this.gameManager.setGameDifficulty(gameDifficulty);
        return gameDifficulty;
    }

    /**
     * Starts the game. Goes to the main game screen from the setup screen.
     * @throws GameException if requirements are not met. e.g. name and towers not filled out.
     */
    public void startGame() throws GameException {
        if(this.gameManager.getName() == null) {
            throw new GameException("A name is required.");
        }

        Pattern specialCharacterPattern = Pattern.compile("[^a-zA-Z ]");
        Matcher specialCharacterMatcher = specialCharacterPattern.matcher(this.gameManager.getName());

        if(specialCharacterMatcher.find()) {
            throw new GameException("Your name must not contains special characters.");
        }

        if (gameManager.getName().length() > 15 || gameManager.getName().length() < 3) {
            throw new GameException("Your name must be between 3-15 characters.");
        }

        this.gameManager.getInventory().setActiveTowers(Arrays.stream(startingTowers).filter((Objects::nonNull)).toList());

        if(this.gameManager.getInventory().getActiveTowers().isEmpty()) {
            throw new GameException("At least one starting tower is required.");
        }

        this.gameManager.prepareRound();
        this.gameManager.openGameScreen();
    }

    /**
     * Helper function to find next empty slot in array.
     *
     * @param array array to find next empty slot
     *
     * @return the index of the next empty slot
     */
    public int findNextSlot(Tower[] array) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == null) {
                return i;
            }
        }

        return -1;
    }

    public Tower[] getStartingTowers() {
        return this.startingTowers;
    }

    public void setStartingTower(int index, Tower tower) {
        this.startingTowers[index] = tower;
    }
}
