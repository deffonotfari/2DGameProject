package Game.HighScore;

//imports
import Game.Game;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Represents a window displaying the game over screen with score information and options.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class GameOverScreen extends JFrame {
    /**
     * Constructs a GameOverScreen window.
     *
     * @param game        The Game instance.
     * @param currentScore The player's current score.
     * @param highScores  The list of high scores.
     */
    public GameOverScreen(Game game, int currentScore, List<String> highScores) {
        setTitle("Game Over");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("Game Over");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 40));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        // Current Score Section
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());
        scorePanel.setBorder(BorderFactory.createTitledBorder("Your Score"));
        JLabel scoreLabel = new JLabel("Your Score: " + currentScore);
        scoreLabel.setFont(new Font(scoreLabel.getFont().getName(), Font.PLAIN, 24));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scorePanel.add(scoreLabel, BorderLayout.CENTER);
        mainPanel.add(scorePanel);

        // Horizontal Separator
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        // High Score Section
        JPanel highScorePanel = new JPanel();
        highScorePanel.setLayout(new BoxLayout(highScorePanel, BoxLayout.Y_AXIS));
        highScorePanel.setBorder(BorderFactory.createTitledBorder("High Score"));

        //should display 3 scores only
        for (int i = 0; i < Math.min(3, highScores.size()); i++) {
            JLabel highScoreLabel = new JLabel((i + 1) + ": " + highScores.get(i));
            highScoreLabel.setFont(new Font(highScoreLabel.getFont().getName(), Font.PLAIN, 16));
            highScorePanel.add(highScoreLabel);
            highScorePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        mainPanel.add(highScorePanel);

        // Horizontal Separator
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton exitButton = new JButton("Exit");
        JButton replayButton = new JButton("Replay");

        //adding the buttons to the panel
        buttonPanel.add(exitButton);
        buttonPanel.add(replayButton);
        mainPanel.add(buttonPanel);

        add(mainPanel);
        pack();

        // Center the window on the screen
        setLocationRelativeTo(null);
        setVisible(true);

        //Exit button action
        exitButton.addActionListener(e -> System.exit(0));

        //Replay button action
        replayButton.addActionListener(e ->{
            game.replay();
            dispose();
        });
    }
}
