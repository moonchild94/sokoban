package src.darya.levelBuilder;

import java.io.File;
import java.util.Set;

import com.google.common.collect.Sets;

import src.darya.model.Box;
import src.darya.model.GameObjects;
import src.darya.model.Home;
import src.darya.model.Player;
import src.darya.model.Wall;

public class Wrapper
{
    public static void main(String[] args)
    {
        Set<Wall> walls = Sets.newHashSet();
        walls.add(new Wall(70, 50));
        walls.add(new Wall(70, 70));
        walls.add(new Wall(70, 90));
        walls.add(new Wall(70, 110));
        walls.add(new Wall(70, 130));

        Set<Box> boxes = Sets.newHashSet();
        boxes.add(new Box(90, 170));

        Set<Home> homes = Sets.newHashSet();
        homes.add(new Home(10, 210));

        Player player = new Player(150, 70);

        GameObjects gameObjects = new GameObjects(walls, boxes, homes, player);

        XMLHelper.wrapToXML(new File("./resources/level4.xml"), gameObjects);
    }
}
