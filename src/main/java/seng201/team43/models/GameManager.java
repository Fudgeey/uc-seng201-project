package seng201.team43.models;

import seng201.team43.exceptions.GameException;
import seng201.team43.helpers.RoundInformation;
import seng201.team43.models.enums.GameDifficulty;
import seng201.team43.models.enums.Resource;
import seng201.team43.models.enums.RoundDifficulty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Controller for the game_screen.fxml window
 *
 * @author Luke Hallett, Riley Jeffcote
 */
public class GameManager {
    private String name;
    private int money;
    private int experience;
    private int moneyGained;
    private int experienceGained;
    private final Inventory inventory;

    private Integer roundCount;
    private Integer currentRound;
    private RoundDifficulty roundDifficulty;
    private final List<Cart> carts;

    private GameDifficulty gameDifficulty;
    private int trackDistance;
    private List<Purchasable> shopItems;
    private boolean gameWon;
    private RoundInformation previousRoundInformation;

    /**
     * Initalises the game manager which JavaFX screens
     */
    public GameManager() {
        this.roundCount = 5;
        this.currentRound = 0;
        this.inventory = new Inventory();
        this.money = 0;
        this.moneyGained = 0;
        this.carts = new ArrayList<>();
        this.gameWon = false;

        this.setGameDifficulty(GameDifficulty.EASY);
        this.setRoundDifficulty(RoundDifficulty.MEDIUM);
    }

    public void setPreviousRoundInformation(RoundInformation roundInformation) {
        this.previousRoundInformation = roundInformation;
    }

    public void setName(String setName) {
        this.name = setName;
    }

    public String getName() {
        return this.name;
    }

    public void setRoundCount(Integer setRounds) throws GameException {
        if(setRounds < 5 || setRounds > 15) {
            throw new GameException("Amount of rounds is not between 5 and 15.");
        }

        this.roundCount = setRounds;
    }

    public Integer getRoundCount() {
        return this.roundCount;
    }

    public GameDifficulty getGameDifficulty() {
        return this.gameDifficulty;
    }

    /**
     * Sets the game difficulty and sets starting balance
     * @param gameDifficulty game difficulty to set
     */
    public void setGameDifficulty(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        this.money = gameDifficulty.startingMoney;
        this.moneyGained = gameDifficulty.startingMoney;
    }

    /**
     * Sets the round difficulty by changing track distance and cart speed
     * @param roundDifficulty round difficulty to set
     */
    public void setRoundDifficulty(RoundDifficulty roundDifficulty) {
        this.roundDifficulty = roundDifficulty;
        this.trackDistance = roundDifficulty.trackDistance;

        for(Cart cart : this.getCarts()) {
            cart.setSpeed(roundDifficulty);
        }
    }

    public RoundDifficulty getRoundDifficulty() {
        return this.roundDifficulty;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Takes money as a parameter and adds this to the money.
     * @param money money to add
     */
    public void addMoney(int money) {
        this.money += money;
        this.moneyGained += money;
    }

    /**
     * Takes money as a parameter and takes it away from money after checking that money cant go below 0.
     * @param money money to remove
     * @throws GameException error if balance will go below 0
     */
    public void removeMoney(int money) throws GameException {
        if(this.getMoney() - money < 0) {
            throw new GameException("You do not have enough money to buy this.");
        }

        this.money -= money;
    }

    public int getMoney() {
        return this.money;
    }

    /**
     * Adds experience.
     * @param experience amount of experience to add
     */
    public void addExperience(int experience) {
        int oldLevel = this.getLevel();
        this.experience += experience;
        this.experienceGained += experience;
        int newLevel = this.getLevel();

        if(newLevel > oldLevel) {
            int levelDifference = newLevel - oldLevel;
            this.addMoney(100 * levelDifference);
        }
    }

    public int getExperienceGained() {
        return this.experienceGained;
    }

    /**
     * Gets current level based on experience
     * @return current player level
     */
    public int getLevel() {
        return (Math.floorDiv(this.experience, 10) + 1);
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

    public List<Cart> getCarts() {
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
            cart.setSpeed(this.getRoundDifficulty());

            this.addCart(cart);
        }

        double costMultiplier = this.getGameDifficulty().multiplier;

        List<Purchasable> items = new ArrayList<>(List.of(new ProductionUpgrade((int) (100 * costMultiplier), 25), new ReloadUpgrade((int) (100 * costMultiplier)), new ResourceTypeUpgrade((int) (75 * costMultiplier)), new RepairTowerUpgrade((int) (50 * costMultiplier))));

        for(Resource resource : Resource.values()) {
            items.add(new Tower((int) (100 * costMultiplier), resource));
        }

        Collections.shuffle(items);
        this.shopItems = items.stream().limit(5).toList();
    }

    /**
     * Starts the current round.
     * @return information about the just played round
     */
    public RoundInformation startRound() {
        RoundInformation roundInformation = new RoundInformation();

        for(Tower tower : this.getInventory().getActiveTowers()) {
            for(Cart cart : this.getCarts()) {
                int previousLevel = tower.getLevel();
                int moneyEarned = cart.fill(tower, this.getTrackDistance());

                this.addMoney(moneyEarned);
                roundInformation.addMoneyEarned(moneyEarned);

                if (previousLevel < tower.getLevel()) {
                    roundInformation.addLevelledUpTower(tower);
                }
            }
        }

        for(Cart cart : this.getCarts()) {
            if(cart.getCurrentFilled() < cart.getSize()) {
                roundInformation.setWon(false);
                break;
            }

            this.addExperience(2);
            this.addMoney(25);
            roundInformation.addMoneyEarned(25);
        }

        if(roundInformation.getWon()) {
            this.addMoney(50);
            roundInformation.addMoneyEarned(50);
        }

        return roundInformation;
    }

    public List<Purchasable> getShopItems() {
        return this.shopItems;
    }

    public int getMoneyGained() {
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
}
