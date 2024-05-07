package Game.EnemyProperties;

//imports
import Game.Player;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;


/**
 * Represents a dark figure enemy in the game.
 * Extends the Walker class and implements the StepListener interface.
 * This class handles collisions involving the player character and other game objects.
 * It updates the movement and behavior of the dark figure enemy based on the player's actions.
 *
 * @author Fariha Alam, alamfariha@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class DarkFigure extends Walker implements StepListener {
    /** Reference to the player character. */
    private final Player player;

    /** Shape of the dark figure. */
    private static final Shape shape = new BoxShape(2, 3);

    /** Speed of the dark figure. */
    private final int SPEED = 3;

    /** Boolean flag for movement direction. */
    private boolean directionLeft;

    /** Counter for the number of hits received by the dark figure. */
    private int hitCount;

    /** Enum for defining states of the dark figure. */
    private enum State {
        IDLE, CHASE
    }

    /** Current state of the dark figure. */
    private State state;


    /**
     * Increments the hit count of the dark figure.
     */
    public void incrementHitCount() {
        hitCount++;
    }


    /**
     * Retrieves the hit count of the dark figure.
     *
     * @return The hit count.
     */
    public int getHitCount() {
        return hitCount;
    }


    /**
     * Checks if the dark figure is facing left.
     *
     * @return True if facing left, false otherwise.
     */
    public boolean isDirectionLeft() {
        return directionLeft;
    }


    /**
     * Constructs a DarkFigure instance.
     *
     * @param world The world in which the dark figure exists.
     * @param player The player character in the game.
     */
    public DarkFigure(World world, Player player) {
        super(world, shape);
        this.player = player;
        world.addStepListener(this);
        addImage(new BodyImage("data/enemy/darkFigureLeft.gif", 7f));

        state = State.IDLE; //Start in the idle state
        startWalking(SPEED); //Start moving
    }


    /**
     * Updates the dark figure's state to idle and potentially transition to chasing the player.
     */
    private void updateIdleState() {
        if (player != null) {
            // Get the position of the player
            Vec2 playerPosition = player.getPosition();

            // Calculate the direction to move towards the player
            float directionX = (playerPosition.x - getPosition().x) + 4;

            // Set the direction of movement based on the player's position
            if (directionX > 0) {
                // Move right towards the player
                startWalking(SPEED);
                removeAllImages();
                addImage(new BodyImage("data/enemy/darkFigureRight.gif", 8f)); // Moving right
                directionLeft = false;
                state = State.CHASE; // Transition to CHASE state
            } else if (directionX < 0) {
                // Move left towards the player
                startWalking(-SPEED + 2);
                removeAllImages();
                addImage(new BodyImage("data/enemy/darkFigureLeft.gif", 8f)); // Moving left
                directionLeft = true;
                state = State.CHASE; // Transition to CHASE state
            }
        }
    }


    /**
     * Updates the dark figure's state to chase the player.
     */
    private void updateChaseState() {
        if (player != null) {
            // Get the position of the player
            Vec2 playerPosition = player.getPosition();

            // Calculate the direction to move towards the player
            float directionX = playerPosition.x - getPosition().x;

            // Set the direction of movement based on the player's position
            if (directionX > 0) {
                // Move right towards the player
                startWalking(SPEED);
                removeAllImages();
                addImage(new BodyImage("data/enemy/darkFigureRight.gif", 8f)); // Moving right
                directionLeft = false;
            } else if (directionX < 0) {
                // Move left towards the player
                startWalking(-SPEED + 2);
                removeAllImages();
                addImage(new BodyImage("data/enemy/darkFigureLeft.gif", 8f)); // Moving left
                directionLeft = true;
            }
        }

        // Check if the dark figure has been hit three times
        if (hitCount >= 3) {
            Vec2 directionAway = getPosition().sub(player.getPosition());

            if (directionAway.x > 0) {
                // Move right to run away
                startWalking(SPEED + 1);
                removeAllImages();
                addImage(new BodyImage("data/enemy/darkFigureRight.gif", 8f)); // Moving right
                directionLeft = false;
            } else {
                // Move left to run away
                startWalking(-SPEED - 1);
                removeAllImages();
                addImage(new BodyImage("data/enemy/darkFigureLeft.gif", 8f)); // Moving left
                directionLeft = true;
            }
        }
    }


    /**
     * Pre-step method called before each time step.
     * Updates the state of the dark figure based on the player's position.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        switch (state) {
            case IDLE:
                updateIdleState();
                break;
            case CHASE:
                updateChaseState();
                break;
            default:
                break;
        }
    }


    /**
     * Post-step method called after each time step.
     */
    @Override
    public void postStep(StepEvent stepEvent) {
        // No action needed in the post-step method
    }
}
