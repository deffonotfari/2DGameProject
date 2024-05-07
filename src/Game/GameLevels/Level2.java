package Game.GameLevels;

//imports
import Game.Collectibles.Coins;
import Game.GameElements.Ground;
import Game.GameElements.Platforms;
import Game.Game;
import org.jbox2d.common.Vec2;

/**
 * Represents the second level of the game.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Level2 extends GameLevel {

    /**
     * Constructs Level2 by populating it with platforms and setting the ground.
     * @param game The game instance associated with this level.
     */
    public Level2(Game game) {
        super(game);

        //creating the ground
        Ground ground = new Ground(this, "data/road/road1.png");
        ground.setPosition(new Vec2(0, -13.5f));

        //creating platforms for the second level
        Platforms leftPlatform1 = new Platforms(this, 13f, 0.5f, -18, -1);
        Platforms rightPlatform1 = new Platforms(this, 15f, 0.5f, 16, 5);
        Platforms leftPlatform2 = new Platforms(this, 10f, 0.5f, -22, 11);

        //rendering the platforms with images
        leftPlatform1.photo("data/Platforms/level2/leftPlatform1.png", 1);
        rightPlatform1.photo("data/Platforms/level2/rightPlatform1.png", 1);
        leftPlatform2.photo("data/Platforms/level2/leftPlatform2.png", 1);
    }


    /**
     * Populates the level with game elements, sets their initial positions, and destroys unnecessary objects.
     * @param game The game instance associated with this level.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        //creating player
        getPlayer().setPosition(new Vec2(22, -13.5f));

        //creating the portal
        getPortal().setPosition(new Vec2(25, -9.5f));

        //creating a teleportation item: Teleport to leftPlatform2
        newTeleportItem(-22, 12.5f,-27,-10);

        //creating a teleportation item: Teleport to rightPlatform1
        newTeleportItem(16, 6.5f, -29,1.5f);

        //creating 3 coins: located on the leftPlatform1
        for (int i = 0; i<3;i++){
            Coins coins = new Coins(this);
            coins.setPosition(new Vec2(-26+(i*5),13.5f));
        }

        //creating 5 coins: located on the leftPlatform2
        for (int i = 0; i<5;i++){
            Coins coins = new Coins(this);
            coins.setPosition(new Vec2(-26.5f+(i*5),1.5f));
        }

        //creating 6 coins: located on the rightPlatform1
        for (int i = 0; i<6;i++){
            Coins coins = new Coins(this);
            coins.setPosition(new Vec2(5+(i*5),7.5f));
        }

        //creating the dark figure
        getDarkFigure().setPosition(new Vec2(-25, -9.5f));

        //Destroy objects that are not needed within this level
        getEnemy().destroy();
        getSpikes().destroy();
        getTank().stopTimer(); //stop the timer for shooting cannons
        getTank().destroy();
        getHealthPotion().destroy();
    }

    /**
     * Retrieves the name of the level.
     * @return The name of the level.
     */
    @Override
    public String getLevelName() {
        return "Level2";
    }


    /**
     * Retrieves the number of the level.
     * @return The number of the level.
     */
    @Override
    public int getLevelNo() {
        return 2;
    }


    /**
     * Checks if the level completion conditions are met.
     * @return True if the conditions are met, otherwise false.
     */
    @Override
    public boolean isComplete() {
        //goal: destroy the darkfigure, whill will earn the user another ticker
        //goal: collect all of the coins
        return (getPlayer().getTicketCount() >=2 && getPlayer().getCoinCount() >= 14);
    }

}
