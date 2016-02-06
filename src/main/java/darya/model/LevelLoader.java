package darya.model;

import java.io.File;

import darya.common.Constants;
import darya.utils.ResourceHelper;
import darya.utils.XMLHelper;

/**
 * ��������� ������� �������.
 * @author ��������� �.�.
 * @sinse 5 ����. 2016 �.
 */
public class LevelLoader
{
    public GameObjects getLevel(int level)
    {
        File file;

        if (level > Constants.MAX_DEFALT_LEVEL)
        {
            String fileNameFormat = LevelData.additionalLevelsDirectory + "/level%d.xml";
            file = new File(String.format(fileNameFormat, level));
        }
        else
        {
            String fileNameFormat = Constants.DIRECTORY_LEVELS_PREFIX + "/level%d.xml";
            file = ResourceHelper.getFile(String.format(fileNameFormat, level));
        }
        GameObjects gameObjects = (GameObjects)XMLHelper.unwrapFromXML(file, GameObjects.class);
        return gameObjects;
    }
}