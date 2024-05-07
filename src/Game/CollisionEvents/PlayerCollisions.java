package Game.CollisionEvents;

//imports
import Game.Collectibles.*;
import Game.EnemyProperties.Enemy;
import Game.EnemyProperties.FloorSpikes;
import Game.GameElements.MovingWall;
import Game.EnemyProperties.DarkFigure;
import Game.EnemyProperties.FallingItems;
import Game.Player;
import Game.SoundClips.SFX;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Manages collisions between the player character and game objects.
 * Implements the CollisionListener interface.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */

public class PlayerCollisions implements CollisionListener {
    /** Reference to the player character. */
    private final Player player;

    /** Sound effects manager. */
    private static final SFX effects = new SFX();


    /**
     * Constructs a PlayerCollisions instance with a reference to the player character.
     *
     * @param player The player character involved in collisions.
     */
    public PlayerCollisions(Player player) {
        this.player = player;
    }

    /**
     * Handles collision events involving the player picking up collectibles.
     *
     * @param collides CollisionEvent containing information about the collision.
     */
    @Override
    public void collide(CollisionEvent collides) {

        //Event: Player picking up a bad cookie
        if (collides.getOtherBody() instanceof BadCookie) {
            //lose a life
            player.setRemainingLives(player.getRemainingLives() - 1);

            //sound effects
            effects.getSoundEffect("cookie", 0.5f);

            //lose points
            player.setPoint(player.getPoints() - 50);

            collides.getOtherBody().destroy();
            player.playerDead();
        }

        //Event: Player colliding with Dark Figure or Enemy
        if (collides.getOtherBody() instanceof DarkFigure || collides.getOtherBody() instanceof Enemy) {
            //lose a life
            player.setRemainingLives(player.getRemainingLives()-1);

            //lose points
            player.setPoint(player.getPoints() - 150);

            player.playerDead();
        }

        //Event: Player falling on spikes
        if (collides.getOtherBody() instanceof FloorSpikes ) {
            //lose a life
            player.setRemainingLives(player.getRemainingLives() - 1);

            //lose points
            player.setPoint(player.getPoints() - 15);

            player.playerDead();
        }

        //Event: Player runs into the moving wall
        if (collides.getOtherBody() instanceof MovingWall) {
            //lose a life
            player.setRemainingLives(player.getRemainingLives()-1);

            //lose points
            player.setPoint(player.getPoints() - 120);

            player.playerDead();
        }

        //Event: Player colliding with falling items
        if (collides.getOtherBody() instanceof FallingItems) {
            effects.getSoundEffect("box", 0.6f);

            //lose all lives
            player.setRemainingLives(player.getRemainingLives()-6);

            //lose points
            player.setPoint(player.getPoints() - 200);


            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    player.playerDead();
                }
            }, 200);
        }

        //Event: Player picks up a good cookie
        else if (collides.getOtherBody() instanceof GoodCookie) {
            player.setPoint(player.getPoints() + 10);
            player.setCookieCount(player.getCookieCount()+1);
            collides.getOtherBody().destroy();

            //sound effects
            effects.getSoundEffect("cookie", 0.5f);
        }

        //Event: Player picks up a coin
        else if (collides.getOtherBody() instanceof Coins) {
            player.setPoint(player.getPoints() + 20);
            player.setCoinCount(player.getCoinCount()+1);
            collides.getOtherBody().destroy(); // Destroy the GoodCookie

            //sound effects
            effects.getSoundEffect("coin", 0.8f);
        }

        //Event: Player colliding with teleport item
        if (collides.getOtherBody() instanceof TeleportItem){
            float x = ((TeleportItem) collides.getOtherBody()).getTeleportToX();
            float y = ((TeleportItem) collides.getOtherBody()).getTeleportToY();
            collides.getReportingBody().setPosition(new Vec2(x, y));
        }
    }
}
