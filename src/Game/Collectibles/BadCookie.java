package Game.Collectibles;

//imports
import city.cs.engine.*;


/**
 * Represents a bad cookie collectible item in the game.
 * Extends the Cookie class.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class BadCookie extends Cookie{

    /** The image representing the Bad Cookie collectible. */
    private static final BodyImage image = new BodyImage("data/collectibles/badCookie.png",2f);

    /**
     * Constructs a BadCookie object.
     *
     * @param world The world in which the BadCookie object will be created.
     */
    public BadCookie(World world){
        super(world, image);
    }
}
