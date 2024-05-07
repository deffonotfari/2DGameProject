package Game.GameElements;

//imports
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * Represents static platforms in the game world with various shapes, sizes, positions, and colors.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Platforms extends StaticBody {

    /**
     * Constructor to create a platform with a specific color.
     *
     * @param world The game world in which the platform exists.
     * @param width The width of the platform.
     * @param height The height of the platform.
     * @param x The x-coordinate of the platform.
     * @param y The y-coordinate of the platform.
     */
    public Platforms(World world, float width, float height, float x, float y) {
        super(world, new BoxShape(width, height));
        setPosition(new Vec2(x, y));
        setAlwaysOutline(false);
    }


    /**
     * Adds an image to the platform.
     *
     * @param dataPath The path to the image file.
     * @param size     The size of the image.
     */
    public void photo(String dataPath, float size) {
        addImage(new BodyImage(dataPath, size));
    }
}
