package seng201.team43.models;

import javafx.application.Platform;
import seng201.team43.exceptions.GameError;

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
    private Integer roundCount;
    private Integer currentRound;
    private GameDifficulty gameDifficulty;
    private final Inventory inventory;
    private Double money;
    private Double trackDistance;
    private Double moneyGained;
    private Double expGained;
    private final ArrayList<Cart> carts;
    private RoundDifficulty roundDifficulty;
    private List<Purchasable> shopItems;

    private final Consumer<GameManager> setupScreenLauncher;
    private final Consumer<GameManager> gameScreenLauncher;
    private final Consumer<GameManager> inventoryScreenLauncher;
    private final Consumer<GameManager> pauseScreenLauncher;
    private final Consumer<GameManager> shopScreenLauncher;
    private final Runnable clearScreen;

    public GameManager(Consumer<GameManager> setupScreenLauncher, Consumer<GameManager> gameScreenLauncher, Consumer<GameManager> inventoryScreenLauncher, Consumer<GameManager> shopScreenLauncher, Consumer<GameManager> pauseScreenLauncher, Runnable clearScreen) {
        this.roundCount = 5;
        this.currentRound = 0;
        this.inventory = new Inventory();
        this.money = 0.0;
        this.moneyGained = 0.0;
        this.carts = new ArrayList<>();

        this.setGameDifficulty(GameDifficulty.EASY);
        this.setRoundDifficulty(RoundDifficulty.EASY);

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

    /**
     * Gets the total amount of rounds.
     * @return total amount of rounds
     */
    public Integer getRoundCount() {
        return this.roundCount;
    }

    /**
     * Sets the game difficulty.
     * @param gameDifficulty game difficulty
     */
    public void setGameDifficulty(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        this.money = gameDifficulty.startingMoney;
        this.moneyGained = gameDifficulty.startingMoney;
    }

    /**
     * Gets the game difficulty
     * @return game difficulty
     */
    public GameDifficulty getGameDifficulty() {
        return this.gameDifficulty;
    }

    public void setRoundDifficulty(RoundDifficulty roundDifficulty) {
        this.roundDifficulty = roundDifficulty;
        this.trackDistance = roundDifficulty.trackDistance;
    }

    /**
     * Gets the current round's difficulty
     * @return the round difficulty
     */
    public RoundDifficulty getRoundDifficulty() {
        return this.roundDifficulty;
    }

    /**
     * Gets the inventory
     * @return inventory
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    public void addMoney(Double money) {
        this.money += money;
        this.moneyGained += money;
    }

    public void removeMoney(Integer money) throws GameError {
        if(this.getMoney() - money < 0) {
            throw new GameError("You do not have enough money to buy this.");
        }

        this.money -= money;
    }

    public Double getMoney() {
        return this.money;
    }

    public Integer getCurrentRound() {
        return this.currentRound;
    }

    /**
     * Returns the current track distance.
     * @return track distance
     */
    public Double getTrackDistance() {
        return this.trackDistance;
    }

    /**
     * Adds a cart
     * @param cart cart object to add
     */
    public void addCart(Cart cart) {
        this.carts.add(cart);
    }

    /**
     * Gets current carts
     * @return list of carts
     */
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

            double sizeMultiplier = ((double) this.getRoundCount() / 10) + 1.0;
            Integer size = (int) (100 * sizeMultiplier);

            Integer speed = 5 * this.getCurrentRound();

            Cart cart = new Cart(size, speed, resources[resourceIndex]);

            this.addCart(cart);
        }

        List<Purchasable> shopItems = new ArrayList<>(List.of(new ProductionUpgrade(10), new ReloadUpgrade(), new ResourceTypeUpgrade()));

        for(Resource resource : Resource.values()) {
            shopItems.add(new Tower(resource));
        }

        Collections.shuffle(shopItems);
        this.shopItems = shopItems.stream().limit(5).toList();
    }

    /**
     * Starts the current round.
     */
    public boolean startRound() {
        for(Tower tower : this.getInventory().getActiveTowers()) {
            for(Cart cart : this.getCarts()) {
                if(tower.getResourceType() == cart.getType()) {
                    double cartTimeOnTrack = this.getTrackDistance() / cart.getSpeed();
                    int towerAction = Math.floorDiv((int) cartTimeOnTrack, tower.getReloadSpeed()) + 1;
                    int unitsToAdd = towerAction * tower.getProductionUnits();

                    cart.addCurrentFilled(unitsToAdd);
                }
            }
        }

        int cartsNotFilled = 0;
        boolean roundWon = true;

        for(Cart cart : this.getCarts()) {
            if(cart.getCurrentFilled() < cart.getSize()) {
                cartsNotFilled += 1;
                roundWon = false;
            }
        }

        return roundWon;
    }

    public List<Purchasable> getShopItems() {
        return this.shopItems;
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

    public void closeShopScreen() {
        clearScreen.run();
        launchInventoryScreen();
    }

    public void quitGame() {
        clearScreen.run();
        Platform.exit();
    }
}
