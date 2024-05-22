# Tower Dynasty - SENG201 Project
A resource stockpiling game where you have to fill carts from towers. Made for SENG201. This readme shows how to build, run, and test the game.

## Authors
- Luke Hallett
- Riley Jeffcote
- SENG201 Teaching team

## Prerequisites
- JDK >= 17 [click here to get the latest stable OpenJDK release (as of writing this README)](https://jdk.java.net/18/)
- *(optional)* Gradle [Download](https://gradle.org/releases/) and [Install](https://gradle.org/install/)

## Importing Project (Using IntelliJ)
IntelliJ has built-in support for Gradle. To import your project:

- Launch IntelliJ and choose `Open` from the start-up window.
- Select the project and click open
- At this point in the bottom right notifications you may be prompted to 'load gradle scripts', If so, click load

**Note:** *If you run into dependency issues when running the app or the Gradle pop up doesn't appear then open the Gradle sidebar and click the Refresh icon.*

## Run Project 
1. Open a command line interface inside the project directory and run `./gradlew run` to run the app.
2. The app should then open a new window, this may not be displayed over IntelliJ but can be easily selected from the taskbar

## Build and Run Jar
1. Open a command line interface inside the project directory and run `./gradlew jar` to create a packaged Jar. The Jar file is located at build/libs/seng201_team43-1.0-SNAPSHOT.jar
2. Navigate to the build/libs/ directory (you can do this with `cd build/libs`)
3. Run the command `java -jar seng201_team43-1.0-SNAPSHOT.jar` to open the application.

## Run Tests
1. Open a command line interface inside the project directory and run `./gradlew test` to run the tests.
2. Test results should be printed to the command line

## Copyright
- Tower icons used under license. [Link to assets](https://admurin.itch.io/mega-admurins-freebies).
- Game background used under [CC0](https://creativecommons.org/publicdomain/zero/1.0/) license. [Link to assets](https://kenney.nl/assets/tiny-dungeon).
- Title font used under license. [Link to assets](https://www.fontspace.com/pixemon-trial-font-f107824).