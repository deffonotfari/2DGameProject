package Game.GameLevels;

//imports
import Game.Collectibles.Coins;
import Game.GameElements.Ground;
import Game.GameElements.MovingWall;
import Game.GameElements.Platforms;
import Game.GameElements.Trampoline;
import Game.EnemyProperties.FallingItems;
import Game.Game;
import org.jbox2d.common.Vec2;


/**
 * Represents the third level of the game.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Level3 extends GameLevel{
    /**
     * Constructs Level3 by populating it with platforms and setting the ground.
     * @param game The game instance associated with this level.
     */
    public Level3(Game game) {
        super(game);

        //creating platforms
        Platforms bigPlatform = new Platforms(this, 22, 0.5f, 9,-4);
        Platforms platform1 = new Platforms(this, 12, 0.5f, 8,24);
        Platforms platform2 = new Platforms(this, 12, 0.5f, 19.29f,6);
        Platforms platformWithTank = new Platforms(this, 10, 0.5f, -21.5f,16);
        Platforms platformWithPortal = new Platforms(this, 10, 0.5f, -22,28);

        //rendering images for the platforms
        bigPlatform.photo("data/Platforms/level3/bigPlatform.png",1);
        platform2.photo("data/Platforms/level3/platform2.png",1);
        platformWithTank.photo("data/Platforms/level3/platformWithTank.png",1);
        platformWithPortal.photo("data/Platforms/level3/platformWithPortal.png",1);
        platform1.photo("data/Platforms/level3/platform2.png",1);

        //creating trampolines a trampoline,
        Trampoline trampoline = new Trampoline(this, -3f, -2.5f, 2);
        Trampoline trampoline2 = new Trampoline(this, 28,7.15f, 2);

        //creating the ground
        Ground ground = new Ground(this, "data/road/road3.png");
        ground.setPosition(new Vec2(0, -13.5f));
    }

    @Override
    public void populate(Game game) {
        super.populate(game);

        //creating the platform
        getPlayer().setPosition(new Vec2(29, -13f));

        //setting position for the portal
        getPortal().setPosition(new Vec2(-26, 31.75f));

        //setting position for the spikes
        getSpikes().setPosition(new Vec2(5.5f,6));

        //creating a new teleport item that moves player to the big Platform
        newTeleportItem(29, -1,-29,-10);

        //creating a moving gate
        MovingWall movingGate = new MovingWall(this, getPlayer(), -13.5f, -8f);

        //Creating 6 coins: located on bigPlatform
        for (int i = 0; i<6;i++){
            Coins coins = new Coins(this);
            coins.setPosition(new Vec2(1+(i*5.78f),-2.5f));
        }

        //Creating 4 coins: located on platform2
        for (int i = 0; i<4;i++){
            Coins coins = new Coins(this);
            coins.setPosition(new Vec2(9+(i*5),7.5f));
        }

        //keeps 5 jumping box
        for (int i = 0; i < 5; i++){
            float x =  -5+(i*7.5f);
            float y = -12+(i+1.5f);

            FallingItems jumpingObject = new FallingItems(this);
            jumpingObject.setPosition(new Vec2(x, y));
        }

        //setting location for health potion
        getHealthPotion().setPosition(new Vec2(8,26f));

        //creating and setting position for the tank
        getTank().setPosition(new Vec2(-19,18));
        getTank().setGravityScale(0);

        //destroy objects that are not needed within this level
        getDarkFigure().destroy();
        getEnemy().destroy();

    }


    /**
     * Retrieves the name of the level.
     * @return The name of the level.
     */
    @Override
    public String getLevelName() {
        return "Level3";
    }


    /**
     * Retrieves the number of the level.
     * @return The number of the level.
     */
    @Override
    public int getLevelNo() {
        return 3;
    }

    /**
     * Checks if the level completion conditions are met.
     * @return True if the conditions are met, otherwise false.
     */
    @Override
    public boolean isComplete() {
        //goal: collect all coins in the level
        return getPlayer().getCoinCount() >=24;
    }
}
