package Game.GUI;

//imports
import Game.Collectibles.CookieSpawns;
import Game.Game;
import Game.GameLevels.GameLevel;
import Game.DataManagement.SaverAndLoader;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


/**
 * Represents a panel for navigating between levels and performing related actions.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class LevelNavigationPanel extends JPanel{
    /** Panel containing buttons for level handling. */
    private final JPanel handlingLevel;

    /**
     * Retrieves the handling level panel.
     * @return The handling level panel.
     */
    public JPanel getHandlingLevel(){
        return handlingLevel;
    }


    /**
     * Constructs a LevelNavigationPanel instance.
     *
     * @param level The current game level.
     * @param game The game instance.
     */
    public LevelNavigationPanel(GameLevel level, Game game){
        //creating the buttons
        handlingLevel = new JPanel();
        JButton backLevel = new JButton("Previous");
        JButton restartLevel = new JButton("Restart");
        JButton forwardLevel = new JButton("Next");
        JLabel information = new JLabel("GameLevels: ");
        JButton saveLevel = new JButton("Save");
        JSeparator separator = new JSeparator();
        JButton loadButton = new JButton("Load");
        JButton mainMenu = new JButton("Menu");


        //positioning the buttons for display
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(handlingLevel);
        handlingLevel.setLayout(new BoxLayout(handlingLevel,BoxLayout.X_AXIS));
        handlingLevel.add(information);
        handlingLevel.add(backLevel);
        handlingLevel.add(restartLevel);
        handlingLevel.add(forwardLevel);
        handlingLevel.add(separator);
        handlingLevel.add(mainMenu);
        handlingLevel.add(saveLevel);
        handlingLevel.add(loadButton);


        //designing settings
        handlingLevel.setBackground(new Color(53, 35, 77));
        separator.setForeground(new Color(53, 35, 77));
        information.setForeground(Color.white);


        //Enables user to go back to previous levels
        backLevel.addActionListener(e -> game.goToPreviousLevel());

        //Enables user to go to the next level
        forwardLevel.addActionListener(e -> game.goToNextLevel());

        //enables user to restart current level
        restartLevel.addActionListener(e -> game.restart());

        //opens the main menu
        mainMenu.addActionListener(e -> game.transitionToMenuFromLM());

        //saves user's current game progress
        saveLevel.addActionListener(e -> {
            try {
                SaverAndLoader.save("data/DataManagement/SaverAndLoader.txt",game.getLevel());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //loads the user's progress after user presses restart or for any other circumstances
        loadButton.addActionListener(e -> {
            try {
                GameLevel loadedLevel = SaverAndLoader.load("data/DataManagement/SaverAndLoader.txt", game, level.getPlayer());
                game.setLevel(loadedLevel);
                level.populate(game);

                //cookie starts spawning again
                CookieSpawns cookieSpawns = new CookieSpawns(level);
                cookieSpawns.startSpawning();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }
}