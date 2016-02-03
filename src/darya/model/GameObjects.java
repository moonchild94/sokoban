package src.darya.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Sets;

@XmlRootElement
public class GameObjects
{
    @XmlElementWrapper(name = "wallSet")
    private Set<Wall> walls;
    @XmlElementWrapper(name = "boxSet")
    private Set<Box> boxes;
    @XmlElementWrapper(name = "homeSet")
    private Set<Home> homes;
    @XmlElement
    private Player player;

    public GameObjects()
    {

    }

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player)
    {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public Set<GameObject> getAll()
    {
        Set<GameObject> gameObjects = Sets.newLinkedHashSet();
        gameObjects.add(player);
        gameObjects.addAll(walls);
        gameObjects.addAll(boxes);
        gameObjects.addAll(homes);

        return gameObjects;
    }

    public Set<Box> getBoxes()
    {
        return boxes;
    }

    public Set<Home> getHomes()
    {
        return homes;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Set<Wall> getWalls()
    {
        return walls;
    }
}
