package Game.GameLevels;

//imports
import Game.Collectibles.Coins;
import Game.EnemyProperties.FallingItems;
import Game.Game;
import Game.GameElements.Ground;
import Game.GameElements.Platforms;
import org.jbox2d.common.Vec2;

/**
 * Represents the fourth level of the game.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Level4 extends GameLevel{
    /**
     * Constructs Level4 by populating it with platforms, setting the ground, and initializing game elements.
     * @param game The game instance associated with this level.
     */
    public Level4(Game game) {
        super(game);

        //creating platforms
        Platforms platform1 = new Platforms(this, 5,0.5f,-27,-5.5f);
        Platforms platform2 = new Platforms(this, 4,0.5f,8,3);
        Platforms platform3 = new Platforms(this, 8f,0.5f,-7,-1);
        Platforms platform4 = new Platforms(this, 5f,0.5f,-18,6);
        Platforms platform5 = new Platforms(this, 12,0.5f,20,9f);
        Platforms platformWithPortal = new Platforms(this, 14f,0.5f,10,18);

        //rendering the platforms with images
        platform1.photo("data/Platforms/level4/platform1.png",1);
        platform2.photo("data/Platforms/level4/platform2.png",1);
        platform3.photo("data/Platforms/level4/platform3.png",1);
        platform4.photo("data/Platforms/level4/platform1.png",1);
        platform5.photo("data/Platforms/level4/platform5.png",1);
        platform2.photo("data/Platforms/level4/platform2.png",1);
        platformWithPortal.photo("data/Platforms/level4/platform6.png",1);

        //creating ground
        Ground ground = new Ground(this, "data/road/road4.png");
        ground.setPosition(new Vec2(0, -13.5f));
    }

    /**
     * Populates the level with game elements, sets their initial positions, and destroys unnecessary objects.
     * @param game The game instance associated with this level.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        //setting position for player
        getPlayer().setPosition(new Vec2(-25,-13.2f));

        //setting position for the portal
        getPortal().setPosition(new Vec2(3,21.5f));

        //creating and changing the range of ghost's movement
        getEnemy().setPosition(new Vec2(-18,6.5f));
        getEnemy().setRANGE(2);

        //creating a dark figure and setting position
        getDarkFigure().setPosition(new Vec2(25, -9.5f));

        //creating health potion and setting position
        getHealthPotion().setPosition(new Vec2(19,10.5f));

        //Creating 2 coins, located on platform1
        for (int i = 0; i<2;i++){
            Coins coins = new Coins(this);
            coins.setPosition(new Vec2(-30+(i*5),-4f));
        }

        //Creating 2 coins, located on platform2
        for (int i = 0; i<2;i++){
            Coins coins = new Coins(this);
            coins.setPosition(new Vec2(6.5f+(i*4),4.5f));
        }

        //Creating 4 coins, located on platform3
        for (int i = 0; i<4;i++){
            Coins coins = new Coins(this);
            coins.setPosition(new Vec2(-13.5f+(i*4),0.5f));
        }

        //Creating 4 coins, located on platformWithPortal
        for (int i = 0; i<4;i++){
            Coins coins = new Coins(this);
            coins.setPosition(new Vec2(9.5f+(i*4),19.5f));
        }

        //creating 2 jumpingObjects on platform5
        for (int i = 0; i < 2; i++){
            //creating the x-coordinate and y-coordinate
            float x =  15.5f+(i*6.5f);
            float y = 8f+(i+2f);

            FallingItems jumpingObject = new FallingItems(this);

            //setting their speed
            jumpingObject.setSpeed(12);

            //setting their location
            jumpingObject.setPosition(new Vec2(x, y));
        }

        //destroy objects that are not needed within this level
        getTeleportItem().destroy();
        getSpikes().destroy();
        getTank().destroy();
        getTank().stopTimer(); //stopping the timer so no cannons are shot
    }

    /**
     * Retrieves the name of the level.
     * @return The name of the level.
     */
    @Override
    public String getLevelName() {return "Level4";}


    /**
     * Retrieves the number of the level.
     * @return The number of the level.
     */
    @Override
    public int getLevelNo() {
        return 4;
    }

    /**
     * Checks if the level completion conditions are met.
     * @return True if the conditions are met, otherwise false.
     */
    @Override
    public boolean isComplete() {
        //goal: defeat the ghost and the dark figure, earning 2 tickets
        return getPlayer().getTicketCount() >=4;
    }

}
