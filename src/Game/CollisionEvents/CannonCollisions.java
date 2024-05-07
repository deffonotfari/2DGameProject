package Game.CollisionEvents;

//imports

import Game.Collectibles.BadCookie;
import Game.Collectibles.GoodCookie;
import Game.GameElements.Platforms;
import Game.GameElements.Trampoline;
import Game.GameElements.Walls;
import Game.EnemyProperties.FloorSpikes;
import Game.Player;
import Game.Weapons.Projectile;
import Game.SoundClips.SFX;
import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import java.util.Timer;
import java.util.TimerTask;


/**
 * This class handles collision events involving cannons.
 * It implements the CollisionListener interface to listen for collision events.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class CannonCollisions implements CollisionListener {
    /** Reference to the Player object. */
    private final Player player;

    /** Sound effect for explosions. */
    private final SFX cannonExplosion = new SFX();

    /**
     * Constructs a CannonCollisions instance with references to the Player object.
     *
     * @param player The Player object for life management.
     */
    public CannonCollisions(Player player) {
        this.player = player;
    }


    /**
     * Handles collision events involving cannons and other objects.
     *
     * @param e CollisionEvent containing information about the collision.
     */
    @Override
    public void collide(CollisionEvent e) {
        //Handles collision with various objects
        if (e.getOtherBody() instanceof Walls
                || e.getOtherBody() instanceof FloorSpikes
                || e.getOtherBody() instanceof Trampoline
                || e.getOtherBody() instanceof BadCookie
                || e.getOtherBody() instanceof GoodCookie
                || e.getOtherBody() instanceof Projectile
                || e.getOtherBody() instanceof Player
                || e.getOtherBody() instanceof Platforms) {

            //Implementing an explosion effect and destroying the object
            e.getReportingBody().removeAllImages();
            e.getReportingBody().addImage(new BodyImage("data/explosions/explosion.gif", 5f));
            cannonExplosion.getSoundEffect("cannonExplosion", 0.3f);

            //Schedule destruction of the reporting body after a delay
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    e.getReportingBody().destroy();

                    //If collided with Player, decrement life
                    if (e.getOtherBody() instanceof Player) {
                        player.setRemainingLives(player.getRemainingLives()-1);

                        //if all lives are lost, handle player death
                        player.playerDead();
                    }
                }
            }, 530);
        }
    }
}

