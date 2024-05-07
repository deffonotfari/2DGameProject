package Game.GUI;

//imports
import Game.Game;
import javax.swing.*;
import java.awt.*;


/**
 * Represents the main menu panel of the game.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class MainMenuPanel extends JPanel{
    /** The main panel containing menu components. */
    private final JPanel mainMenu;

    /** The instruction box for displaying game instructions. */
    private final InstructionBox instructionBox;


    /**
     * Retrieves the main menu panel.
     * @return The main menu panel.
     */
    public JPanel getMainMenu() {
        return mainMenu;
    }


    /**
     * Constructs the main menu panel.
     * @param game The game instance.
     */
    public MainMenuPanel(Game game) {
        //creating main menu panel
        mainMenu = new JPanel();
        mainMenu.setLayout(new GridBagLayout());
        mainMenu.setBackground(new Color(53, 35, 77));

        //creating the buttons
        JButton handlingLevels = new JButton("Level Navigation");
        JButton instructions = new JButton("Instructions");
        JButton exitMenu = new JButton("Exit Menu");
        JLabel label = new JLabel("Main Menu");
        instructionBox = new InstructionBox();

        // Create and set preferred sizes
        handlingLevels.setPreferredSize(new Dimension(200, 136));
        instructions.setPreferredSize(new Dimension(200, 136));
        exitMenu.setPreferredSize(new Dimension(200, 136));

        //Set text position and alignment
        instructions.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setVerticalAlignment(SwingConstants.BOTTOM);

        // Set label font size
        label.setFont(label.getFont().deriveFont(30.0f));
        label.setForeground(Color.WHITE);

        // Set up grid constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weighty = 0.22f;
        gbc.fill = GridBagConstraints.VERTICAL;

        // Add label with insets (including space)
        gbc.gridy = 0;
        gbc.insets = new Insets(29, 60, 0, 55); // Increase top inset to add space
        mainMenu.add(label, gbc);

        // Set the background color for the panel
        JPanel spacePanel = new JPanel();
        spacePanel.setBackground(new Color(53, 35, 77));
        spacePanel.setOpaque(true);
        gbc.insets = new Insets(4, 0, 0, 0);
        gbc.gridy = 1;
        mainMenu.add(spacePanel, gbc);

        // Add components to mainMenu
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridy = 2;
        gbc.weighty = 0.0;
        mainMenu.add(handlingLevels, gbc);

        // Add space between buttons
        JPanel spacePanel2 = new JPanel();
        spacePanel2.setBackground(new Color(53, 35, 77));
        gbc.insets = new Insets(3, 0, 0, 0);
        gbc.gridy = 3;
        mainMenu.add(spacePanel2, gbc);

        gbc.gridy = 4;
        mainMenu.add(instructions, gbc);

        gbc.gridy = 5;
        JPanel spacePanel3 = new JPanel();
        spacePanel3.setBackground(new Color(53, 35, 77));
        mainMenu.add(spacePanel3, gbc);

        gbc.gridy = 6;
        mainMenu.add(exitMenu, gbc);


        //Giving functions to the buttons
        handlingLevels.addActionListener(e -> game.transitionToLevelNavigation());

        //enables the user to leave the main menu
        exitMenu.addActionListener(e -> game.removeMenu());

        //displays the instruction box
        instructions.addActionListener(e -> instructionBox.setVisible(true));
    }
}
