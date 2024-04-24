package seng201.team43.models;

import java.util.function.Consumer;

public class GameManager {
    private String playerName;
    private Integer rounds;
    private Difficulty difficulty;

    private final Consumer<GameManager> setupScreenLauncher;
//    private final Consumer<GameManager> mainScreenLauncher;
    private final Runnable clearScreen;

    public GameManager(Consumer<GameManager> setupScreenLauncher, Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.clearScreen = clearScreen;

        launchSetupScreen();
    }

    public void setName(String setName) {
        this.playerName = setName;
    }

    public String getName() {
        return this.playerName;
    }

    public void setRounds(Integer setRounds) {
        if(setRounds > 15 || setRounds < 5) {
//            throw error
        }

        this.rounds = setRounds;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    public void closeSetupScreen() {
        clearScreen.run();
//        launchMainScreen();
    }
}
