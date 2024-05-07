package Game.Collectibles;

import city.cs.engine.*;

/**
 * Represents a coin collectible item in the game.
 * Extends the StaticBody class.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Coins extends StaticBody {
    /** The shape of the coin. */
    private static final Shape coinShape = new CircleShape(0.3f);

    /** The image representing the coin collectible. */
    private static final BodyImage coin = new BodyImage("data/collectibles/coin.gif",3f);

    /**
     * Constructs a Coins object.
     *
     * @param w The world in which the Coins object will be created.
     */
    public Coins(World w) {
        super(w, coinShape);
        addImage(coin);
    }
}
