package Game.CollisionEvents;

//imports
import Game.GameElements.HousePortal;
import Game.Game;
import Game.GameLevels.GameLevel;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 * Manages collision events involving a house portal.
 * Implements the CollisionListener interface to respond to collision events.
 *
 * This class checks if the player collides with a house portal and
 * if the current level is complete, it triggers the transition to the next level.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 2024-04-17
 */
public class PortalEncounter implements CollisionListener {
    /** Reference to the current game level. */
    private GameLevel level;

    /** Reference to the game instance. */
    private Game g;


    /**
     * Constructs a PortalEncounter instance with references to the current game level and the game instance.
     *
     * @param level The current game level.
     * @param game The game instance.
     */
    public PortalEncounter(GameLevel level, Game game){
        this.level = level;
        g = game;
    }


    /**
     * Handles collision events involving the player and a house portal.
     *
     * @param e CollisionEvent containing information about the collision.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof HousePortal){
            if (level.isComplete()){
                g.goToNextLevel();
            }
        }
    }
}
