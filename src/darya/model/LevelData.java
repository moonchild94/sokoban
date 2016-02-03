package src.darya.model;

import java.io.File;
import java.io.FileFilter;

import src.darya.common.Constants;

public class LevelData
{
    private int currentLevel = 1;
    private int maxLevel;
    private LevelLoader levelLoader = new LevelLoader();
    private GameObjects gameObjects;
    private int maxDefaultLevel = 1;

    public LevelData()
    {
        maxLevel = calculateMaxLevel();
    }

    public int getCurrentLevel()
    {
        return currentLevel;
    }

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public int getMaxDefaultLevel()
    {
        return maxDefaultLevel;
    }

    public int getMaxLevel()
    {
        return maxLevel;
    }

    public int incrementMaxLevel()
    {
        maxLevel++;
        return maxLevel;
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

    private int calculateMaxLevel()
    {
        File resourcesDirectory = new File(Constants.DIRECTORY_PREFIX);

        FileFilter levelFileFilter = new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                String pattern = "level[0-9]*.xml";
                return pathname.isFile() && pathname.getName().matches(pattern);
            }
        };

        File[] list = resourcesDirectory.listFiles(levelFileFilter);
        return list.length;
    }
}
