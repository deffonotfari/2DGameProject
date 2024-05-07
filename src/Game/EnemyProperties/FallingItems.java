package Game.EnemyProperties;

//imports
import city.cs.engine.*;

/**
 * Represents an item that falls from the sky in the game world.
 * Inherits from Walker and automatically falls at a specified speed.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class FallingItems extends Walker {
    /** The shape of the falling item. */
    private static final Shape shape = new BoxShape(0.5f, 0.2f);

    /** The image representing the falling item. */
    private final BodyImage image = new BodyImage("data/enemy/fallingObject.png",3);

    /** The speed at which the item falls. */
    private int speed = 12;


    /**
     * Sets the falling speed of the item.
     *
     * @param speed The speed at which the item falls.
     */

    public void setSpeed(int speed){
        this.speed = speed;
    }


    /**
     * Constructs a FallingItems instance in the specified game world.
     *
     * @param world The game world in which the item exists.
     */
    public FallingItems(World world) {
        super(world, shape);
        addImage(image);

        //adding a step listener to make the item fall at a certain speed
        world.addStepListener(new StepListener() {
            @Override
            public void preStep(StepEvent stepEvent) {
                // No action needed before each step
            }

            @Override
            public void postStep(StepEvent stepEvent) {
                //Make the item jump downwards at the specified speed after each step
                jump(speed);
            }
        });
    }
}
