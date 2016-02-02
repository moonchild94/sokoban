package src.darya.model;

import java.nio.file.Path;
import java.util.Set;

import com.google.common.collect.Sets;

public class LevelLoader 
{
	private Path levels;

    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }

    public GameObjects getLevel(int level)
    {
    	Set<Wall> walls = Sets.newHashSet();
    	walls.add(new Wall(30, 50));
    	walls.add(new Wall(30, 70));
    	walls.add(new Wall(30, 90));
    	walls.add(new Wall(30, 110));
    	walls.add(new Wall(30, 130));
    	
        Set<Box> boxes = Sets.newHashSet();
        boxes.add(new Box(90, 90));
        
        Set<Home> homes = Sets.newHashSet();
        homes.add(new Home(10, 90));
        
        Player player = new Player(70, 70);
        
        return new GameObjects(walls, boxes, homes, player);
    }
}
