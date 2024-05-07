package Game.Weapons;

//imports
import Game.Collectibles.*;
import Game.EnemyProperties.Enemy;
import Game.GameElements.*;
import Game.EnemyProperties.DarkFigure;
import Game.GameLevels.GameLevel;
import Game.Player;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Weapons class represents a projectile in the game world.
 * It extends DynamicBody to enable dynamic movement and collision handling.
 * Implements CollisionListener to react to collision events with various game elements.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class  Projectile extends DynamicBody implements CollisionListener{
    /**This creates the shape of the projectile*/
    private static final CircleShape projectileShape = new CircleShape(0.8f);

    /**References to Player class*/
    private final Player player;

    /**
     * Constructs a Weapons instance in the specified game world at the given initial position.
     * @param world            The game world in which the projectile exists.
     * @param initialPosition  The initial position of the projectile.
     * @param player           Reference to the player for handling collision events.
     */
    public Projectile(World world, Vec2 initialPosition, Player player) {
        super(world, projectileShape);
        this.player = player;
        this.setPosition(initialPosition);
        addImage(new BodyImage("data/projectiles/projectile.png",0.8f));
    }

    /**
     * Handles collisions with various game elements.
     * Reacts to events when the projectile collides with different objects in the game world.
     * @param e The collision event representing the collision between the projectile and another object.
     */

    @Override
    public void collide(CollisionEvent e) {
        //Handles projectile hits with
         if(e.getOtherBody() instanceof HousePortal
                || e.getOtherBody() instanceof Walls
                || e.getOtherBody() instanceof Trampoline
                || e.getOtherBody() instanceof Lift
                || e.getOtherBody() instanceof GoodCookie
                ||e.getOtherBody() instanceof Ground
                ||e.getOtherBody() instanceof Coins
                ||e.getOtherBody() instanceof Platforms
         ) {
            destroy(); //then it should destroy itself
        }


        //Handles collision with the badCookie collectible
         if(e.getOtherBody() instanceof BadCookie) {
            //getting the position of BadCookie before it is destroyed
            Vec2 badCookiePosition = e.getOtherBody().getPosition();

            //destroying the bad cookie
            e.getOtherBody().destroy();

            //CookieSpawns a GoodCookie in the position of destroyed BadCookie
            GoodCookie goodCookie = new GoodCookie(getWorld());
            goodCookie.setPosition(badCookiePosition);

             //Destroy the projectile
            destroy();
        }


         // Handles collision with an enemy
         else if(e.getOtherBody() instanceof Enemy) {
             Enemy enemy = (Enemy) e.getOtherBody();
             enemy.incrementHitCount();

             //If the enemy has been hit 3 times, award points and destroy it
             if (enemy.getHitCount() == 3) {
                 player.setPoint(player.getPoints()+300);
                 player.setTicketCount(player.getTicketCount()+1);

                 //Stop enemy movement
                 enemy.stopWalking();

                 //show explosion animation
                 enemy.removeAllImages();
                 enemy.addImage(new BodyImage("data/explosions/explosion2.gif", 6f));

                 //destroy the enemy after a delay
                 Timer timer = new Timer();
                 timer.schedule(new TimerTask() {
                     @Override
                     public void run() {
                         enemy.destroy();
                     }
                 }, 550);
             }

             // If the enemy hasn't been hit 3 times yet, display hit animation based on direction
             else{
                 if (enemy.isDirectionLeft()) {
                     enemy.removeAllImages();
                     enemy.addImage(new BodyImage("data/enemy/enemyBeingHit.gif", 4f));
                 } else {
                     enemy.removeAllImages();
                     enemy.addImage(new BodyImage("data/enemy/enemyBeingHit.gif", 4f)).flipHorizontal();
                 }
             }

             //Destroy the projectile
             destroy();
         }

         // Handles collision with the dark figure
         else if (e.getOtherBody() instanceof DarkFigure) {
             DarkFigure darkFigure = (DarkFigure) e.getOtherBody();

             //Increment the hit count for the dark figure
             darkFigure.incrementHitCount();

             //If the dark figure has been hit 4 times, award points and destroy it
             if (darkFigure.getHitCount() == 4) {
                 player.setPoint(player.getPoints()+400);
                 player.setTicketCount(player.getTicketCount()+1);

                 //Store the dark figure's position for potion placement
                 Vec2 darkFigurePosition = darkFigure.getPosition();

                 //Stop dark figure movement
                 darkFigure.stopWalking();

                 //show explosion animation
                 darkFigure.removeAllImages();
                 darkFigure.addImage(new BodyImage("data/explosions/explosion2.gif", 9f));

                 //destroy it after a delay
                 Timer timer = new Timer();
                 timer.schedule(new TimerTask() {
                     @Override
                     public void run() {
                         darkFigure.destroy();

                         //create a health potion
                         HealthPotion potion = new HealthPotion((GameLevel) getWorld());
                         potion.setPosition(new Vec2(darkFigurePosition.x, darkFigurePosition.y));
                     }
                 },850);
             }

             //If the dark figure hasn't been hit 4 times yet, display hit animation based on direction
             else {
                 if (!darkFigure.isDirectionLeft()){
                     darkFigure.removeAllImages();
                     darkFigure.addImage(new BodyImage("data/enemy/defeated.gif",8f));
                 } else{
                     darkFigure.removeAllImages();
                     darkFigure.addImage(new BodyImage("data/enemy/defeated.gif",8f)).flipHorizontal();
                 }
             }

             //Destroy the projectile
             destroy();
         }
    }
}
