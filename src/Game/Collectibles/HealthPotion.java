package Game.Collectibles;

//imports
import Game.GameLevels.GameLevel;
import Game.Player;
import Game.SoundClips.SFX;
import city.cs.engine.*;

/**
 * Represents a health potion collectible item in the game.
 * Extends the StaticBody class.
 * <p>
 * This potion restores the player's health when collected.
 * If the player's remaining lives are less than or equal to 5,
 * it increments the player's heart count and destroys itself.
 * <p>
 * It contains a sensor to detect collisions with the player.
 * Upon collision, it plays a sound effect and increments the player's heart count.
 * <p>
 * Inherits attributes and methods from the StaticBody class.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class HealthPotion extends StaticBody {

    /** Sound effect for potion effects. */
    private final SFX effects = new SFX();

    /** Shape of the health potion. */
    private static final Shape shape = new CircleShape(1f);

    /** Image representing the health potion. */
    private static final BodyImage image = new BodyImage("data/elements/restoreHeart.gif",2f);

    /**
     * Constructs a HealthPotion instance.
     *
     * @param level The current game level.
     */
    public HealthPotion(GameLevel level) {
        super(level);

        // Create ghostly fixture to enable collision detection without physical interaction
        new GhostlyFixture(this, shape);

        // Create a sensor to detect collision with the player
        Sensor sensor = new Sensor(this, shape);
        sensor.addSensorListener(new SensorListener() {
            @Override
            public void beginContact(SensorEvent e) {
                // Check if the contact body is the player
                if (e.getContactBody() instanceof Player) {

                    //If player's lives are ≤ to 5, restore health
                    if (level.getPlayer().getRemainingLives() <= 5) {
                        effects.getSoundEffect("heart", 0.95f);
                        level.getPlayer().setRemainingLives(level.getPlayer().getRemainingLives()+1);

                        //The cost of using a health potion should be negative 10 points
                        level.getPlayer().setPoint(level.getPlayer().getPoints() - 10);

                        destroy(); // Destroy the health potion after use
                    }
                }
            }

            @Override
            public void endContact(SensorEvent e) {
                // No action needed when the sensor ends contact
            }
        });


        // Add image to the health potion
        addImage(image);
    }
}
