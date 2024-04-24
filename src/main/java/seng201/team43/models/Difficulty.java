package seng201.team43.models;

public enum Difficulty {
    EASY("Easy", 2),
    MEDIUM("Medium", 1),
    HARD("Hard", 0.5);

    public final String label;
    public final double multiplier;

    private Difficulty(String label, double multiplier) {
        this.label = label;
        this.multiplier = multiplier;
    }
}
