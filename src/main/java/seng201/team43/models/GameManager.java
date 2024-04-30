package seng201.team43.models;

import javafx.application.Platform;
import seng201.team43.exceptions.GameError;

import java.util.function.Consumer;
/**
 * Controller for the game_screen.fxml window
 *
 * @author Luke Hallet and Riley Jeffcote
 */
public class GameManager {
    private String playerName;
    private Integer roundCount;
    private Integer currentRound;
    private Difficulty difficulty;
    private final Inventory inventory;
    private Integer money;

    private final Consumer<GameManager> setupScreenLauncher;
    private final Consumer<GameManager> gameScreenLauncher;
    private final Consumer<GameManager> inventoryScreenLauncher;
    private final Consumer<GameManager> pauseScreenLauncher;
    private final Consumer<GameManager> shopScreenLauncher;
    private final Runnable clearScreen;

    public GameManager(Consumer<GameManager> setupScreenLauncher, Consumer<GameManager> gameScreenLauncher, Consumer<GameManager> inventoryScreenLauncher, Consumer<GameManager> shopScreenLauncher, Consumer<GameManager> pauseScreenLauncher, Runnable clearScreen) {
        this.roundCount = 5;
        this.currentRound = 1;
        this.difficulty = Difficulty.EASY;
        this.inventory = new Inventory();

        this.setupScreenLauncher = setupScreenLauncher;
        this.gameScreenLauncher = gameScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.pauseScreenLauncher = pauseScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.clearScreen = clearScreen;

        launchSetupScreen();
    }

    public void setName(String setName) {
        this.playerName = setName;
    }

    public String getName() {
        return this.playerName;
    }

    public void setRoundCount(Integer setRounds) throws GameError {
        if(setRounds < 5 || setRounds > 15) {
            throw new GameError("Amount of rounds is not between 5 and 15.");
        }

        this.roundCount = setRounds;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {return this.difficulty; }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void addMoney(Integer money) {
        this.money += money;
    }

    public void removeMoney(Integer money) {
        this.money -= money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getMoney() {
        return this.money;
    }

    public Integer getCurrentRound() {
        return this.currentRound;
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    public void launchGameScreen() {
        gameScreenLauncher.accept(this);
    }
    public void launchInventoryScreen() {inventoryScreenLauncher.accept(this); }
    public void launchPauseScreen() {pauseScreenLauncher.accept(this); }
    public void launchShopScreen() {shopScreenLauncher.accept(this); }

    public void closeSetupScreen() {
        clearScreen.run();
        launchGameScreen();
    }
    public void closeInventoryScreen() {
        clearScreen.run();
        launchGameScreen();
    }
    public void openInventoryScreen() {
        clearScreen.run();
        launchInventoryScreen();
    }
    public void openPauseScreen() {
        clearScreen.run();
        launchPauseScreen();
    }
    public void openShopScreen() {
        clearScreen.run();
        launchShopScreen();
    }

    public void closePauseScreen() {
        clearScreen.run();
        launchGameScreen();
    }

    public void quitGame() {
        clearScreen.run();
        Platform.exit();
    }

    public void closeShopScreen() {
        clearScreen.run();
        launchInventoryScreen();
    }

    /**
     * startRound() will start and run the current round
     */
    public void startRound() {

    }
}
