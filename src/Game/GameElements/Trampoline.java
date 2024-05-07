package Game.GameElements;

//imports
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**Represents a trampoline in the game world.
 * Inherits from the StaticBody class, indicating a fixed object.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 * */
public class Trampoline extends StaticBody {
    /** The shape of the trampoline mat. */
    private final PolygonShape matShape = new BoxShape(2f, 0.5f);

    /** The shape of the trampoline legs. */
    private final PolygonShape legShape1 = new PolygonShape(
            -1.49f, 0.0f,
            -1.03f, 0.01f,
            -1.0f, -0.01f,
            -1.03f, -0.93f,
            -1.5f, -0.96f,
            -1.49f, 0.0f
    );
    private final PolygonShape legShape2 = new PolygonShape(
            1.0f, -0.02f,
            1.01f, -0.95f,
            1.45f, -0.95f,
            1.45f, -0.02f,
            1.0f, -0.02f
    );

    /** This determines how bouncy the trampoline will be. */
    private float restitution;

    /**
     * Constructs a Trampoline instance in the specified world with a given position and restitution.
     * @param world       The game world in which the trampoline exists.
     * @param x           The x-coordinate of the trampoline.
     * @param y           The y-coordinate of the trampoline.
     * @param restitution The restitution (bounciness) of the trampoline.
     */
    public Trampoline(World world, float x, float y, float restitution) {
        super(world);
        this.restitution = restitution;

        // Creating fixtures for trampoline parts
        SolidFixture mat = new SolidFixture(this, matShape);
        SolidFixture leg1 = new SolidFixture(this, legShape1);
        SolidFixture leg2 = new SolidFixture(this, legShape2);

        // Setting the bounciness for the mat
        mat.setRestitution(restitution);

        // Adding trampoline image and setting its position
        this.addImage(new BodyImage("data/elements/trampoline.png", 3f));
        this.setPosition(new Vec2(x, y));
    }


    /**
     * Constructs a Trampoline instance in the specified world with default properties.
     * @param world The game world in which the trampoline exists.
     */
    public Trampoline(World world) {
        super(world);

        // Creating fixtures for trampoline parts
        SolidFixture mat = new SolidFixture(this, matShape);
        SolidFixture leg1 = new SolidFixture(this, legShape1);
        SolidFixture leg2 = new SolidFixture(this, legShape2);

        mat.setRestitution(getRestitution()); // Setting the bounciness for the mat

        // Adding trampoline image and setting its position
        this.addImage(new BodyImage("data/elements/trampoline.png", 3f));
    }


    /**
     * Gets the restitution of the trampoline.
     * @return The restitution of the trampoline.
     */
    public float getRestitution() {
        return restitution;
    }
}


