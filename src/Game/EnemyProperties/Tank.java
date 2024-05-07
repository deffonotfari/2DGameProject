package Game.EnemyProperties;

// imports
import Game.CollisionEvents.CannonCollisions;
import Game.Player;
import Game.Weapons.Cannon;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;

/**
 * Represents a tank enemy in the game world.
 * Extends Walker and implements StepListener for autonomous movement.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Tank extends Walker implements StepListener {

    // States enumeration
    private enum State {
        MOVING_LEFT,
        MOVING_RIGHT,
        SHOOTING
    }

    // Current state
    private State currentState;

    /**References to Player class.*/
    private final Player player;

    /**The game world in which the tank exists.*/
    private final World world;

    /**Shape of the tank*/
    private static final Shape shape = new BoxShape(2, 3);

    /**References to Cannon class: This is the item that is shot out of the tank*/
    private Cannon cannon;

    /**The interval between shots in milliseconds.*/
    private static final int SHOOT_INTERVAL = 10000; // 10 seconds

    /**The left and right bound for autonomous movement.*/

    private float left, right;

    /** Timer for shooting*/
    private Timer shootTimer;

    /**
     * Constructs a Tank instance in the specified game world with a reference to the player.
     * Initializes the tank's appearance, movement, and shooting capabilities.
     *
     * @param world The game world in which the tank exists.
     * @param player The player instance.
     */
    public Tank(World world, Player player) {
        super(world, shape);
        this.player = player;
        this.world = world;
        world.addStepListener(this);
        addImage(new BodyImage("data/enemy/tankRight.gif", 5f));

        // Start the tank's autonomous movement
        startMoving(State.MOVING_RIGHT);

        // Initialize and start a timer for shooting projectiles at regular intervals
        shootTimer = new Timer(SHOOT_INTERVAL, e -> {
            startMoving(currentState); // Resume movement after shooting
            shoot();
            shootTimer.restart(); // Restart the timer after each shot
        });
        shootTimer.setInitialDelay(SHOOT_INTERVAL); // Set initial delay
        shootTimer.start(); // Start the timer
    }

    /**
     * Stops the shooting timer.
     */
    public void stopTimer() {
        shootTimer.stop();
    }

    /**
     * Start moving in the specified direction based on the current state.
     */
    private void startMoving(State state) {
        currentState = state;
        switch (state) {
            case MOVING_LEFT:
                startWalking(-4);
                removeAllImages();
                addImage(new BodyImage("data/enemy/tankLeft.gif", 5f)); // Moving left
                break;
            case MOVING_RIGHT:
                startWalking(4);
                removeAllImages();
                addImage(new BodyImage("data/enemy/tankRight.gif", 5f)); // Moving right
                break;
            case SHOOTING:
                stopMoving();
                break;
        }
    }

    /**
     * Stop the tank's movement.
     */
    private void stopMoving() {
        stopWalking();
    }

    /**
     * Handles pre-step events, controlling the autonomous movements of the tank.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        if (currentState == State.MOVING_LEFT && getPosition().x <= left) {
            startMoving(State.MOVING_RIGHT);
        } else if (currentState == State.MOVING_RIGHT && getPosition().x >= right) {
            startMoving(State.MOVING_LEFT);
        }
    }

    /**
     * Sets the position of the tank and updates the left and right bounds for autonomous movement.
     */
    @Override
    public void setPosition(Vec2 position) {
        super.setPosition(position);

        left = position.x - 2;
        right = position.x + 2;
    }

    /**
     * Handles shooting logic for the tank.
     */
    private void shoot() {
        // Create a new Cannon instance if not already created
        cannon = new Cannon(world, 0);

        // Set the position of the cannon
        cannon.setPosition(getPosition());

        // Set the initial linear velocity for horizontal movement
        Vec2 velocity = new Vec2(currentState == State.MOVING_LEFT ? -10 : 10, 0);
        cannon.setLinearVelocity(velocity);

        // Create a new CannonCollisions instance and add it as a collision listener
        CannonCollisions collisions = new CannonCollisions(player);
        cannon.addCollisionListener(collisions);
    }

    /**
     * Handles post-step events, resetting the shoot timer delay.
     */
    @Override
    public void postStep(StepEvent stepEvent) {
        shootTimer.setDelay(SHOOT_INTERVAL);
    }
}

