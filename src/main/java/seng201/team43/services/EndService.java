package seng201.team43.services;

import seng201.team43.models.GameManager;

public class EndService {
    private final GameManager gameManager;

    public EndService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public double getMoneyGained() {
        return this.gameManager.getMoneyGained();
    }

    public String getName() {
        return this.gameManager.getName();
    }

    public boolean isGameWon() {
        return this.gameManager.isGameWon();
    }

    public int getRoundsWon() {
        return this.gameManager.getCurrentRound() - 1;
    }

    public int getRoundCount() {
        return this.gameManager.getRoundCount();
    }

    public int getLevel() {
        return this.gameManager.getLevel();
    }

    public int getExperienceGained() {
        return this.gameManager.getExperienceGained();
    }

    public void closeGame() {
        this.gameManager.quitGame();
    }
}
