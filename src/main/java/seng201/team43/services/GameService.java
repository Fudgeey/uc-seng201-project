package seng201.team43.services;

import seng201.team43.helpers.RoundInformation;
import seng201.team43.models.*;
import seng201.team43.models.enums.RoundDifficulty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

/**
 * Class for logic behind Game screen.
 * @author Riley Jeffcote, Luke Hallett.
 */
public class GameService {
    private final GameManager gameManager;
    private final Random random;

    /**
     * Initialises game service with game manager
     * @param gameManager to initialise game service.
     */
    public GameService(GameManager gameManager) {
        this.gameManager = gameManager;
        this.random = new Random();
    }

    /**
     * Initialises game service with game manager and a predefined random.
     * @param gameManager to initialise game service.
     * @param random the random class
     */
    public GameService(GameManager gameManager, Random random) {
        this.gameManager = gameManager;
        this.random = random;
    }

    public void setPreviousRoundInformation(RoundInformation roundInformation) {
        this.gameManager.setPreviousRoundInformation(roundInformation);
    }

    /**
     * Starts the round.
     * @return Round information on the round that has been played.
     */
    public RoundInformation startRound() {
        return this.gameManager.startRound();
    }

    /**
     * Prepares the round.
     */
    public void prepareRound() {
        List<Cart> carts = this.gameManager.getCarts();

        for(Cart cart : carts) {
            cart.setCurrentFilled(0);
        }

        this.gameManager.prepareRound();
    }

    /**
     * Runs random events. Called at the end of a round to run the random events.
     * @return the events that happened.
     */
    public List<String> runRandomEvents() {
        List<Tower> activeTowers = this.gameManager.getInventory().getActiveTowers().stream().filter(not(Tower::isBroken)).toList();
        List<Tower> reserveTowers = this.gameManager.getInventory().getReserveTowers().stream().filter(not(Tower::isBroken)).toList();

        ArrayList<String> activeTowersMessages = this.runTowersRandomEvent(activeTowers, this.gameManager.getInventory().getActiveTowers(), 5);
        ArrayList<String> reserveTowersMessages = this.runTowersRandomEvent(reserveTowers, this.gameManager.getInventory().getReserveTowers(), 6);

        return Stream.concat(activeTowersMessages.stream(), reserveTowersMessages.stream()).toList();
    }

    /**
     * Runs the random tower events.
     * @param towersToCheck the towers to check and run events on.
     * @param towers the towers.
     * @param probability the chance of random event happening.
     * @return the message containing what random events happened.
     */
    private ArrayList<String> runTowersRandomEvent(List<Tower> towersToCheck, ArrayList<Tower> towers, int probability) {
        ArrayList<String> messages = new ArrayList<>();

        for(Tower tower : towersToCheck) {
            int chance = this.random.nextInt(0, probability);

            if(chance == 0) {
                int choice = this.random.nextInt(0, 4);
                String resourceLabel = tower.getResourceType().label.toLowerCase();

                switch(choice) {
                    case 0:
                        messages.add(String.format("A %s tower was broken.", resourceLabel));
                        tower.setBroken(true);
                        break;
                    case 1:
                        messages.add(String.format("A %s tower was destroyed and removed from your inventory.", resourceLabel));
                        towers.remove(tower);
                        break;
                    case 2:
                        if(tower.getLevel() > 1) {
                            tower.levelDown();
                            messages.add(String.format("A %s tower has had its level decreased.", resourceLabel));
                        }
                        break;
                    case 3:
                        messages.add(String.format("A %s tower has had its level increased.", resourceLabel));
                        tower.addExperience(10);
                        break;
                }
            }
        }

        return messages;
    }

    public void setRoundDifficulty(RoundDifficulty roundDifficulty) {
        this.gameManager.setRoundDifficulty(roundDifficulty);
    }

    public RoundDifficulty getRoundDifficulty() {
        return this.gameManager.getRoundDifficulty();
    }

    /**
     * See if the game is ended.
     * @return boolean of whether the game has ended.
     */
    public boolean gameEnded() {
        return this.gameManager.getCurrentRound() + 1 > this.gameManager.getRoundCount();
    }

    public List<Cart> getCarts() {
        return this.gameManager.getCarts();
    }

    public void setGameWon() {
        this.gameManager.setGameWon();
    }

    public List<Tower> getActiveTowers() {
        return this.gameManager.getInventory().getActiveTowers().stream().filter(not(Tower::isBroken)).toList();
    }

    /**
     * Calculates remaining rounds based on current round and total rounds
     * @return remaining round count
     */
    public int getRemainingRounds() {
        return this.gameManager.getRoundCount() - this.gameManager.getCurrentRound() + 1;
    }

    public int getCurrentRound() {
        return this.gameManager.getCurrentRound();
    }

    public int getRoundsWon() {
        return this.gameManager.getCurrentRound() - 1;
    }

    public double getMoney() {
        return this.gameManager.getMoney();
    }

    public double getTrackDistance() {
        return this.gameManager.getTrackDistance();
    }

    public int getLevel() {
        return this.gameManager.getLevel();
    }
}
