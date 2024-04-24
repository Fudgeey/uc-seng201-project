package seng201.team43.models;

public enum Resource {
    WATER("Water"),
    WOOD("Wood"),
    FOOD("Food");

    public final String label;

    Resource(String label) {
        this.label = label;
    }
}
