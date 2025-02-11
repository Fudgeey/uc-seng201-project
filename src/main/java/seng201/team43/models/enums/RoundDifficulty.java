package seng201.team43.models.enums;

/**
 * Class for round difficulty.
 * @author Riley Jeffcote, Luke Hallett.
 */
public enum RoundDifficulty {
    EASY("Easy", 100, "#22a359"),
    MEDIUM("Medium",  80, "#ff9c1c"),
    HARD("Hard",  60, "#ff3737");

    public final String label;
    public final int trackDistance;
    public final String colour;

    /**
     * Initialises round difficulty.
     * @param label initialises label.
     * @param trackDistance initialises track distance.
     * @param colour initialises colour. e.g. green for easy.
     */
    RoundDifficulty(String label, int trackDistance, String colour) {
        this.label = label;
        this.trackDistance = trackDistance;
        this.colour = colour;
    }
}
