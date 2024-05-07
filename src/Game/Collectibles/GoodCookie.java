package Game.Collectibles;

//import
import city.cs.engine.BodyImage;
import city.cs.engine.World;

/**
 * Represents a GoodCookie, a subclass of Cookie.
 * Inherits attributes and methods from Cookie class through Inheritance.
 * Introduces an additional image attribute for appearance.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class GoodCookie extends Cookie {
    /** Image attribute for the collectible: "Good Cookie" */
    private static final BodyImage image = new BodyImage("data/collectibles/goodCookie.png", 2f);

    /**
     * Constructs a GoodCookie instance in the specified world with the assigned image.
     *
     * @param world The game world in which the GoodCookie exists.
     */
    public GoodCookie(World world) {
        super(world, image);
    }
}
