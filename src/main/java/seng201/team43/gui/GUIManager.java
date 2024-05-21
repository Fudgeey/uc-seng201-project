package seng201.team43.gui;

import javafx.application.Platform;

import java.util.function.Consumer;

/**
 * Class for GUI Manager. Manages changing between javaFX scenes.
 * @author Luke Hallett, Riley Jeffcote
 */
public class GUIManager {
    private final Consumer<GUIManager> setupScreenLauncher;
    private final Consumer<GUIManager> gameScreenLauncher;
    private final Consumer<GUIManager> inventoryScreenLauncher;
    private final Consumer<GUIManager> pauseScreenLauncher;
    private final Consumer<GUIManager> shopScreenLauncher;
    private final Consumer<GUIManager> endScreenLauncher;
    private final Runnable clearScreen;

    /**
     * Initialises GUI manager
     * @param setupScreenLauncher set up screen initialise
     * @param gameScreenLauncher game screen initialise
     * @param inventoryScreenLauncher inventory screen initialise
     * @param shopScreenLauncher shop screen initialise
     * @param pauseScreenLauncher pause screen initialise
     * @param endScreenLauncher end screen initialise
     * @param clearScreen clear screen initialise
     */
    public GUIManager(Consumer<GUIManager> setupScreenLauncher, Consumer<GUIManager> gameScreenLauncher, Consumer<GUIManager> inventoryScreenLauncher, Consumer<GUIManager> shopScreenLauncher, Consumer<GUIManager> pauseScreenLauncher, Consumer<GUIManager> endScreenLauncher, Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.gameScreenLauncher = gameScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.pauseScreenLauncher = pauseScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.endScreenLauncher = endScreenLauncher;
        this.clearScreen = clearScreen;

        openSetupScreen();
    }

    /**
     * Launches set up screen.
     */
    public void openSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    /**
     * Launches game screen.
     */
    public void openGameScreen() {
        clearScreen.run();
        gameScreenLauncher.accept(this);
    }

    /**
     * Launches inventory screen.
     */
    public void openInventoryScreen() {
        clearScreen.run();
        inventoryScreenLauncher.accept(this);
    }

    /**
     * Launches pause screen.
     */
    public void openPauseScreen() {
        clearScreen.run();
        pauseScreenLauncher.accept(this);
    }

    /**
     * Launches shop screen.
     */
    public void openShopScreen() {
        clearScreen.run();
        shopScreenLauncher.accept(this);
    }

    /**
     * Launches end screen.
     */
    public void openEndScreen() {
        clearScreen.run();
        endScreenLauncher.accept(this);
    }

    /**
     * Quits the game.
     */
    public void quitGame() {
        clearScreen.run();
        Platform.exit();
    }
}
