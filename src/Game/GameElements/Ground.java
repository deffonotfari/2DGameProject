package Game.GameElements;

//imports
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import java.awt.*;

/** Represents the ground in the game world as a static body with a box shape.
 * Provides a solid surface for characters and objects to stand on.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 * */
public class Ground extends StaticBody {
    /**The shape of the ground.*/
    private static final BoxShape groundShape = new BoxShape(31, 1);

    /**The filepath for storing the location of the ground image.*/
    private String filePath;

    /**
     * Constructs a Ground instance in the specified game world with the given filepath.
     *
     * @param world     The game world in which the ground exists.
     * @param filePath  The filepath of the ground image.
     */
    public Ground(World world, String filePath) {
        super(world, groundShape);
        this.filePath = filePath;
        setFillColor(new Color(103, 66, 144));
        setBodyImage(filePath);
    }


    /**
     * Sets the body image of the ground to the specified filepath.
     * @param filePath  The filepath of the new ground image.
     */
    public void setBodyImage(String filePath) {
        removeAllImages();
        addImage(new BodyImage(filePath, 4f));
    }


    /**
     * Retrieves the filepath of the ground image.
     * @return The filepath of the ground image.
     */
    public String getFilePath() {
        return filePath;
    }


    /**
     * Sets the filepath of the ground image.
     * @param filePath The new filepath of the ground image.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
        setBodyImage(filePath);
    }
}
