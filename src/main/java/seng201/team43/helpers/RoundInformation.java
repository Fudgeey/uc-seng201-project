package seng201.team43.helpers;

import seng201.team43.models.Tower;

import java.util.ArrayList;

/**
 * Class to store information about the round.
 */
public class RoundInformation {
    private boolean won;
    private int moneyEarned;
    private final ArrayList<Tower> levelledUpTowers;

    /**
     * Initialises the round information with starting values
     */
    public RoundInformation() {
        this.won = true;
        this.moneyEarned = 0;
        this.levelledUpTowers = new ArrayList<>();
    }

    public boolean getWon() {
        return this.won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public int getMoneyEarned() {
        return this.moneyEarned;
    }

    public void addMoneyEarned(int money) {
        this.moneyEarned += money;
    }

    public ArrayList<Tower> getLevelledUpTowers() {
        return this.levelledUpTowers;
    }

    public void addLevelledUpTower(Tower tower) {
        this.levelledUpTowers.add(tower);
    }
}
