package Game.GameLevels;

//imports
import Game.Collectibles.CookieSpawns;
import Game.Collectibles.HealthPotion;
import Game.CollisionEvents.PlayerCollisions;
import Game.CollisionEvents.PortalEncounter;
import Game.EnemyProperties.Enemy;
import Game.GameElements.HousePortal;
import Game.Collectibles.TeleportItem;
import Game.GameElements.Walls;
import Game.EnemyProperties.DarkFigure;
import Game.EnemyProperties.FloorSpikes;
import Game.EnemyProperties.Tank;
import Game.Game;
import Game.Player;
import city.cs.engine.World;


/**
 * The abstract class GameLevel represents the common class for the rest of the
 * level in the game world, providing functionalities and attributes for different levels.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public abstract class GameLevel extends World {
    /** The player character in the level. */
    private Player player;

    /** The enemy character in the level. */
    private Enemy enemy;

    /** The game instance associated with the level. */
    private final Game game;

    /** The floor spikes in the level. */
    private FloorSpikes spikes;

    /** The portal in the level. */
    private HousePortal portal;

    /** The dark figure character in the level. */
    private DarkFigure darkFigure;

    /** The tank character in the level. */
    private Tank tank;

    /** The health potion item in the level. */
    private HealthPotion healthPotion;

    /** The teleport item in the level. */
    private TeleportItem teleportItem;


    /**
     * Constructs a GameLevel object with the specified game instance.
     * @param game The game instance associated with the level.
     */
    public GameLevel(Game game) {
        this.game = game;

        //the walls will be present in every single level
        Walls wall = new Walls(this, 0.5f, 100, -31.5f, 2, "#000");
        Walls wall2 = new Walls(this, 0.5f, 100, 31.5f, 2, "#000");
    }


    /**
     * Checks whether the level is complete.
     * @return true if the level is complete, otherwise false.
     */
    public abstract boolean isComplete();


    /**
     * Gets the name of the level.
     * @return The name of the level.
     */
    public abstract String getLevelName();


    /**
     * Gets the number of the level.
     * @return The number of the level.
     */
    public abstract int getLevelNo();


    /**
     * Populates the game level with necessary elements such as player, portal, enemies, etc.
     * @param game The game instance associated with the level.
     */
    public void populate(Game game){
        //Creating the player object for this game level
        player = new Player(this);

        //Adding collision listener to handle player collisions
        player.addCollisionListener(new PlayerCollisions(player));
        player.addCollisionListener(new PortalEncounter(this, game));

        //Creating the portal object for this game level
        portal = new HousePortal(this);

        // Creating and starting the cookie spawning mechanism
        CookieSpawns cookieSpawns = new CookieSpawns(this);
        cookieSpawns.startSpawning();

        //creating the enemy object for this game level
        enemy = new Enemy(this);

        //creating the dark figure object for this game level
        darkFigure = new DarkFigure(this, player);

        spikes = new FloorSpikes(this, player);

        //creating the floor spikes object for this game level
        tank = new Tank(this, player);

        //creating a health potion object for this game level
        healthPotion = new HealthPotion(this);
    }


    /**
     * Gets the player object associated with the level.
     * @return The player object.
     */
    public Player getPlayer() {
        return player;
    }


    /**
     * Sets the player object associated with the level.
     * @param player The player object.
     */
    public void setPlayer(Player player){
        this.player = player;
    }


    /**
     * Gets the portal object associated with the level.
     * @return The portal object.
     */
    public HousePortal getPortal() {
        return portal;
    }


    /**
     * Gets the floor spikes object associated with the level.
     * @return The floor spikes object.
     */
    public FloorSpikes getSpikes() {
        return spikes;
    }


    /**
     * Sets the floor spikes object associated with the level.
     * @param spikes The floor spikes object.
     */
    public void setSpikes(FloorSpikes spikes) {
        this.spikes = spikes;
    }


    /**
     * Gets the dark figure object associated with the level.
     * @return The dark figure object.
     */
    public DarkFigure getDarkFigure(){
        return darkFigure;
    }


    /**
     * Sets the dark figure object associated with the level.
     * @param darkFigure The dark figure object.
     */
    public void setDarkFigure(DarkFigure darkFigure) {
        this.darkFigure = darkFigure;
    }


    /**
     * Gets the teleport item associated with the level.
     * @return The teleport item object.
     */
    public TeleportItem getTeleportItem() {
        return teleportItem = new TeleportItem(this);
    }


    /**
     * Sets the teleport item associated with the level.
     * @param teleportItem The teleport item object.
     */
    public void setTeleportItem(TeleportItem teleportItem) {
        this.teleportItem = teleportItem;
    }


    /**
     * Creates a new instance of the teleport item
     * @param teleportToX The x-coordinate to teleport to.
     * @param teleportToY The y-coordinate to teleport to.
     * @param x           The x-coordinate of the teleport item.
     * @param y           The y-coordinate of the teleport item.
     */
    public void newTeleportItem(float teleportToX, float teleportToY, float x, float y){
        this.teleportItem = new TeleportItem(this,teleportToX, teleportToY, x, y);
    }


    /**
     * Gets the tank object associated with the level.
     * @return The tank object.
     */
    public Tank getTank() {
        return tank;
    }


    /**
     * Sets the tank object associated with the level.
     * @param tank The tank object.
     */
    public void setTank(Tank tank) {
        this.tank = tank;
    }


    /**
     * Gets the health potion associated with the level.
     * @return The health potion object.
     */
    public HealthPotion getHealthPotion(){
        return healthPotion;
    }


    /**
     * Sets the health potion object associated with the level.
     * @param potion The health potion object.
     */
    public void setHealthPotion(HealthPotion potion){
        this.healthPotion = potion;
    }


    /**
     * Gets the game instance associated with the level.
     * @return The game instance.
     */
    public Game getGame() {
        return game;
    }


    /**
     * Gets the enemy object associated with the level.
     * @return The enemy object.
     */
    public Enemy getEnemy() {
        return enemy;
    }


    /**
     * Sets the enemy object associated with the level.
     * @param enemy The enemy object.
     */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
