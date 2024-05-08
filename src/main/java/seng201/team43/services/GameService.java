package seng201.team43.services;

import seng201.team43.models.*;

import java.util.ArrayList;

public class GameService {
    private final GameManager gameManager;

    public GameService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void startRound() {
        // Add between 1 and 3 carts per round - each of a random type
    }

    public void setRoundDifficulty(RoundDifficulty roundDifficulty) {
        this.gameManager.setRoundDifficulty(roundDifficulty);
    }

    public ArrayList<Tower> getActiveTowers() {
        return this.gameManager.getInventory().getActiveTowers();
    }

    public RoundDifficulty getRoundDifficulty() {
        return this.gameManager.getRoundDifficulty();
    }
}
