package seng201.team43.helpers;

public class RoundInformation {
    private boolean won;
    public double moneyEarned;
    public double experienceEarned;

    private String title;
    private String message;

    public RoundInformation() {
        this.won = true;
        this.moneyEarned = 0;
        this.experienceEarned = 0;

        this.title = null;
        this.message = null;
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
