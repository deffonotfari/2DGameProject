package Game.GameElements;

//imports
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Represents a lift in the game world that moves vertically.
 * Implemented as a static body with a polygon shape and controlled by step events.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Lift extends StaticBody implements StepListener {
    /**Shape of the lift*/
    private static final PolygonShape liftShape = new PolygonShape(-2.04f, -0.01f, -2.03f, -1.14f, 2.02f, -1.14f, 2.0f, 0.01f, -2.01f, 0.0f);

    /**The initial position of the lift*/
    private final Vec2 startPosition;

   /** The top and bottom positions where the lift can move*/
    private float top, bottom, speed;

    /**Imahe of the lift*/
    private BodyImage liftImage = new BodyImage("data/elements/lift.png", 6f);


    /** Constructs a Lift instance in the specified world with the given initial position.
     * @param world The game world in which the lift exists.
     * @param x     The initial x-coordinate of the lift.
     * @param y     The initial y-coordinate of the lift.*/
    public Lift(World world, float x, float y) {
        super(world, liftShape);

        setPosition(new Vec2(x, y));
        addImage(liftImage);

        startPosition = this.getPosition();
        bottom = startPosition.y;
        top = startPosition.y + 10;
        speed = 0.08f;

        world.addStepListener(this);
    }


    /** Handles pre-step events, controlling the vertical movement of the lift.
     * @param stepEvent The StepEvent containing information about the step.*/
    @Override
    public void preStep(StepEvent stepEvent) {
        //Check if the lift reaches the bottom, then reset its position and change direction
        if (getPosition().y < bottom) {
            setPosition(startPosition);
            speed *= -1;
        }

        //Check if the lift reaches the top, then change direction
        if (getPosition().y > top) {
            speed *= -1;
        }

        //Move the lift vertically based on the current speed
        setPosition(new Vec2(getPosition().x, getPosition().y + speed));
    }


    /**Handles post-step events (unused in this class).
     * @param stepEvent The StepEvent containing information about the step.*/
    @Override
    public void postStep(StepEvent stepEvent) {
        // No action after step
    }
}
