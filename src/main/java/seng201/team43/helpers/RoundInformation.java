package seng201.team43.helpers;

import seng201.team43.models.Purchasable;

import java.util.ArrayList;

public class RoundInformation {
    private boolean won;
    public double moneyEarned;
    public double experienceEarned;

    public ArrayList<Purchasable> levelledUpTowers;

    private String title;
    private String message;

    public RoundInformation() {
        this.won = true;
        this.moneyEarned = 0;
        this.experienceEarned = 0;

        this.title = null;
        this.message = null;

        this.levelledUpTowers = new ArrayList<>();
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getWon() {
        return this.won;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }
}
