package seng201.team43.exceptions;

public class GameError extends Exception {
    private final String message;

    public GameError(String message) {
        super();

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
