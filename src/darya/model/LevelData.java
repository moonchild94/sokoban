package src.darya.model;

public class LevelData
{
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader();
    private GameObjects gameObjects;

    public int getCurrentLevel()
    {
        return currentLevel;
    }

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void startLevel(int level)
    {
        currentLevel = level;
        gameObjects = levelLoader.getLevel(level);
    }

    public void startNextLevel()
    {
        gameObjects = levelLoader.getLevel(++currentLevel);
    }
}
