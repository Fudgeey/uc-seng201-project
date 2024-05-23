package seng201.team43.models.enums;

/**
 * Enum for game difficulty options
 */
public enum GameDifficulty {
    EASY("Easy", 0.75, 200, "#22a359"),
    MEDIUM("Medium", 1,  150, "#ff9c1c"),
    HARD("Hard", 1.5,  100, "#ff3737");

    public final String label;
    public final double multiplier;
    public final int startingMoney;
    public final String colour;

    GameDifficulty(String label, double multiplier, int startingMoney, String colour) {
        this.label = label;
        this.multiplier = multiplier;
        this.startingMoney = startingMoney;
        this.colour = colour;
    }
}
