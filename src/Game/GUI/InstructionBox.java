package Game.GUI;

//imports
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;


/**
 * Represents a dialog box displaying game instructions.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class InstructionBox {
    /** The JFrame for the instruction box. */
    private final JFrame instructionBox;

    /**
     * Constructs an InstructionBox instance.
     */
    public InstructionBox() {
        //creating a new JFrame for the instruction box
        instructionBox = new JFrame();
        instructionBox.setSize(400, 300);
        instructionBox.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //this JPanel will hold the information
        JPanel information = new JPanel(new GridLayout(0, 1));

        //goal of the game section
        JLabel goalLabel = new JLabel("Goal of the game:");
        goalLabel.setHorizontalAlignment(JLabel.CENTER);

        //movement instructions section
        JLabel moveLabel = new JLabel("How to Move?");
        moveLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel spaceAboveMove = new JLabel(" ");

        //Adding underlines to goalLabel and moveLabel to make a distinction between title and information
        Font font = goalLabel.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        goalLabel.setFont(font.deriveFont(attributes));

        Font font2 = moveLabel.getFont();
        Map<TextAttribute, Object> attr = new HashMap<>(font2.getAttributes());
        attr.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        moveLabel.setFont(font2.deriveFont(attr));

        //Goal of the game labels
        JLabel goal1Label = new JLabel("Avoid the cannons and the enemies!");
        JLabel goal2Label = new JLabel("Collect as many cookies and coins.");
        JLabel goal3Label = new JLabel("The green cookies are the bad cookies.");
        JLabel goal4Label = new JLabel("You can shoot them and turn them into good cookies.");

        //movement instructions labels
        JLabel move1Label = new JLabel("You can use the arrows for movement.");
        JLabel move2Label = new JLabel("To shoot left, you need to press 'A'.");
        JLabel move3Label = new JLabel("To shoot right, you need to press 'D'.");
        JLabel move4Label = new JLabel("Space - Displays Menu.");

        //creating a close button
        JButton close = new JButton("Close");
        close.addActionListener(e -> instructionBox.setVisible(false));

        // Adding components to the panel
        information.add(goalLabel);
        information.add(goal1Label);
        information.add(goal2Label);
        information.add(goal3Label);
        information.add(goal4Label);

        information.add(spaceAboveMove);
        information.add(moveLabel);
        information.add(move1Label);
        information.add(move2Label);
        information.add(move3Label);
        information.add(move4Label);


        information.add(close);

        //the panel being added to the JFrame of the game window
        instructionBox.add(information);
    }

    /**
     * Sets the visibility of the instruction box.
     * @param visible Whether the instruction box should be visible.
     */
    public void setVisible(boolean visible) {
        instructionBox.setVisible(visible);
    }
}
