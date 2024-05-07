package Game.EnemyProperties;

//imports
import Game.Player;
import city.cs.engine.*;
import javax.swing.*;


/**
 * Represents floor spikes in the game world.
 * Extends StaticBody and implements SensorListener.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class FloorSpikes extends StaticBody implements SensorListener {
    /** The shape of the floor spikes. */
    private final static Shape shape = new BoxShape(1f, 0.5f);

    /** The player character in the game. */
    private final Player player;


    /**
     * Constructs floor spikes in the game world.
     * @param world The world in which the floor spikes exist.
     * @param player The player character in the game.
     */
    public FloorSpikes(World world, Player player) {
        super(world);
        this.player = player;

        //adding images
        addImage(new BodyImage("data/elements/floorSpikes.png",1f));

        // Adding sensors
        Sensor sensor = new Sensor(this, shape);
        sensor.addSensorListener(this);
    }

    /**
     * Handles the beginning of contact with the floor spikes.
     * @param sensorEvent Information about the contact event.
     */
    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if (sensorEvent.getContactBody() instanceof Player) {
            // Reduce player's remaining lives
            player.setRemainingLives(player.getRemainingLives()-1);

            //handles player death if lives run out
            if (player.getRemainingLives() <= 0) {
                player.getPosition();
                player.getPlayerDead();

                // Destroy dead player after a delay
                Timer timer = new Timer(800, e -> player.getPlayerDead().destroy());
                timer.start();
            }
        }
    }

    /**
     * Handles the end of contact with the floor spikes.
     * @param sensorEvent Information about the contact event.
     */
    @Override
    public void endContact(SensorEvent sensorEvent) {
        //No action needed for end of contact
    }

}

