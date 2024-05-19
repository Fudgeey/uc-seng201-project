package seng201.team43.services;

import seng201.team43.models.GameManager;

/**
 * Class for the logic behind the ending screen.
 * @author Riley Jeffcote, Luke Hallett.
 */
public class EndService {
    private final GameManager gameManager;

    /**
     * Initialises the end service with game manager.
     * @param gameManager to initialise end service.
     */
    public EndService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public double getMoneyGained() {
        return this.gameManager.getMoneyGained();
    }

    public String getName() {
        return this.gameManager.getName();
    }

    /**
     * Returns whether the game is won.
     * @return boolean representing if the game is won or lost.
     */
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

    /**
     * Closes the game.
     */
    public void closeGame() {
        this.gameManager.quitGame();
    }
}
