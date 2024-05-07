package Game.DataManagement;

//imports
import Game.Collectibles.*;
import Game.CollisionEvents.PlayerCollisions;
import Game.CollisionEvents.PortalEncounter;
import Game.GameElements.*;
import Game.EnemyProperties.*;
import Game.Game;
import Game.GameLevels.*;
import Game.Player;
import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Utility class for saving and loading game levels.
 * Provides methods to save the state of a game level to a file and load a game level from a file.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class SaverAndLoader {

    /**
     * Saves the state of a game level to a file.
     * @param fileName The name of the file to save to.
     * @param level    The game level to save.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public static void save(String fileName, GameLevel level) throws IOException {
        boolean append = false;

        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(level.getLevelName() + "\n");

            //tracking all the static bodies
            for (StaticBody body : level.getStaticBodies()) {
                String type = "";
                if (body instanceof Coins)
                    type = "Coins";
                else if (body instanceof HealthPotion)
                    type = "HealthPotion";
                else if (body instanceof Ground) {
                    Ground ground = (Ground) body; // Cast body to Ground
                    type = "Ground";
                    writer.write(type + "," + body.getPosition().x + "," + body.getPosition().y +","+
                            ground.getFilePath()+"\n"); // Call getFilePath() on the ground instance
                    continue;
                } else if (body instanceof HousePortal)
                    type = "HousePortal";
                else if (body instanceof Lift)
                    type = "Lift";
                else if (body instanceof MovingWall) {
                    MovingWall movingWall = (MovingWall) body; // Cast body to MovingWall
                    writer.write("MovingWall," + body.getPosition().x + "," + body.getPosition().y + "," +
                            movingWall.isOpen() + "," + movingWall.getLastTimeOpened() + "," + movingWall.getCurrentSpeed() + "\n");
                }else if (body instanceof TeleportItem){
                    TeleportItem teleportItem = (TeleportItem) body;
                    writer.write("TeleportItem," + teleportItem.getPosition().x + "," + teleportItem.getPosition().y
                            + "," + teleportItem.getTeleportToX() + "," + teleportItem.getTeleportToY() + "\n");
                    continue;
                }
                else if (body instanceof Trampoline)
                    type = "Trampoline";
                else if (body instanceof FloorSpikes)
                    type = "FloorSpikes";

                writer.write(type + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
            }

            //tracking all the DynamicBodies
            for (DynamicBody body : level.getDynamicBodies()) {
                String type = "";
                if (body instanceof BadCookie)
                    type = "BadCookie";
                else if (body instanceof GoodCookie)
                    type = "GoodCookie";
                else if (body instanceof DarkFigure)
                    type = "DarkFigure";
                else if (body instanceof FallingItems)
                    type = "FallingItems";
                else if (body instanceof Tank)
                    type = "Tank";
                else if (body instanceof Enemy) {
                    type = "Enemy";
                    writer.write(type + "," + body.getPosition().x + "," + body.getPosition().y + ","
                            + ((Enemy) body).getRANGE() + "\n");
                    continue;
                }
                else if (body instanceof Player) {
                    type = "Player";
                    writer.write(type + "," + body.getPosition().x + "," + body.getPosition().y + ","
                            + ((Player) body).getRemainingLives() + "," + ((Player) body).getPoints() + ","
                            + ((Player) body).getCookieCount() + "," + ((Player) body).getCoinCount() + ","
                            + ((Player) body).getTicketCount() +"\n");
                    continue;
                }

                writer.write(type + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
            }

        } catch (IOException e) {
            // Handle IO exception
            e.printStackTrace();
        }
    }

    /**
     * Loads a game level from a file.
     * @param fileName       The name of the file to load from.
     * @param game           The game instance to which the loaded level belongs.
     * @param notNullPlayer  A reference to a non-null player instance.
     * @return The loaded game level.
     * @throws IOException if an I/O error occurs while reading from the file.
     */
    public static GameLevel load(String fileName, Game game, Player notNullPlayer) throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;

        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();

            GameLevel level;
            switch (line) {
                case "Level1" -> level = new Level1(game);
                case "Level2" -> level = new Level2(game);
                case "Level3" -> level = new Level3(game);
                case "Level4" -> level = new Level4(game);
                default -> throw new IllegalArgumentException("Unknown level number: " + line);
            }

            line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(",");


                switch (tokens[0]) {
                    case "Player" -> {
                        Player player = new Player(level);
                        player.setPosition(new Vec2(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2])));
                        player.setRemainingLives(Integer.parseInt(tokens[3]));
                        player.setPoint(Integer.parseInt(tokens[4]));
                        player.setCookieCount(Integer.parseInt(tokens[5]));
                        player.setCoinCount(Integer.parseInt(tokens[6]));
                        player.setTicketCount(Integer.parseInt(tokens[7]));

                        // Add collision listeners
                        PortalEncounter encounter = new PortalEncounter(level, level.getGame());
                        player.addCollisionListener(encounter);

                        PlayerCollisions collisions = new PlayerCollisions(player);
                        player.addCollisionListener(collisions);

                        level.setPlayer(player);
                    }
                    case "Coins" -> {
                        Coins coins = new Coins(level);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        coins.setPosition(new Vec2(x, y));
                    }
                    case "HealthPotion" -> {
                        HealthPotion potion = new HealthPotion(level);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        potion.setPosition(new Vec2(x, y));

                        level.setHealthPotion(potion);

                    }
                    case "HousePortal" -> {
                        HousePortal portal = new HousePortal(level);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        portal.setPosition(new Vec2(x, y));
                    }
                    case "MovingWall" -> {
                        MovingWall movingWall = new MovingWall(level, notNullPlayer, -13.5f, -8f);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        movingWall.setPosition(new Vec2(x, y));


                        boolean isOpen = Boolean.parseBoolean(tokens[3]);
                        long lastTimeOpened = Long.parseLong(tokens[4]);
                        float currentSpeed = Float.parseFloat(tokens[5]);


                        movingWall.setOpen(isOpen);
                        movingWall.setLastTimeOpened(lastTimeOpened);
                        movingWall.setCurrentSpeed(currentSpeed);
                    }
                    case "TeleportItem" -> {
                        TeleportItem item = new TeleportItem(level);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        item.setPosition(new Vec2(x, y));

                        float teleportX = Float.parseFloat(tokens[3]);
                        float teleportY = Float.parseFloat(tokens[4]);
                        item.teleportTo(teleportX, teleportY);

                        level.setTeleportItem(item);
                    }

                    case "Ground" -> {
                        Ground ground = new Ground(level, tokens[3]); // Pass filepath to Ground constructor
                        ground.setFilePath(tokens[3]);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        ground.setPosition(new Vec2(x, y));
                    }

                    case "Trampoline" -> {
                        Trampoline trampoline = new Trampoline(level);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        trampoline.setPosition(new Vec2(x, y));
                    }
                    case "FloorSpikes" -> {
                        FloorSpikes spikes = new FloorSpikes(level, notNullPlayer);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        spikes.setPosition(new Vec2(x, y));

                        level.setSpikes(spikes);
                    }
                    case "Enemy" -> {
                        Enemy enemy = new Enemy(level);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        enemy.setInitialPosition(new Vec2(x, y));

                        int range = Integer.parseInt(tokens[3]);
                        enemy.setRANGE(range);

                        level.setEnemy(enemy);
                    }
                    case "DarkFigure" -> {
                        DarkFigure darkFigure = new DarkFigure(level, notNullPlayer);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        darkFigure.setPosition(new Vec2(x, y));

                        level.setDarkFigure(darkFigure);
                    }
                    case "FallingItems" -> {
                        FallingItems boxes = new FallingItems(level);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        boxes.setPosition(new Vec2(x, y));
                    }
                    case "BadCookie" -> {
                        BadCookie badCookie = new BadCookie(level);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        badCookie.setPosition(new Vec2(x, y));
                    }
                    case "GoodCookie" -> {
                        GoodCookie goodCookie = new GoodCookie(level);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        goodCookie.setPosition(new Vec2(x, y));
                    }
                    case "Tank" -> {
                        Tank tank = new Tank(level, notNullPlayer);

                        float x = Float.parseFloat(tokens[1]);
                        float y = Float.parseFloat(tokens[2]);
                        tank.setPosition(new Vec2(x, y));

                        level.setTank(tank);
                    }
                }

                line = reader.readLine();
            }

            return level;

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}