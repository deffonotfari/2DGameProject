package Game.SceneManagement;

//imports
import Game.Player;
import city.cs.engine.UserView;
import city.cs.engine.World;
import javax.swing.*;
import java.awt.*;

/**
 * The GameView class extends UserView to provide a visual representation of the game elements.
 * It handles painting the background, foreground, player's score, remaining lives, and collectibles.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class GameView extends UserView {
    /**References to the Player class*/
    private Player player;

    /**This represents the background image*/
    private Image background;

    /**Flag to indicate if initial information box is displayed or not*/
    private boolean displayInfo = true;

    /**Flag to indicate if the game is paused*/
    private boolean isPause = false;

    /**Image for hearts, representing the remaining lives of the player*/
    private final Image fullHeart = new ImageIcon("data/lives/fullHeart.png").getImage();
    private final Image halfHeart = new ImageIcon("data/lives/halfEmptyHeart.png").getImage();
    private final Image emptyHeart = new ImageIcon("data/lives/emptyHeart.png").getImage();

    /**Images for display purposes, to count how many cookies have been collected*/
    private final Image cookie;

    /**Images for display purposes, to count how many coins have been collected*/
    private final Image coin;

    /**Images for display purposes, to count how many tickets have been collected*/
    private final Image ticket;

    /**Images for display purposes, to display the score*/
    private final Image score = new ImageIcon("data/DisplayStatus/score.png").getImage();

    /**Image for displaying initial information box*/
    private final Image info = new ImageIcon("data/popUpScreen.png").getImage();


    /**
     * Constructs a GameView with the specified World, Player, width, and height.
     * Initializes the player and loads the background image.
     *
     * @param w      The game world.
     * @param player The player character.
     * @param width  The width of the view.
     * @param height The height of the view.
     */
    public GameView(World w, Player player, int width, int height) {
        super(w, width, height);
        this.player = player;
        this.background = new ImageIcon("data/Background/background1.gif").getImage();
        this.cookie = new ImageIcon("data/DisplayStatus/cookie.png").getImage();
        this.coin = new ImageIcon("data/DisplayStatus/coinStatus.png").getImage();
        this.ticket = new ImageIcon("data/DisplayStatus/ticket.png").getImage();
    }


    /**
     * Checks if the initial information box is currently being displayed.
     *
     * @return True if the information box is displayed; false otherwise.
     */
    public boolean isDisplayInfo() {
        return displayInfo;
    }

    /**
     * Sets the player character.
     * @param player The player character.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }


    /**
     * Sets the pause status of the game.
     * @param isPause True to pause the game; false to resume.
     */
    public void setPause(boolean isPause){
        this.isPause = isPause;
    }


    /**
     * This method draws the player's score on the screen.
     * @param g The graphics context.
     */
    private void drawScoreSystem(Graphics2D g) {
        g.setFont(new Font("DialogInput", Font.BOLD, 32));
        g.setColor(Color.red);

        //Draw the score image at the specified position and size
        g.drawImage(score, 20, 20, 60, 40, this);

        //drawing the player's current points next to score image
        g.drawString(" " + player.getPoints(), 80, 50);
    }


    /**
     * This method draws the hearts, representing the remaining lives of the player,
     * in the foreground of the game view.
     * @param g The graphics context.
     */
    private void paintHearts(Graphics2D g) {
        //Define the dimensions and starting position for the hearts
        int heartWidth = 40;
        int heartHeight = 40;
        int startX = 20;
        int startY = 60;

        //Iterate through each of the player's lives
        for (int i = 0; i < 3; i++) {
            int heartX = startX + i * (heartWidth + 5);
            int heartY = startY;

            //Check if both halves of the current heart are empty
            if (i * 2 >= player.getRemainingLives()) {
                g.drawImage(emptyHeart, heartX, heartY, heartWidth, heartHeight, this);
            }

            //Check if the current heart represents a half-filled heart
            else if (i * 2 + 1 == player.getRemainingLives()) {
                g.drawImage(halfHeart, heartX, heartY, heartWidth, heartHeight, this);
            }

            //Draw full hearts
            else {
                g.drawImage(fullHeart, heartX, heartY, heartWidth, heartHeight, this);
            }
        }
    }


    /**
     * Paints the statistics for how many good cookies have been consumed.
     * @param g The graphics context.
     */
    public void paintCollectibles(Graphics2D g) {
        //Set the font and color for the text
        g.setFont(new Font("Times Roman", Font.BOLD, 32));
        g.setColor(Color.white);

        //Drawing the cookie count and its icon
        g.drawImage(cookie, 650, 20, 50, 50, this);
        g.drawString(" X" + player.getCookieCount(), 680, 60);

        //drawing coin count and its icon
        g.drawImage(coin, 650, 60, 50, 50, this);
        g.drawString(" X" + player.getCoinCount(), 680, 100);

        //drawing ticket count and its icon
        g.drawImage(ticket, 650, 100, 50, 50, this);
        g.drawString(" X" + player.getTicketCount(), 680, 140);
    }


    /**
     * Paints the initial information box on the screen.
     * @param g The graphics context.
     */
    public void paintInstructions(Graphics2D g) {
        g.drawImage(info, 50, 30, 700, 500, this);
    }


    /**
     * Paints the background of the view with the specified background image.
     * @param g The graphics context.
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, 800, 600, this);
    }


    /**
     * Paints the foreground of the view, including the player's score and remaining lives.
     * @param g The graphics context.
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        // Call the superclass method to paint the foreground
        super.paintForeground(g);

        //Check if the initial information box is currently being displayed
        if (displayInfo) {
            paintInstructions(g);
        }

        else {
            //Display the user's score on the screen
            drawScoreSystem(g);

            //Display the user's life statistics (i.e., how many lives it has remaining)
            paintHearts(g);

            //Display the collectible
            paintCollectibles(g);
        }

        //If the game is paused, display the pause image
        if(isPause){
            g.drawImage(new ImageIcon("data/Background/gamePaused.png").getImage(),0,0,800,600,this);
        }
    }

    /**
     * Set the displayInitialImage variable to false.
     * Call this method to remove the initial image from the view.
     */
    public void hideInitialImage() {
        displayInfo = false;
        repaint();
    }


    /**
     * Changes the background image based on the level number.
     * @param levelNo The level number.
     */
    public void changeBackground(int levelNo) {
        switch (levelNo) {
            case 2 -> background = new ImageIcon("data/Background/background2.png").getImage();
            case 3 -> background = new ImageIcon("data/Background/background3.gif").getImage();
            case 4 -> background = new ImageIcon("data/Background/background4.gif").getImage();
            case 1 -> background = new ImageIcon("data/Background/background1.gif").getImage();
            default -> throw new IllegalStateException("Unexpected value: " + levelNo);
        }
    }

}
