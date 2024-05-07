package Game.GUI;

//imports
import Game.Game;
import Game.SceneManagement.GameView;
import javax.swing.*;
import java.awt.*;

/**
 * The MenuBar class represents the menu bar of the game GUI.
 * It contains menus for game controls and volume settings.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class MenuBar extends JMenuBar {
    /**
     * Resizes the given ImageIcon to the specified dimensions.
     * @param icon The ImageIcon to resize.
     * @return The resized ImageIcon.
     */
    private ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    /**
     * Constructs a MenuBar with the specified Game instance.
     * @param game The Game instance associated with the menu bar.
     * @param view The GameView instance associated with the menu bar.
     */
    public MenuBar(Game game, GameView view) {
        //Create the game menu
        JMenu gameMenu = new JMenu("Game Controls");
        JMenu volumeMenu = new JMenu("Volume");

        //creating the image icons for each option
        ImageIcon pauseIcon = resizeIcon(new ImageIcon("data/buttons/pause.png"));
        ImageIcon resumeIcon = resizeIcon(new ImageIcon("data/buttons/resume.png"));
        ImageIcon muteIcon = resizeIcon(new ImageIcon("data/buttons/noMusic.png"));
        ImageIcon unmuteIcon = resizeIcon(new ImageIcon("data/buttons/unmute.png"));
        ImageIcon volumeIcon = resizeIcon(new ImageIcon("data/buttons/volume.png"));
        ImageIcon menuIcon = resizeIcon(new ImageIcon("data/buttons/menu.png"));
        ImageIcon quitIcon = resizeIcon(new ImageIcon("data/buttons/quit.png"));

        //crating menu items
        JMenuItem pause = new JMenuItem("Pause", pauseIcon);
        JMenuItem resume = new JMenuItem("Resume", resumeIcon);
        JMenuItem quit = new JMenuItem("Quit", quitIcon);
        JMenuItem mainMenu = new JMenuItem("Menu", menuIcon);

        //The action listener will pause and mute the game when the user presses pause
        pause.addActionListener(e -> {
            game.getLevel().stop();
            view.setPause(true); //displays a different image
            game.mute();

        });

        //This will resume the gameplay and unmute the game
        resume.addActionListener(e ->{
            game.getLevel().start();
            view.setPause(false);
            game.unmute();
        });

        //Displays the main menu
        mainMenu.addActionListener(e -> game.transitionToMenuFromLM());

        //Adding menu items to the game menu
        gameMenu.add(pause);
        gameMenu.add(resume);
        gameMenu.add(quit);
        gameMenu.add(mainMenu);

        //Creating volume control items
        JMenuItem musicAdjust = new JMenuItem("Adjust Sound", volumeIcon);
        JMenuItem mute = new JMenuItem("Mute", muteIcon);
        JMenuItem unmute = new JMenuItem("Unmute", unmuteIcon);

        //This will mute the game
        mute.addActionListener(e -> game.mute());

        //This will unmute the game
        unmute.addActionListener(e -> game.unmute());

        //Adding volume control items to the volume menu
        volumeMenu.add(musicAdjust);
        volumeMenu.add(mute);
        volumeMenu.add(unmute);

        //Adding the game menu and volume menu to the menu bar
        this.add(gameMenu);
        this.add(volumeMenu);

        //Adding action listener for quit
        quit.addActionListener(e -> {
            // Get the parent frame
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

            // Create the confirmation dialog
            int confirm = JOptionPane.showConfirmDialog(parentFrame, "Do you want to leave?", "Quit", JOptionPane.YES_NO_OPTION);

            //If the user confirms, exit the application
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        musicAdjust.addActionListener(e -> {
            //Create a panel to hold the volume slider
            JPanel panel = new JPanel(new BorderLayout());

            //Create a label for the volume slider
            JLabel volumeLabel = new JLabel("Music Volume");
            volumeLabel.setFont(volumeLabel.getFont().deriveFont(Font.PLAIN, 16));

            //Create the volume slider
            JSlider musicVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (game.getMusicVolume() * 100));
            musicVolumeSlider.setMajorTickSpacing(20);
            musicVolumeSlider.setMinorTickSpacing(5);
            musicVolumeSlider.setPaintTicks(true);
            musicVolumeSlider.setPaintLabels(true);
            musicVolumeSlider.setSnapToTicks(true);

            //Add change listener to update music volume when slider is adjusted
            musicVolumeSlider.addChangeListener(event -> {
                JSlider source = (JSlider) event.getSource();
                if (!source.getValueIsAdjusting()) {
                    int val = source.getValue();
                    float volume = val / 100f;

                    //Adjust volume with a minimum value of 0.001 to avoid exceptions
                    if (val == 0){
                        game.setMusicVolume(0.001f);
                    } else{
                        game.setMusicVolume(volume);
                    }
                }
            });

            // Add components to the panel
            panel.add(volumeLabel, BorderLayout.CENTER);
            panel.add(musicVolumeSlider, BorderLayout.CENTER);

            // Get the parent frame
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

            // Calculate the coordinates to center the dialog
            int dialogX = parentFrame.getX() + (parentFrame.getWidth() - panel.getPreferredSize().width) / 2;
            int dialogY = parentFrame.getY() + (parentFrame.getHeight() - panel.getPreferredSize().height) / 2;

            // Display the volume adjustment dialog
            JOptionPane.showMessageDialog(parentFrame, panel, "Volume Adjustment", JOptionPane.PLAIN_MESSAGE);
        });

    }
}
