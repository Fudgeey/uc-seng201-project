package seng201.team43.models.enums;

/**
 * Class for resource.
 * @author Riley Jeffcote, Luke Hallett.
 */
public enum Resource {
    WATER("Water"),
    WOOD("Wood"),
    FOOD("Food");

    public final String label;

    /**
     * Initialises resource
     * @param label initialises label.
     */
    Resource(String label) {
        this.label = label;
    }
}
