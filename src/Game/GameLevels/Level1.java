package Game.GameLevels;

//Imports
import Game.GameElements.*;
import Game.Game;
import org.jbox2d.common.Vec2;

/**
 * Represents the first level of the game.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Level1  extends GameLevel {
    /**
     * Constructs Level1 by populating it
     * @param game The game instance associated with this level.
     */
    public Level1(Game game) {
        super(game);

        //creating the ground
        Ground ground = new Ground(this, "data/road/road1.png");
        ground.setPosition(new Vec2(0, -13.5f));

        //Creating platforms for the first level and rendering them with images
        Platforms largePlatform = new Platforms(this, 15f, 0.5f, 17, 3f);
        largePlatform.photo("data/Platforms/level1/largePlatform.png", 1f);

        Platforms portalPlatform = new Platforms(this, 12f, 0.5f, -20, 5f);
        portalPlatform.photo("data/Platforms/level1/portalPlatform.png", 1f);

        Platforms nextToLiftPlatform = new Platforms(this, 4.5f, 0.5f, 3f, -5f);
        nextToLiftPlatform.photo("data/Platforms/level1/platform.png", 1f);

        //creating a trampoline,
        Trampoline trampoline = new Trampoline(this, 10f, -12f, 4);

        //creating a lift
        Lift lift = new Lift(this, -4.5f, -5);
        lift.setPosition(new Vec2( -8f, -15f));

        //creating plant
        Plant plant = new Plant(this);
        plant.setPosition(new Vec2(20, 8f));
    }


    /**
     * Populates the level with game elements and sets their initial positions.
     * @param game The game instance associated with this level.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        //Set the position of the enemy
        getEnemy().setPosition(new Vec2(8, 7f));

        //Setting the player's initial position
        getPlayer().setPosition(new Vec2(20, -13.5f));

        //Setting the position of the portal
        getPortal().setPosition(new Vec2(-27, 8.5f));

        //Setting the position of the spikes
        getSpikes().setPosition(new Vec2(-16, -11.75f));

        //Setting the position of the tank
        getTank().setPosition(new Vec2(-25, -10.5f));
        getTank().setGravityScale(0);

        //Setting the position of the health potion
        getHealthPotion().setPosition(new Vec2(7, 5f));

        //Destroy objects that are not needed within this level
        getTeleportItem().destroy();
        getDarkFigure().destroy();
    }


    /**
     * Retrieves the name of the level.
     * @return The name of the level.
     */
    @Override
    public String getLevelName() {
        return "Level1";
    }


    /**
     * Retrieves the number of the level.
     * @return The number of the level.
     */
    @Override
    public int getLevelNo() {
        return 1;
    }


    /**
     * Checks if the level completion conditions are met.
     * @return True if the conditions are met, otherwise false.
     */
    @Override
    public boolean isComplete() {
        if (getPlayer() != null) {
            //goal: collect at least 3 good cookies
            //goal: defeat the ghost, earning the player a ticket
            return getPlayer().getCookieCount() >= 3 && getPlayer().getTicketCount() >= 1;
        } else {
            return false;
        }
    }

}
