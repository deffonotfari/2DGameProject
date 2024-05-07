package Game.GameElements;

//imports
import city.cs.engine.World;

import java.awt.*;

/**Represents walls in the game world, acting as platforms.
 * Inherits from the Platforms class.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 * */
public class Walls extends Platforms {
    /**Constructs Walls instances in the specified world with specific dimensions, position, and color.
     * @param world  The game world in which the walls exist.
     * @param width  The width of the walls.
     * @param height The height of the walls.
     * @param x      The x-coordinate of the walls.
     * @param y      The y-coordinate of the walls.
     * @param color  The color of the walls in hexadecimal format.*/
    public Walls(World world, float width, float height, float x, float y, String color) {
        super(world, width, height, x, y);

        //setting the colour of the walls
        setFillColor(Color.black);
    }
}

