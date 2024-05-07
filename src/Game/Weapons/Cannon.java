package Game.Weapons;

//imports
import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * Represents a Cannon projectile in the game world.
 * Extends DynamicBody to enable dynamic movement and collision handling.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Cannon extends DynamicBody {

    /**
     * Constructs a Cannon instance in the specified game world.
     * Initializes the cannon's appearance, sets its initial horizontal velocity,
     * and adjusts its gravity scale.
     *
     * @param world        The game world in which the cannon exists.
     * @param gravityScale The gravity scale applied to the cannon (0 for no gravity, 1 for normal gravity).
     */
    public Cannon(World world, float gravityScale) {
        super(world, new CircleShape(1.5f));
        addImage(new BodyImage("data/projectiles/cannon.png", 2));

        // Set the initial linear velocity for horizontal movement
        Vec2 initialVelocity = new Vec2(10f, 0f);
        setLinearVelocity(initialVelocity);

        //set the gravity scale of the cannon
        setGravityScale(gravityScale);
    }
}