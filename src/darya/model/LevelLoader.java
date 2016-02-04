package src.darya.model;

import java.io.File;

import src.darya.common.Constants;
import src.darya.utils.XMLHelper;

/**
 * Загрузчик игровых уровней.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public class LevelLoader
{
    private String fileNameFormat = Constants.DIRECTORY_LEVELS_PREFIX + "/level%d.xml";

    public GameObjects getLevel(int level)
    {
        File file = new File(String.format(fileNameFormat, level));
        GameObjects gameObjects = (GameObjects)XMLHelper.unwrapFromXML(file, GameObjects.class);
        return gameObjects;
    }
}