package seng201.team43.services;

import seng201.team43.helpers.RoundInformation;
import seng201.team43.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class GameService {
    private final GameManager gameManager;

    public GameService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public RoundInformation startRound() {
        return this.gameManager.startRound();
    }

    public void prepareRound() {
        this.gameManager.prepareRound();
    }

    public List<String> runRandomEvents() {
        List<Tower> activeTowers = this.gameManager.getInventory().getActiveTowers().stream().filter(not(Tower::isBroken)).toList();
        List<Tower> reserveTowers = this.gameManager.getInventory().getReserveTowers().stream().filter(not(Tower::isBroken)).toList();

        ArrayList<String> activeTowersMessages = this.runTowersRandomEvent(activeTowers);
        ArrayList<String> reserveTowersMessages = this.runTowersRandomEvent(reserveTowers);

        return Stream.concat(activeTowersMessages.stream(), reserveTowersMessages.stream()).toList();
    }

    private ArrayList<String> runTowersRandomEvent(List<Tower> towers) {
        Random random = new Random();
        ArrayList<String> messages = new ArrayList<>();

        for(Tower tower : towers) {
            int chance = random.nextInt(0, 8);

            if(chance == 0) {
                int choice = random.nextInt(0, 4);

                if(choice == 0) {
                    messages.add(String.format("A %s tower was broken.", tower.getResourceType().label));
                    tower.setBroken(true);
                } else if(choice == 1) {
                    messages.add(String.format("A %s tower was destroyed and removed from your inventory.", tower.getResourceType().label));
                    towers.remove(tower);
                } else if(choice == 2) {
                    messages.add(String.format("A %s tower has had its level decreased.", tower.getResourceType().label));
                    tower.levelDown();
                } else {
                    messages.add(String.format("A %s tower has had its level increased.", tower.getResourceType().label));
                    tower.levelUp();
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

    public boolean gameEnded() {
        return this.gameManager.getCurrentRound() + 1 > this.gameManager.getRoundCount();
    }

    public List<Cart> getCarts() {
        return this.gameManager.getCarts();
    }

    public void setPreviousRoundInformation(RoundInformation roundInformation) {
        this.gameManager.setPreviousRoundInformation(roundInformation);
    }

    public void setGameWon() {
        this.gameManager.setGameWon();
    }

    public List<Tower> getActiveTowers() {
        return this.gameManager.getInventory().getActiveTowers().stream().filter(not(Tower::isBroken)).toList();
    }
}
