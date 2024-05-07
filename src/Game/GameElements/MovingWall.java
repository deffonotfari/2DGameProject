package Game.GameElements;

import Game.Player;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;


/**
 * Represents a moving wall in the game world that opens when the player approaches.
 * Controlled by step events and sensor detection of the player.
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class MovingWall extends StaticBody implements SensorListener, StepListener {
    /** The shape of the moving wall. */
    private static final Shape shape = new BoxShape(0.5f, 4.5f);

    /** The speed at which the moving wall opens. */
    private static final float OPEN_SPEED = 0.02f;

    /** The speed at which the moving wall closes. */
    private static final float CLOSE_SPEED = 0.15f;

    /** The duration the moving wall remains open in milliseconds. */
    private static final long OPEN_TIME = 8500;

    /** Indicates whether the moving wall is currently open. */
    private boolean isOpen;

    /** Reference to the player instance. */
    private final Player player;

    /** The initial y-coordinate of the moving wall. */
    private final float initialY;

    /** The time when the moving wall was last opened. */
    private long lastTimeOpened;

    /** The current speed of the moving wall. */
    private float currentSpeed;


    /**
     * Constructs a MovingWall instance in the specified world with the given initial position.
     * @param w         The game world in which the moving wall exists.
     * @param player    The player instance.
     * @param initialX  The initial x-coordinate of the moving wall.
     * @param initialY  The initial y-coordinate of the moving wall.
     */
    public MovingWall(World w, Player player, float initialX, float initialY) {
        super(w, shape);

        this.initialY = initialY;
        this.player = player;

        BodyImage image = new BodyImage("data/elements/movingWall.png", 9f);
        addImage(image);

        setPosition(new Vec2(initialX, initialY));
        isOpen = false;
        lastTimeOpened = System.currentTimeMillis();
        currentSpeed = 0;

        // Create sensor
        Sensor sensor = new Sensor(this, new BoxShape(3f, 3f));
        sensor.addSensorListener(this);

        w.addStepListener(this);
    }


    /**
     * Handles pre-step events, controlling the vertical movement of the moving wall.
     * @param stepEvent The StepEvent containing information about the step.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        //Check if it's time to close the wall
        if (isOpen && System.currentTimeMillis() - lastTimeOpened > OPEN_TIME) {
            isOpen = false;
            currentSpeed = -CLOSE_SPEED;
        }

        //Move the wall vertically based on the current speed
        if (getPosition().y < initialY && currentSpeed < 0) {
            currentSpeed = 0;
        }
        setPosition(new Vec2(getPosition().x, getPosition().y + currentSpeed));
    }


    /**
     * Handles post-step events (unused in this class).
     * @param stepEvent The StepEvent containing information about the step.
     */
    @Override
    public void postStep(StepEvent stepEvent) {
        // No action after step
    }


    /**
     * Called when the sensor starts to detect contact with another body.
     * @param sensorEvent The SensorEvent containing information about the contact event.
     */
    @Override
    public void beginContact(SensorEvent sensorEvent) {
        // Check if the player has entered the sensor range
        if (sensorEvent.getContactBody() == player) {
            isOpen = true;
            lastTimeOpened = System.currentTimeMillis();
            currentSpeed = OPEN_SPEED;
        }
    }

    /**
     * Called when the sensor ends contact with another body (unused in this class).
     * @param sensorEvent The SensorEvent containing information about the contact event.
     */
    @Override
    public void endContact(SensorEvent sensorEvent) {
        // Not used
    }


    /**
     * Sets the state of whether the moving wall is currently open or not.
     * @param isOpen True if the moving wall is open, false otherwise.
     */
    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }


    /**
     * Sets the timestamp of when the moving wall was last opened.
     *
     * @param lastTimeOpened The timestamp of when the moving wall was last opened.
     */
    public void setLastTimeOpened(long lastTimeOpened) {
        this.lastTimeOpened = lastTimeOpened;
    }


    /**
     * Sets the current speed of the moving wall.
     *
     * @param currentSpeed The current speed of the moving wall.
     */
    public void setCurrentSpeed(float currentSpeed) {
        this.currentSpeed = currentSpeed;
    }


    /**
     * Retrieves the state of whether the moving wall is currently open or not.
     *
     * @return True if the moving wall is open, false otherwise.
     */
    public boolean isOpen() {
        return isOpen;
    }


    /**
     * Retrieves the timestamp of when the moving wall was last opened.
     *
     * @return The timestamp of when the moving wall was last opened.
     */
    public long getLastTimeOpened() {
        return lastTimeOpened;
    }


    /**
     * Retrieves the current speed of the moving wall.
     *
     * @return The current speed of the moving wall.
     */
    public float getCurrentSpeed() {
        return currentSpeed;
    }
}