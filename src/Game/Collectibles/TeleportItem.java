package Game.Collectibles;

//imports
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Represents a teleportation item in the game world.
 * When collected by the player, it moves the player to a different location.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class TeleportItem extends StaticBody{
    /** The x-coordinate where the player will be teleported. */
    private float teleportToX;

    /** The y-coordinate where the player will be teleported. */
    private float teleportToY;

    /** The shape of the teleport item. */
    private static final BoxShape shape = new BoxShape(0.5f, 0.5f);

    /** The x-coordinate and y-coordinate of the teleport item's position. */
    private final float x, y;


    /**
     * Constructs a TeleportItem instance with specified teleportation coordinates and position.
     *
     * @param w             The game world in which the teleport item exists.
     * @param teleportToX   The x-coordinate where the player will be teleported.
     * @param teleportToY   The y-coordinate where the player will be teleported.
     * @param x             The x-coordinate of the teleport item's position.
     * @param y             The y-coordinate of the teleport item's position.
     */
    public TeleportItem(World w, float teleportToX, float teleportToY, float x, float y) {
        super(w, shape);
        addImage(new BodyImage("data/collectibles/teleportItem.gif", 2));

        //set the position
        this.x = x;
        this.y = y;
        setPosition(new Vec2(x, y));


        this.teleportToX = teleportToX;
        this.teleportToY = teleportToY;
    }


    /**
     * Constructs a TeleportItem instance with default teleportation coordinates and position.
     *
     * @param w The game world in which the teleport item exists.
     */
    public TeleportItem(World w) {
        super(w, shape);
        addImage(new BodyImage("data/collectibles/teleportItem.gif", 2));

        // Set the position to default (0, 0)
        this.x = 0;
        this.y = 0;
    }


    /**
     * Returns the x-coordinate where the player will be teleported.
     * @return The teleportation x-coordinate.
     */
    public float getTeleportToX() {
        return teleportToX;
    }


    /**
     * Returns the y-coordinate where the player will be teleported.
     * @return The teleportation y-coordinate.
     */
    public float getTeleportToY(){
        return teleportToY;
    }


    /**
     * Sets the coordinates to which the player will be teleported.
     * @param teleportX The x-coordinate where the player will be teleported.
     * @param teleportY The y-coordinate where the player will be teleported.
     */
    public void teleportTo(float teleportX, float teleportY) {
        this.teleportToX = teleportX;
        this.teleportToY = teleportY;
    }
}
