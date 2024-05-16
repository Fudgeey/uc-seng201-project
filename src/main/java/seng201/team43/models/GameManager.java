package seng201.team43.models;

import javafx.application.Platform;
import seng201.team43.exceptions.GameError;
import seng201.team43.helpers.RoundInformation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Controller for the game_screen.fxml window
 *
 * @author Luke Hallett, Riley Jeffcote
 */
public class GameManager {
    private String playerName;
    private Double money;
    private int experience;
    private final Inventory inventory;

    private Integer roundCount;
    private Integer currentRound;
    private GameDifficulty gameDifficulty;
    private int trackDistance;

    private Double moneyGained;
    private int experienceGained;
    private final ArrayList<Cart> carts;
    private RoundDifficulty roundDifficulty;
    private List<Purchasable> shopItems;
    private boolean gameWon;

    private final Consumer<GameManager> setupScreenLauncher;
    private final Consumer<GameManager> gameScreenLauncher;
    private final Consumer<GameManager> inventoryScreenLauncher;
    private final Consumer<GameManager> pauseScreenLauncher;
    private final Consumer<GameManager> shopScreenLauncher;
    private final Consumer<GameManager> endScreenLauncher;
    private final Runnable clearScreen;

    public GameManager(Consumer<GameManager> setupScreenLauncher, Consumer<GameManager> gameScreenLauncher, Consumer<GameManager> inventoryScreenLauncher, Consumer<GameManager> shopScreenLauncher, Consumer<GameManager> pauseScreenLauncher, Consumer<GameManager> endScreenLauncher, Runnable clearScreen) {
        this.roundCount = 5;
        this.currentRound = 0;
        this.inventory = new Inventory();
        this.money = 0.0;
        this.moneyGained = 0.0;
        this.carts = new ArrayList<>();
        this.gameWon = false;

        this.setGameDifficulty(GameDifficulty.EASY);
        this.setRoundDifficulty(RoundDifficulty.EASY);

        this.setupScreenLauncher = setupScreenLauncher;
        this.gameScreenLauncher = gameScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.pauseScreenLauncher = pauseScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.endScreenLauncher = endScreenLauncher;
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

    public Integer getRoundCount() {
        return this.roundCount;
    }

    public void setGameDifficulty(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        this.money = gameDifficulty.startingMoney;
        this.moneyGained = gameDifficulty.startingMoney;
    }

    public GameDifficulty getGameDifficulty() {
        return this.gameDifficulty;
    }

    public void setRoundDifficulty(RoundDifficulty roundDifficulty) {
        this.roundDifficulty = roundDifficulty;
        this.trackDistance = roundDifficulty.trackDistance;
    }

    public RoundDifficulty getRoundDifficulty() {
        return this.roundDifficulty;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Takes money as a parameter and adds this to the money.
     * @param money
     */
    public void addMoney(Double money) {
        this.money += money;
        this.moneyGained += money;
    }

    /**
     * Takes money as a parameter and takes it away from money after checking that money cant go below 0.
     * @param money
     * @throws GameError
     */
    public void removeMoney(Integer money) throws GameError {
        if(this.getMoney() - money < 0) {
            throw new GameError("You do not have enough money to buy this.");
        }

        this.money -= money;
    }

    public Double getMoney() {
        return this.money;
    }

    /**
     * Adds experience.
     * @param experience
     */
    public void addExperience(int experience) {
        this.experience += experience;
        this.experienceGained += experience;
    }

    public int getExperienceGained() {
        return this.experienceGained;
    }

    public int getLevel() {
        return ((this.experience - this.experience % 10) / 10);
    }

    public Integer getCurrentRound() {
        return this.currentRound;
    }

    public int getTrackDistance() {
        return this.trackDistance;
    }

    /**
     * Adds a cart
     * @param cart cart object to add
     */
    public void addCart(Cart cart) {
        this.carts.add(cart);
    }

    public ArrayList<Cart> getCarts() {
        return this.carts;
    }

    /**
     * Prepares the current round.
     */
    public void prepareRound() {
        this.currentRound += 1;

        if(this.getCurrentRound() % 2 != 0) {
            Random random = new Random();
            Resource[] resources = Resource.values();
            int resourceIndex = random.nextInt(resources.length);

            double sizeMultiplier = ((double) this.getCurrentRound() / 10) + 1.0;
            int size = (int) (100 * sizeMultiplier);

            int speed = 5 + this.getCurrentRound();

            Cart cart = new Cart(size, speed, resources[resourceIndex]);

            this.addCart(cart);
        }

        List<Purchasable> shopItems = new ArrayList<>(List.of(new ProductionUpgrade(25), new ReloadUpgrade(), new ResourceTypeUpgrade(), new RepairTowerUpgrade()));

        for(Resource resource : Resource.values()) {
            shopItems.add(new Tower(resource));
        }

        Collections.shuffle(shopItems);
        this.shopItems = shopItems.stream().limit(5).toList();
    }

    /**
     * Starts the current round.
     */
    public RoundInformation startRound() {
        RoundInformation roundInformation = new RoundInformation();

        for(Tower tower : this.getInventory().getActiveTowers()) {
            for(Cart cart : this.getCarts()) {
                int previousLevel = tower.getLevel();
                double moneyEarned = cart.fill(tower, this.getTrackDistance());

                this.addMoney(moneyEarned);
                roundInformation.moneyEarned += moneyEarned;

                if (previousLevel < tower.getLevel()) {
                    roundInformation.levelledUpTowers.add(tower);
                }
            }
        }

        int cartsNotFilled = 0;

        for(Cart cart : this.getCarts()) {
            if(cart.getCurrentFilled() < cart.getSize()) {
                cartsNotFilled += 1;
                roundInformation.setWon(false);
                roundInformation.setMessage(String.format("Carts Not Filled: %s", cartsNotFilled));
            } else {
                this.addExperience(2);
                this.addMoney(25.0);
                roundInformation.moneyEarned += 25;
            }
        }

        if(roundInformation.getWon()) {
            this.addMoney(50.0);
            roundInformation.moneyEarned += 50;
        }

        return roundInformation;
    }

    public List<Purchasable> getShopItems() {
        return this.shopItems;
    }

    public Double getMoneyGained() {
        return this.moneyGained;
    }


    public void setGameWon() {
        this.gameWon = true;
    }

    /**
     * Returns whether the game is won or not.
     * @return gameWon
     */
    public boolean isGameWon() {
        return this.gameWon;
    }

    /**
     * Launches set up screen.
     */
    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    /**
     * Launches game screen.
     */
    public void launchGameScreen() {
        clearScreen.run();
        gameScreenLauncher.accept(this);
    }

    /**
     * Launches inventory screen.
     */
    public void launchInventoryScreen() {
        clearScreen.run();
        inventoryScreenLauncher.accept(this);
    }

    /**
     * Launches pause screen.
     */
    public void launchPauseScreen() {
        clearScreen.run();
        pauseScreenLauncher.accept(this);
    }

    /**
     * Launches shop screen.
     */
    public void launchShopScreen() {
        clearScreen.run();
        shopScreenLauncher.accept(this);
    }

    /**
     * Launches end screen.
     */
    public void launchEndScreen() {
        clearScreen.run();
        endScreenLauncher.accept(this);
    }

    /**
     * Closes setup screen.
     */
    public void closeSetupScreen() {
        clearScreen.run();
        launchGameScreen();
    }

    /**
     * Closes inventory screen.
     */
    public void closeInventoryScreen() {
        clearScreen.run();
        launchGameScreen();
    }

    /**
     * Closes pause screen.
     */
    public void closePauseScreen() {
        clearScreen.run();
        launchGameScreen();
    }

    /**
     * Closes shop screen.
     */
    public void closeShopScreen() {
        clearScreen.run();
        launchInventoryScreen();
    }

    /**
     * Quits the game.
     */
    public void quitGame() {
        clearScreen.run();
        Platform.exit();
    }
}
