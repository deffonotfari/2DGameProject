package Game.InputControlSystem;

//Imports
import Game.Game;
import Game.Player;
import Game.Weapons.Projectile;
import Game.SoundClips.SFX;
import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

/**
 * The PlayerController class implements the KeyListener interface to handle keyboard inputs
 * for controlling the player's movement and actions in the game.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class PlayerController implements KeyListener {
    /** The instance needed to control. */
    private Player player;

    /** The sound effects used in the game. */
    private final SFX effects = new SFX();

    /** The movement speed of the player. */
    private static final float speed = 4;

    /** The game object associated with the player. */
    private final Game game;

    /** The image representing the player shooting towards the right direction. */
    private final BodyImage shootingRight = new BodyImage("data/playerMovement/movingRightGun.png",4f);


    /**
     * Constructs a PlayerController with the specified player and game references.
     *
     * @param player The player object to control.
     * @param game   The game object associated with the player.
     */
    public PlayerController(Player player, Game game) {
        this.player = player;
        this.game = game;
    }


    /**
     * Updates the player object.
     * @param newPlayer The updated player object.
     */
    public void updateControls(Player newPlayer) {
        this.player = newPlayer;
    }


    /**
     * Unused: Handles the key typed event.
     * @param e The KeyEvent representing the key typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // No action on key type
    }


    /**
     * Handles the key press event.
     * @param e The KeyEvent representing the key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //Move character left
        if (code == KeyEvent.VK_LEFT) {
            player.setDirection("left");

            if (Objects.equals(player.getDirection(), "left")) {
                player.startWalking(-speed);
                player.removeAllImages();

                //sprite should face left
                player.addImage(new BodyImage("data/playerMovement/movingLeft.gif", 4f));
            }
        }

        //Move character right
        else if (code == KeyEvent.VK_RIGHT) {
            player.setDirection("right");

            if (Objects.equals(player.getDirection(), "right")) {
                player.startWalking(speed);
                player.removeAllImages();

                //sprite should face right
                player.addImage(new BodyImage("data/playerMovement/movingRight.gif",4f));
            }
        }

        //Make character jump
        else if (code == KeyEvent.VK_UP) {
            player.setLinearVelocity(new Vec2(0,12));
        }

        //Shoot while facing right
        else if (code == KeyEvent.VK_D){
            Vec2 projectilePosition = player.getPosition().add(new Vec2(2, 0));
            Projectile bullet = new Projectile(player.getWorld(), projectilePosition, player);
            effects.getSoundEffect("gunShot", 0.55f);

            //changing image of the player
            player.removeAllImages();
            player.addImage(shootingRight);

            //moves to the right
            bullet.setLinearVelocity(new Vec2(10f, 0));
            bullet.addCollisionListener(bullet);
        }

        //Shoot while facing Left
        else if (code == KeyEvent.VK_A){
            Vec2 projectilePosition = player.getPosition().add(new Vec2(-2, 0));
            Projectile bullet = new Projectile(player.getWorld(), projectilePosition, player);
            effects.getSoundEffect("gunShot", 0.14f);

            //changing image of the player
            player.removeAllImages();
            player.addImage(shootingRight).flipHorizontal();

            //moves to the left
            bullet.setLinearVelocity(new Vec2(-13, 0));

            //creating an object and adding it to the event source
            bullet.addCollisionListener(bullet);
        }

        //gets the menu out
        else if (code == KeyEvent.VK_SPACE){
            game.goToMainMenu();
            game.transitionToMenuFromLM();
        }
    }

    /**
     * Handles key release events.
     * @param e The KeyEvent representing the key release.
     */
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        //Stop character movement and set to normal position
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_A || code == KeyEvent.VK_D) {
            player.stopWalking();
            player.removeAllImages();
            player.addImage(new BodyImage("data/playerMovement/normalPosition.png",4f));
        }
    }
}