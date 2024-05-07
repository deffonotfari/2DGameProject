package Game.GameElements;

//imports
import city.cs.engine.*;


/**This class represents a plant in a game world
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Plant extends StaticBody {
    /** The shape of the plant. */
    private final Shape shape = new BoxShape(8f, 4f);

    /**
     * Constructs a Plant instance in the specified world.
     * @param world The game world in which the plant exists.
     */
    public Plant(World world) {
        super(world);
        GhostlyFixture ghostlyFixture = new GhostlyFixture(this, shape);
        addImage(new BodyImage("data/elements/tree.png",10f));
    }
}
