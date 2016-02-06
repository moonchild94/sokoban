package darya.model;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;

import darya.common.Constants;

/**
 * Утилитарный класс, содержащий данные об игровых уровнях.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public class LevelData
{
    public static File additionalLevelsDirectory;

    static
    {
        try
        {
            additionalLevelsDirectory = Files.createTempDirectory(Constants.DIRECTORY_ADDITIONAL_LEVELS_PREFIX)
                    .toFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private int currentLevel = 1;
    private int maxLevel;
    private LevelLoader levelLoader = new LevelLoader();

    private GameObjects gameObjects;

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
        File resourcesDirectory = LevelData.additionalLevelsDirectory;

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
        return list.length + Constants.MAX_DEFALT_LEVEL;
    }
}
