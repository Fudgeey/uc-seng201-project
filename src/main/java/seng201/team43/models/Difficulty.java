package seng201.team43.models;

public enum Difficulty {
    EASY("Easy", 2, "#22a359"),
    MEDIUM("Medium", 1, "#ff9c1c"),
    HARD("Hard", 0.5, "#ff3737");

    public final String label;
    public final double multiplier;
    public final String colour;

    Difficulty(String label, double multiplier, String colour) {
        this.label = label;
        this.multiplier = multiplier;
        this.colour = colour;
    }
}
