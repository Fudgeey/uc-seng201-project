package seng201.team43.services;

import seng201.team43.helpers.PopupHelper;
import seng201.team43.helpers.RoundInformation;
import seng201.team43.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public ArrayList<String> runRandomEvents() {
        ArrayList<String> messages = new ArrayList<>();
        Random random = new Random();
        List<Tower> activeTowers = this.gameManager.getInventory().getActiveTowers().stream().filter(not(Tower::isBroken)).toList();
        List<Tower> reserveTowers = this.gameManager.getInventory().getReserveTowers().stream().filter(not(Tower::isBroken)).toList();

        for(Tower tower : activeTowers) {
            int chance = random.nextInt(0, 6);

            if(chance == 0) {
                int choice = random.nextInt(0, 2);

                if(choice == 0) {
                    messages.add(String.format("A %s tower was broken.", tower.getResourceType().label));
                    tower.setBroken(true);
                } else {
                    messages.add(String.format("A %s tower was destroyed and removed from your inventory.", tower.getResourceType().label));
                    this.gameManager.getInventory().getActiveTowers().remove(tower);
                }
            }
        }

        for(Tower tower : reserveTowers) {
            int chance = random.nextInt(0, 8);

            if(chance == 0) {
                int choice = random.nextInt(0, 2);

                if(choice == 0) {
                    messages.add(String.format("A %s tower was broken.", tower.getResourceType().label));
                    tower.setBroken(true);
                } else {
                    messages.add(String.format("A %s tower was destroyed and removed from your inventory.", tower.getResourceType().label));
                    this.gameManager.getInventory().getReserveTowers().remove(tower);
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

    public boolean isGameWon() {
        return this.gameManager.isGameWon();
    }

    public List<Tower> getActiveTowers() {
        return this.gameManager.getInventory().getActiveTowers().stream().filter(not(Tower::isBroken)).toList();
    }
}
