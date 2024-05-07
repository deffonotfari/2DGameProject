package Game.Collectibles;

//imports

import city.cs.engine.World;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


/**
 * Handles spawning of GoodCookie and BadCookie instances at random locations
 * in the game world over a specified time interval.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class CookieSpawns implements ActionListener {
    /** The game world where the cookies will be spawned. */
    private final World world;

    /** Timer to control the spawning process. */
    private final Timer timer;

    /** Counter for the number of cookies spawned. */
    private int cookiesSpawned;

    /** Constants for generating random coordinates. */
    private static final float minX = -18f, maxX = 18, minY = -13.5f, maxY = 15f;

    /** Random number generator. */
    private final Random random = new Random();


    /**
     * Constructs a CookieSpawns instance with a reference to the game world.
     *
     * @param world The game world in which the cookies will be spawned.
     */
    public CookieSpawns(World world) {
        this.world = world;
        this.timer = new Timer(5000, this); // Setting timer delay to 8 seconds
        this.cookiesSpawned = 0;
    }

    /**
     * Starts the timer to initiate the cookie spawning process.
     */
    public void startSpawning() {
        timer.start();
    }


    /**
     * Responds to time events by spawning cookies until the specified limit is reached.
     *
     * @param e ActionEvent triggering the time event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (cookiesSpawned < 6) {
            spawnRandomCookie();
            cookiesSpawned++;
        } else {
            // Stop the timer when the limit is reached
            timer.stop();
        }
    }


    /**
     * Generates random coordinates and creates an instance of GoodCookie.
     */
    private void spawnGoodCookie() {
        float x = random.nextFloat() * (maxX - minX) + minX;
        float y = random.nextFloat() * (maxY - minY) + minY;
        GoodCookie goodCookie = new GoodCookie(world);
        goodCookie.setPosition(new Vec2(x, y));
    }

    /**
     * Generates random coordinates and creates an instance of BadCookie.
     */
    private void spawnBadCookie() {
        float x = random.nextFloat() * (maxX - minX) + minX;
        float y = random.nextFloat() * (maxY - minY) + minY;
        BadCookie badCookie = new BadCookie(world);
        badCookie.setPosition(new Vec2(x, y));
    }


    /**
     * Randomly decides whether to spawn a GoodCookie or a BadCookie.
     */
    private void spawnRandomCookie() {
        int randomNumber = random.nextInt(2);

        if (randomNumber == 0) {
            spawnGoodCookie(); //generate good cookie
        } else {
            spawnBadCookie(); //generate bad cookie
        }
    }
}