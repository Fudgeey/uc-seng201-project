package seng201.team43;

import seng201.team43.gui.FXWindow;

/**
 * Entry point for game, runs JavaFX functions
 * @author seng201 teaching team, Luke Hallett, Riley Jeffcote
 */
public class App {

    /**
     * Entry point which runs the javaFX application
     * Due to how JavaFX works we must call MainWindow.launchWrapper() from here,
     * trying to run MainWindow itself will cause an error
     * @param args program arguments from command line
     */
    public static void main(String[] args) {
        FXWindow.launchWrapper(args);
    }
}
