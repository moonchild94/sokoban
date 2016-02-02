package src.darya.model;

import java.nio.file.Paths;

public class LevelData
{
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get(".\\res\\levels.txt"));
    private GameObjects gameObjects;

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void startLevel(int level)
    {
        currentLevel = level;
        gameObjects = levelLoader.getLevel(level);
    }
}
