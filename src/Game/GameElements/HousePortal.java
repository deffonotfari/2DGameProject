package Game.GameElements;

//imports
import Game.GameLevels.*;
import Game.Player;
import city.cs.engine.*;

/**Represents a portal in the game world, enabling the user to move to the next level.
 * Implements CollisionListener to detect player proximity and trigger level progression.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class HousePortal extends StaticBody implements SensorListener{
    /**Shape of the portal*/
    private static final Shape shape = new PolygonShape(3.09f,-3.11f, 3.09f,-0.07f, -2.88f,-0.14f, -2.85f,-3.18f, 3.06f,-3.23f);

    /**References to GameLevel class*/
    private final GameLevel level;

    /**
     * Constructs a HousePortal instance in the specified game world.
     * @param level The game level in which the portal exists.
     */
    public HousePortal(GameLevel level) {
        super(level, shape);
        addImage(new BodyImage("data/elements/housePortal.png", 7f));
        this.level = level;
    }

    /**
     * This method is called when the sensor begins to detect contact with another body.
     * @param e The SensorEvent containing information about the contact event
     */
    @Override
    public void beginContact(SensorEvent e) {
        // Check if the contact body is a player and if the level is complete
        if(e.getContactBody() instanceof Player && level.isComplete()){
            level.getGame().goToNextLevel();
        }
    }

    /**
     * This method is called when the sensor ends contact with another body.
     * @param e The SensorEvent containing information about the contact event
     */
    @Override
    public void endContact(SensorEvent e) {
        // No action needed when contact ends
    }
}


