package Game.SceneManagement;

//imports
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * GiveFocus class inherits MouseAdapter to provide focus to a specified GameView on mouse entry.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class GiveFocus extends MouseAdapter {
    /**Reference to the GameView to be focused*/
    private final GameView view;

    /**
     * Constructor for GiveFocus class.
     * @param view The GameView to be focused.
     */
    public GiveFocus(GameView view) {
        this.view = view;
    }


    /**
     * Overrides the mouseEntered method to request focus for the associated GameView.
     * @param e The MouseEvent.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        view.requestFocus();
    }
}

