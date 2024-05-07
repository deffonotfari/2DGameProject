package Game;

// Imports
import Game.SoundClips.SFX;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;

/**
 * Represents the player character in the game.
 * Extends the Walker class to provide movement capabilities.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Player extends Walker {
    /**The shape representing the player's physical body.*/
    private static final Shape studentShape = new PolygonShape(
            0.12f, 1.94f,
            1.67f, 0.39f,
            0.71f, -1.86f,
            -0.55f, -1.84f,
            -1.32f, 0.45f,
            -1.05f, 1.75f,
            0.07f, 1.94f
    );

    /** Represents the direction the player is facing. */
    public String direction;

    /** The number of tickets collected by the player. */
    private int ticketCount;

    /** The current score of the player. */
    private int point;

    /** The number of cookies eaten by the player. */
    private int cookieCount;

    /** The remaining lives of the player. */
    private int remainingLives;

    /** The number of coins collected by the player. */
    private int coinCount;

    /** The sound effects manager for the player. */
    private final SFX effect = new SFX();

    /**
     * Constructs a Player instance in the specified game world.
     * Initializes the player's appearance, attributes, and status.
     * @param world The game world in which the player exists.
     */
    public Player(World world) {
        super(world, studentShape);
        addImage(new BodyImage("data/playerMovement/normalPosition.png", 4f));
        point = 0;
        remainingLives = 6;
        cookieCount = 0;
        coinCount = 0;
        ticketCount = 0;
        direction = "left";
    }


    /**
     * Retrieves the current score of the player.
     * @return The current score of the player.
     */
    public int getPoints() {
        return point;
    }


    /**
     * Sets the player's score to a specific value.
     * @param point The score to set for the player.
     */
    public void setPoint(int point) {
        this.point = point;
    }


    /**
     * Retrieves the number of cookies the player has eaten.
     * @return The number of cookies the player has eaten.
     */
    public int getCookieCount(){
        return cookieCount;
    }


    /**
     * Sets the number of cookies the player has eaten.
     * @param cookieCount The number of cookies to set for the player.
     */
    public void setCookieCount(int cookieCount){
        this.cookieCount = cookieCount;
    }


    /**
     * Retrieves the number of coins the player has collected.
     * @return The number of coins collected by the player.
     */
    public int getCoinCount() {
        return coinCount;
    }


    /**
     * Sets the number of coins collected by the player.
     * @param count The number of coins to set for the player.
     */
    public void setCoinCount(int count){
        this.coinCount = count;
    }


    /**
     * Retrieves the number of tickets the player has collected.
     * @return The number of tickets collected by the player.
     */
    public int getTicketCount() {
        return ticketCount;
    }


    /**
     * Sets the number of tickets collected by the player.
     * @param ticketCount The number of tickets to set for the player.
     */
    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }


    /**
     * Retrieves the remaining lives of the player.
     * @return The remaining lives of the player.
     */
    public int getRemainingLives() {
        return remainingLives;
    }


    /**
     * Sets the remaining lives of the player.
     * @param remainingLives The number of remaining lives to set for the player.
     */
    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }



    /**
     * Creates a representation of the player's dead state and returns it.
     * @return A StaticBody representing the dead player.
     */
    public StaticBody getPlayerDead(){
        destroy();
        Shape death = new BoxShape(4,3);
        StaticBody deadPlayer = new StaticBody(getWorld(), death);
        deadPlayer.setPosition(new Vec2(getPosition()));
        deadPlayer.addImage(new BodyImage("data/playerMovement/skull.png",2f));
        effect.getSoundEffect("death", 1);

        return deadPlayer;
    }


    /**
     * Handles the actions when the player is dead.
     * Destroys the player instance, creates a dead player representation,
     * and exits the game after a delay.
     */
    public void playerDead(){
        if (getRemainingLives() <= 0) {
            getPosition();

            getPlayerDead();

            Timer timer = new javax.swing.Timer(450, e -> {
                getPlayerDead().destroy();

                System.exit(0);

            });
            timer.start();
        }
    }


    /**
     * Retrieves the direction the player is facing.
     * @return The direction the player is facing.
     */
    public String getDirection() {
        return direction;
    }


    /**
     * Sets the direction the player is facing.
     * @param direction The direction the player is facing.
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

}

