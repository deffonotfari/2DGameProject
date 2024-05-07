package Game.SceneManagement;

// Imports
import Game.Player;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

/**
 * The Tracker class tracks the player's position in the game world and updates the view accordingly.
 * Implements StepListener to respond to step events.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Tracker implements StepListener {
    /** References to the game view that is going to be updated. */
    public GameView view;

    /** The Player whose position will be tracked. */
    private final Player player;

    /**
     * Constructs a Tracker object with the specified GameView and Player references.
     * The Tracker is responsible for updating the GameView based on the Player's position in the game world.
     * @param view   The GameView to be updated.
     * @param player The Player whose position will be tracked.
     */
    public Tracker(GameView view, Player player) {
        this.view = view;
        this.player = player;
    }


    /**
     * Handles pre-step events.
     * @param e The StepEvent object representing the step event.
     */
    public void preStep(StepEvent e) {
        // No action before the step
    }

    /**
     * Handles post-step events.
     * @param e The StepEvent object representing the step event.
     */
    public void postStep(StepEvent e) {
        float x = player.getPosition().x;
        float y = player.getPosition().y + 11f;

        //Update the view based on the player's position
        view.setView(new Vec2(x, y), 19);
    }
}
