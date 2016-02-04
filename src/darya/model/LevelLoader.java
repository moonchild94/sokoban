package src.darya.model;

import java.io.File;

import src.darya.common.Constants;
import src.darya.utils.XMLHelper;

/**
 * ��������� ������� �������.
 * @author ��������� �.�.
 * @sinse 5 ����. 2016 �.
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