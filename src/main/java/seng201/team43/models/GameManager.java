package seng201.team43.models;

import java.util.function.Consumer;

public class GameManager {
    private String playerName;
    private Integer roundCount;
    private Integer currentRound;
    private Difficulty difficulty;
    private final Inventory inventory;

    private final Consumer<GameManager> setupScreenLauncher;
    private final Consumer<GameManager> gameScreenLauncher;
    private final Runnable clearScreen;

    public GameManager(Consumer<GameManager> setupScreenLauncher, Consumer<GameManager> gameScreenLauncher, Runnable clearScreen) {
        this.roundCount = 5;
        this.currentRound = 1;
        this.difficulty = Difficulty.EASY;
        this.inventory = new Inventory();

        this.setupScreenLauncher = setupScreenLauncher;
        this.gameScreenLauncher = gameScreenLauncher;
        this.clearScreen = clearScreen;

        launchSetupScreen();
    }

    public void setName(String setName) {
        this.playerName = setName;
    }

    public String getName() {
        return this.playerName;
    }

    public void setRoundCount(Integer setRounds) {
        if(setRounds < 5 || setRounds > 15) {
//            throw new GameError("Amount of rounds is not between 5 and 15.");
            System.out.println("Amount of rounds is not between 5 and 15.");
        }

        this.roundCount = setRounds;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    public void launchGameScreen() {
        gameScreenLauncher.accept(this);
    }

    public void closeSetupScreen() {
        clearScreen.run();
        launchGameScreen();
    }
}
