package Game.Collectibles;

import city.cs.engine.*;


/**
 * Parent class of BadCookie and GoodCookie.
 * Contains basic attributes shared by both collectibles
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Cookie extends DynamicBody {

   /** The common shape for both child classes. */
    private static final Shape cookieShape = new CircleShape(1);

    /** The image representing the appearance of the collectible. */
    private BodyImage image;

    /**
     * Constructs a Cookie instance in the specified world with the assigned image.
     *
     * @param world The game world in which the Cookie exists.
     * @param image The image representing the appearance of the collectible.
     */
    public Cookie(World world, BodyImage image){
        super(world, cookieShape);
        addImage(image);
    }

}
