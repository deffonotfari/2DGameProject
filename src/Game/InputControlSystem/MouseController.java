package Game.InputControlSystem;

//imports
import Game.SceneManagement.GameView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Handles mouse events for interacting with the game world and view.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class MouseController implements MouseListener {
    /**References to the game view class*/
    private final GameView view;

    /**
     * Constructs a MouseController with references to the game world and view.
     * @param newView The game view.
     */
    public MouseController(GameView newView) {
        // References to the game world and view
        view = newView;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     * This method is not used in this implementation.
     * @param e The MouseEvent.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // No action on mouse release
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     * If the initial image is displayed, it hides the initial image on mouse press.
     * @param e The MouseEvent.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // Hide the initial image if displayed
        e.getPoint();
        if (view.isDisplayInfo()) {
            view.hideInitialImage();
        }
    }

    /**
     * Invoked when a mouse button has been released on a component.
     * This method is not used in this implementation.
     * @param e The MouseEvent.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // No action on mouse release
    }

    /**
     * Invoked when the mouse enters a component.
     * This method is not used in this implementation.
     * @param e The MouseEvent.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // No action on mouse enter
    }

    /**
     * Invoked when the mouse exits a component.
     * This method is not used in this implementation.
     * @param e The MouseEvent.
     */
    public void mouseExited(MouseEvent e) {
        // No action on mouse exit
    }
}
