package Game.GUI;

//imports
import Game.GameLevels.*;
import javax.swing.*;
import java.awt.*;


/**
 * This GUI component implements the goals within the game,
 * where player progresses to the next level upon completion.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class GoalBar extends JFrame {
    /** Panel to contain the goal label. */
    private final JPanel goalPanel;


    /**
     * Constructs a GoalBar instance for a specific game level.
     * @param level The game level for which the goal is displayed.
     */
    public GoalBar(GameLevel level){
        //initialize panel and label
        goalPanel = new JPanel();
        JLabel goalLabel = new JLabel();

        //setting the layout of the panel
        setLayout(new BorderLayout());
        goalPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Setting goal label text and style based on each level
        if(level instanceof Level1){
            goalLabel.setText("Goal: Defeat the ghost and collect at least 3 cookies");
            goalLabel.setFont(new Font("Arial", Font.BOLD, 14));
            goalPanel.setBackground(new Color(144, 78, 172 ));
            goalLabel.setForeground(Color.WHITE);
            goalPanel.add(goalLabel);
        } else if(level instanceof Level2){
            goalLabel.setText("Goal: Defeat the dark figure and collect all coins");
            goalLabel.setFont(new Font("Arial", Font.BOLD, 14));
            goalPanel.setBackground(new Color(242, 166, 94 ));
            goalPanel.add(goalLabel);
        } else if(level instanceof Level3){
            goalLabel.setText("Goal: Collect All Coins");
            goalLabel.setFont(new Font("Arial", Font.BOLD, 14));
            goalPanel.setBackground(new Color(22, 28, 55 ));
            goalLabel.setForeground(Color.WHITE);
            goalPanel.add(goalLabel);
        } else if(level instanceof Level4){
            goalLabel.setText("Goal: Defeat the ghost and the dark figure");
            goalLabel.setFont(new Font("Arial", Font.BOLD, 14));
            goalPanel.setBackground(new Color(138, 138, 209 ));
            goalPanel.add(goalLabel);
        }
    }


    /**
     * Retrieves the panel containing the goal information.
     * @return The panel containing the goal label.
     */
    public JPanel getGoalLevel() {
        return goalPanel;
    }
}
