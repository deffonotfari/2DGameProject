package Game;

//imports
import Game.GUI.GoalBar;
import Game.GUI.LevelNavigationPanel;
import Game.GUI.MainMenuPanel;
import Game.GUI.MenuBar;
import Game.GameLevels.*;
import Game.HighScore.GameOverScreen;
import Game.HighScore.HighScoreReader;
import Game.HighScore.HighScoreSaver;
import Game.InputControlSystem.MouseController;
import Game.InputControlSystem.PlayerController;
import Game.SceneManagement.GameView;
import Game.SceneManagement.GiveFocus;
import Game.SceneManagement.Tracker;
import Game.SoundClips.Music;
import city.cs.engine.SoundClip;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The main game entry point
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Game {

    /**Represents the current level of the game.*/
    private GameLevel currentLevel;

    /**The view of the game.*/
    private final GameView view;

    /**The controller for the player */
    private final PlayerController controller;

    /**The music player for the game.*/
    private final Music music;

    /** The panel for navigating levels*/
    private final LevelNavigationPanel levelNavigationPanel;

    /**The main frame of the game*/
    private final JFrame frame;

    /**The main menu panel of the game*/
    private final MainMenuPanel mainMenu;

    /**The goal bar panel of the game*/
    private final GoalBar goalBar;

    /**The volume of the music*/
    private float musicVolume = 0.5f;

    /**A boolean flag indicating whether the game is muted.*/
    private boolean muted = false;

    /**The HighScoreSaver instance is responsible for saving high scores to a file.*/
    private final HighScoreSaver highScoreSaver;

    /**
     * Constructs a new instance of the game.
     * Initializes the game components, such as music, level, view, controllers, and UI elements.
     */
    public Game() {
        //Initialise the music player
        music = new Music();

        //initialising the high score saver
        highScoreSaver = new HighScoreSaver();

        //Initialise level 1
        currentLevel = new Level1(this);
        currentLevel.populate(this);

        //Start playing the music for Level 1 if available
        if (!music.getGameMusic().isEmpty()) {
            music.getGameMusic().get(0).loop();
        } else{
            System.err.println("No music tracks found");
        }

        //Initialize the game view
        view = new GameView(currentLevel, currentLevel.getPlayer(),800, 600);

        //Add mouse controller for user interaction
        view.addMouseListener(new MouseController(view));

        //Add focus controller for keyboard input
        view.addMouseListener(new GiveFocus(view));
        view.setPlayer(currentLevel.getPlayer());

        //Initialize the player controller for keyboard input
        controller = new PlayerController(currentLevel.getPlayer(), this);

        //Add the player controller to the game view
        view.addKeyListener(controller);

        //asking for giving focus to keyboard
        view.requestFocus();

        //tracking the body of the player
        currentLevel.addStepListener(new Tracker(view, currentLevel.getPlayer()));

        //creating a Java window (frame) and add the game
        frame = new JFrame("Night City");
        frame.add(view);

        mainMenu = new MainMenuPanel(currentLevel.getGame());
        levelNavigationPanel = new LevelNavigationPanel(currentLevel, currentLevel.getGame());
        MenuBar control = new MenuBar(this, view);
        frame.setJMenuBar(control);

        //level instructions
        goalBar = new GoalBar(currentLevel);
        frame.add(goalBar.getGoalLevel(), BorderLayout.NORTH);
        frame.pack();

        //enable the frame to quit the application when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        frame.setResizable(false); //no resizing
        frame.pack(); // size the frame to fit the world view
        frame.setVisible(true); //making frame visible

        //start level1 simulation
        currentLevel.start();
    }


    /**
     * Restores the player's stats from an old player instance to a new player instance.
     * @param currentPlayer The new player instance whose stats are being restored.
     * @param oldPlayer     The old player instance from which the stats are retrieved.
     */
    public void restorePlayerStats(Player currentPlayer, Player oldPlayer){
        //set the current player in the view
        view.setPlayer(currentPlayer);

        //Add a step listener to track the current player's body
        currentLevel.addStepListener(new Tracker(view, currentPlayer));

        //update controls for the current player
        controller.updateControls(currentPlayer);

        //Restore player stats
        currentPlayer.setCookieCount(oldPlayer.getCookieCount());
        currentPlayer.setCoinCount(oldPlayer.getCoinCount());
        currentPlayer.setPoint(oldPlayer.getPoints());
        currentPlayer.setRemainingLives(oldPlayer.getRemainingLives());
        currentPlayer.setTicketCount(oldPlayer.getTicketCount());
    }


    /**
     * Sets the current level of the game, mainly used for loading saved progress
     * @param level The new level to be set.
     */
    public void setLevel(GameLevel level) {
        //stop the current level
        this.currentLevel.stop();

        //update a new level
        this.currentLevel = level;

        //update the view and background
        view.setWorld(this.currentLevel);
        this.currentLevel.addStepListener(new Tracker(view, currentLevel.getPlayer()));
        view.changeBackground(currentLevel.getLevelNo());

        //set the player for current level and update controls
        view.setPlayer(currentLevel.getPlayer());
        controller.updateControls(currentLevel.getPlayer());

        //restore player stats
        currentLevel.getPlayer().setCookieCount(level.getPlayer().getCookieCount());
        currentLevel.getPlayer().setCoinCount(level.getPlayer().getCoinCount());
        currentLevel.getPlayer().setPoint(level.getPlayer().getPoints());
        currentLevel.getPlayer().setRemainingLives(level.getPlayer().getRemainingLives());
        currentLevel.getPlayer().setTicketCount(level.getPlayer().getTicketCount());

        //Switching between sound effects
        if (muted) {
            music.mute();
        } else {
            int levelIndex = Integer.parseInt(currentLevel.getLevelName().substring(5));
            music.getGameMusic().get(levelIndex - 1).loop();
        }

        // Start the new level
        this.currentLevel.start();
    }


    /**
     * Proceeds to the next level in the game.
     */
    public void goToNextLevel(){
        //Save the player from the current level
        Player oldPlayer = currentLevel.getPlayer();

        if(currentLevel instanceof Level1){
            // Stop the current level
            currentLevel.stop();

            // Create and initialize the new level
            currentLevel = new Level2(this);
            currentLevel.populate(this);

            // Update the view and background
            view.setWorld(currentLevel);
            view.changeBackground(2);
            changeInstructions(currentLevel);

            // Restore player stats
            Player currentPlayer = currentLevel.getPlayer();
            restorePlayerStats(currentPlayer, oldPlayer);

            //switching between sound effects
            if (muted){
                music.mute();
            } else {
                music.getGameMusic().get(0).stop();
                music.getGameMusic().get(1).loop();
            }

            // Start the new level
            currentLevel.start();
        } else if (currentLevel instanceof Level2){
            // Stop the current level
            currentLevel.stop();

            // Create and initialize the new level
            currentLevel = new Level3(this);
            currentLevel.populate(this);

            // Update the view and background
            view.setWorld(currentLevel);
            view.changeBackground(3);
            changeInstructions(currentLevel);

            // Restore player stats
            Player currentPlayer = currentLevel.getPlayer();
            restorePlayerStats(currentPlayer, oldPlayer);

            //switching between sound effects
            if (muted){
                music.mute();
            } else {
                music.getGameMusic().get(1).stop();
                music.getGameMusic().get(2).loop();
            }

            // Start the  new level
            currentLevel.start();
        } else if(currentLevel instanceof Level3) {
            // Stop the current level
            currentLevel.stop();

            // Create and initialize the new level
            currentLevel = new Level4(this);
            currentLevel.populate(this);
            changeInstructions(currentLevel);

            // Update the view and background
            view.setWorld(currentLevel);
            view.changeBackground(4);

            // Restore player stats
            Player currentPlayer = currentLevel.getPlayer();
            restorePlayerStats(currentPlayer, oldPlayer);

            if (muted){
                music.mute();
            } else {
                music.getGameMusic().get(2).stop();
                music.getGameMusic().get(3).loop();
            }

            // Start the new level
            currentLevel.start();
        } else if (currentLevel instanceof Level4) {
            int currentScore = currentLevel.getPlayer().getPoints();

            //Players who have full lives at the end of the game will receive 500 points
            if(currentLevel.getPlayer().getRemainingLives() == 6){
                currentScore = currentScore + 500;
            }

            // First, save the current score
            highScoreSaver.saveScore(currentScore);

            // Then, retrieve the high scores
            List<String> highScores = HighScoreReader.getHighScores();

            // Finally, create the GameOver instance
            GameOverScreen gameOver = new GameOverScreen(this, currentScore, highScores);
        }
    }


    /**
     * a method which allows users to go back to previous level
     */
    public void goToPreviousLevel(){
        //Save the player from the current level
        Player oldPlayer = currentLevel.getPlayer();

        if(currentLevel instanceof Level4){
            // Stop the current level
            currentLevel.stop();

            // Create and initialize the new level
            currentLevel = new Level3(this);
            currentLevel.populate(this);

            // Update the view and background
            view.setWorld(currentLevel);
            view.changeBackground(3);
            changeInstructions(currentLevel);

            // Restore player stats
            Player currentPlayer = currentLevel.getPlayer();
            restorePlayerStats(currentPlayer, oldPlayer);

            //switching between sound effects
            if (muted){
                music.mute();
            } else {
                music.getGameMusic().get(3).stop();
                music.getGameMusic().get(2).loop();
            }

            // Start the new level
            currentLevel.start();
        } else if(currentLevel instanceof Level3){
            // Stop the current level
            currentLevel.stop();

            // Create and initialize the new level
            currentLevel = new Level2(this);
            currentLevel.populate(this);

            // Update the view and background
            view.setWorld(currentLevel);
            view.changeBackground(2);
            changeInstructions(currentLevel);

            // Restore player stats
            Player currentPlayer = currentLevel.getPlayer();
            restorePlayerStats(currentPlayer, oldPlayer);

            //switching between sound effects
            if (muted){
                music.mute();
            } else {
                music.getGameMusic().get(2).stop();
                music.getGameMusic().get(1).loop();
            }

            // Start the new level
            currentLevel.start();
        } else if (currentLevel instanceof  Level2){
            // Stop the current level
            currentLevel.stop();

            // Create and initialize the new level
            currentLevel = new Level1(this);
            currentLevel.populate(this);

            // Update the view and background
            view.setWorld(currentLevel);
            view.changeBackground(1);
            changeInstructions(currentLevel);

            // Restore player stats
            Player currentPlayer = currentLevel.getPlayer();
            restorePlayerStats(currentPlayer, oldPlayer);

            //switching between sound effects
            if (muted){
                music.mute();
            } else {
                music.getGameMusic().get(1).stop();
                music.getGameMusic().get(0).loop();
            }

            // Start the new level
            currentLevel.start();
        }
    }

    /**
     * Restarts the current level of the game.
     */
    public void restart() {
        if (currentLevel instanceof Level1) {
            //stop the current level
            currentLevel.stop();

            //Create and initialize the level again
            currentLevel = new Level1(this);
            currentLevel.populate(this);

            //Update the view and background
            view.setWorld(currentLevel);
            view.changeBackground(1);
            view.setPlayer(currentLevel.getPlayer());

            //update the tracker and controller, so it follows the user
            currentLevel.addStepListener(new Tracker(view, currentLevel.getPlayer()));
            changeInstructions(currentLevel);
            controller.updateControls(currentLevel.getPlayer());

            //start the next level
            currentLevel.start();
        }

        if (currentLevel instanceof Level2) {
            //stop the current level
            currentLevel.stop();

            //Create and initialize the level again
            currentLevel = new Level2(this);
            currentLevel.populate(this);

            //Update the view and background
            view.setWorld(currentLevel);
            view.changeBackground(2);
            view.setPlayer(currentLevel.getPlayer());

            //update the tracker and controller, so it follows the user
            currentLevel.addStepListener(new Tracker(view, currentLevel.getPlayer()));
            changeInstructions(currentLevel);
            controller.updateControls(currentLevel.getPlayer());

            //start the next level
            currentLevel.start();
        }

        if (currentLevel instanceof Level3) {
            //stop the current level
            currentLevel.stop();

            //Create and initialize the level again
            currentLevel = new Level3(this);
            currentLevel.populate(this);

            //Update the view and background
            view.setWorld(currentLevel);
            view.changeBackground(3);
            view.setPlayer(currentLevel.getPlayer());

            //update the tracker and controller, so it follows the user
            currentLevel.addStepListener(new Tracker(view, currentLevel.getPlayer()));
            changeInstructions(currentLevel);
            controller.updateControls(currentLevel.getPlayer());

            //start the next level
            currentLevel.start();
        }

        if (currentLevel instanceof Level4) {
            //stop the current level
            currentLevel.stop();

            //Create and initialize the level again
            currentLevel = new Level4(this);
            currentLevel.populate(this);

            //Update the view and background
            view.setWorld(currentLevel);
            view.changeBackground(4);
            view.setPlayer(currentLevel.getPlayer());

            //update the tracker and controller, so it follows the user
            currentLevel.addStepListener(new Tracker(view, currentLevel.getPlayer()));
            changeInstructions(currentLevel);
            controller.updateControls(currentLevel.getPlayer());

            //start the next level
            currentLevel.start();
        }
    }


    /**
     * Transitions the game to the level navigation panel.
     */
    public void transitionToLevelNavigation(){
        frame.remove(mainMenu.getMainMenu());
        frame.add(levelNavigationPanel.getHandlingLevel(), BorderLayout.SOUTH);
        frame.pack();
    }


    /**
     * Transitions the game to the main menu from the level navigation menu.
     */
    public void transitionToMenuFromLM(){
        frame.remove(levelNavigationPanel.getHandlingLevel());
        frame.add(mainMenu.getMainMenu(), BorderLayout.WEST);
        frame.pack();
    }


    /**
     * Navigates the game to the main menu.
     */
    public void goToMainMenu(){
        frame.add(mainMenu.getMainMenu(), BorderLayout.WEST);
        frame.pack();
    }


    /**
     * Removes the main menu from the frame.
     */
    public void removeMenu(){
        frame.remove(mainMenu.getMainMenu());
        frame.pack();
    }


    /**
     * Retrieves the current level of the game.
     * @return The current level of the game.
     */
    public GameLevel getLevel() {
        return currentLevel;
    }


    /**
     * Mutes the game music.
     */
    public void mute(){
        muted = true;
        music.mute();
    }


    /**
     * Unmutes the game music and resumes playing the music based on the current level.
     */
    public void unmute(){
        muted = false;

        // Stop all music tracks and loop the appropriate track based on the current level
        if (currentLevel instanceof Level1) {
            music.getGameMusic().get(1).stop();
            music.getGameMusic().get(2).stop();
            music.getGameMusic().get(3).stop();

            music.getGameMusic().get(0).loop();
        }if (currentLevel instanceof Level2) {
            music.getGameMusic().get(0).stop();
            music.getGameMusic().get(2).stop();
            music.getGameMusic().get(3).stop();

            music.getGameMusic().get(1).loop();
        }
        if (currentLevel instanceof Level3) {
            music.getGameMusic().get(0).stop();
            music.getGameMusic().get(1).stop();
            music.getGameMusic().get(3).stop();

            music.getGameMusic().get(2).loop();
        }
        if (currentLevel instanceof Level4) {
            music.getGameMusic().get(0).stop();
            music.getGameMusic().get(1).stop();
            music.getGameMusic().get(2).stop();

            music.getGameMusic().get(3).loop();
        }
    }

    /**
     * Retrieves the current music volume.
     * @return The current music volume.
     */
    public float getMusicVolume() {
        return musicVolume;
    }


    /**
     * Sets the volume level of the game music.
     * @param volume The volume level to be set.
     */
    public void setMusicVolume(float volume) {
        //volume needs to be within this range
        volume = Math.max(volume, -80.0f);
        volume = Math.min(volume, 6.0206f);

        this.musicVolume = volume;

        // Update the volume of the music
        for (SoundClip audio : music.getGameMusic()) {
            if (volume == 0){
                audio.setVolume(0);
            } else{
                audio.setVolume(volume);
            }
        }
    }


    /**
     * Changes the displayed instructions for the current level.
     * @param level The level for which the instructions should be displayed.
     */
    public void changeInstructions(GameLevel level){
        frame.remove(goalBar.getGoalLevel());
        GoalBar goalbar = new GoalBar(level);
        frame.add(goalbar.getGoalLevel(), BorderLayout.NORTH);
        frame.pack();
    }

    /**
     * Restarts the current level, stopping the current game state and initializing a new instance of Level 1.
     * This method is typically called when the player chooses to replay the game after a game over or completion.
     */
    public void replay() {
        // Stop the current level
        currentLevel.stop();

        // Create and initialize Level 1
        currentLevel = new Level1(this);
        currentLevel.populate(this);

        // Update the view and background
        view.setWorld(currentLevel);
        view.changeBackground(1);
        view.setPlayer(currentLevel.getPlayer());

        music.getGameMusic().get(0).play();

        // Update the tracker and controller
        currentLevel.addStepListener(new Tracker(view, currentLevel.getPlayer()));
        controller.updateControls(currentLevel.getPlayer());

        // Start Level 1
        currentLevel.start();
    }


    /**
     * Retrieves the game view.
     * @return The game view.
     */
    public GameView getView() {
        return view;
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
}