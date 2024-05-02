package seng201.team43.gui;

import seng201.team43.models.GameManager;

import java.util.function.Consumer;

public class FXManager {
    private final GameManager gameManager;
    private final Runnable clearScreen;
    private final Consumer<GameManager> setupScreenLauncher;
    private final Consumer<GameManager> gameScreenLauncher;

    public FXManager(GameManager gameManager, Runnable clearScreen, Consumer<GameManager> setupScreenLauncher, Consumer<GameManager> gameScreenLauncher) {
        this.gameManager = gameManager;
        this.clearScreen = clearScreen;
        this.setupScreenLauncher = setupScreenLauncher;
        this.gameScreenLauncher = gameScreenLauncher;
    }

    public void launchSetupScreen() {
        clearScreen.run();
        setupScreenLauncher.accept(this.gameManager);
    }

    public void launchGameScreen() {
        clearScreen.run();
        gameScreenLauncher.accept(this.gameManager);
    }
}
