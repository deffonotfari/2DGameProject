package Game.EnemyProperties;

//imports
import Game.GameLevels.GameLevel;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;


/**
 * Represents an enemy character in the game world.
 * Inherits from Walker and implements the StepListener interface for autonomous movement.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Enemy extends Walker implements StepListener {
    /** The shape of the enemy character. */
    private static final Shape enemyShape = new PolygonShape(
            2.05f, -1.81f,
            0.83f, 1.46f,
            -0.11f, 1.94f,
            -1.32f, 1.32f,
            -2.05f, 0.24f,
            -1.22f, -1.73f,
            2.04f, -1.81f);

    /** The image of the enemy when facing left. */
    private static final BodyImage imageGoingLeft = new BodyImage("data/enemy/enemyL.png", 4f);

    /** The image of the enemy when facing right. */
    private static final BodyImage imageGoingRight = new BodyImage("data/enemy/enemyR.png", 4f);

    /** The number of times the enemy has been hit. */
    private int hitCount = 0;


    /** The speed at which the enemy moves.*/
    private final int SPEED = 5;

    /**The left and right boundary for autonomous movement.*/
    private float left, right;

    /**The range within which the enemy can move autonomously.*/
    private int RANGE = 4;

    /**Indicates the direction the enemy is currently moving.
     * True if moving left, false if moving right.*/
    private boolean directionLeft;

    /**
     * Constructs an Enemy instance in the specified game world with a reference to the player.
     * Initializes the enemy's appearance, movement, and step listening.
     *
     * @param level The game world in which the enemy exists.
     */
    public Enemy(GameLevel level) {
        super(level, enemyShape);
        level.addStepListener(this);

        // Set the initial appearance of the enemy facing right
        addImage(imageGoingRight);

        // Start the enemy's autonomous movement
        startWalking(SPEED);
    }


    /**
     * Gets the range within which the enemy can move autonomously.
     * @return The range value.
     */
    public int getRANGE() {
        return RANGE;
    }


    /**
     * Sets the range within which the enemy can move autonomously.
     * @param RANGE The range value to set.
     */
    public void setRANGE(int RANGE) {
        this.RANGE = RANGE;
    }


    /**
     * Sets the position of the enemy and updates the left and right bounds for autonomous movement.
     * @param position The new position of the enemy.
     */
    @Override
    public void setPosition(Vec2 position) {
        super.setPosition(position);
        left = position.x - RANGE;
        right = position.x + RANGE;
    }


    /**
     * Sets the initial position of the enemy.
     * @param position The initial position to set.
     */
    public void setInitialPosition(Vec2 position){
        setPosition(position);
    }


    /**
     * Increments the hit count of the enemy.
     */
    public void incrementHitCount() {
        hitCount++;
    }


    /**
     * Gets the hit count of the enemy.
     * @return The hit count.
     */
    public int getHitCount() {
        return hitCount;
    }


    /**
     * Checks if the enemy is currently moving left.
     * @return True if moving left, false otherwise.
     */
    public boolean isDirectionLeft() {
        return directionLeft;
    }

    /**
     * Handles pre-step events, controlling the autonomous movements of the enemy.
     * @param stepEvent Information about the step event.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        // Direction: left
        if (getPosition().x > right) {
            startWalking(-SPEED);
            removeAllImages();
            addImage(imageGoingLeft); // Moving left
            directionLeft = true;
        }
        // Direction: right
        else if (getPosition().x < left) {
            startWalking(SPEED);
            removeAllImages();
            addImage(imageGoingRight); //moving right
            directionLeft = false;
        }
    }

    /**
     * Handles post-step events.
     *
     * @param stepEvent Information about the step event.
     */
    @Override
    public void postStep(StepEvent stepEvent) {
        // No action after step
    }
}
