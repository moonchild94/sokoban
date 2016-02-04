package src.darya.view;

import org.eclipse.swt.widgets.Composite;

import src.darya.common.Constants;

/**
 * Представление стены.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public class WallView extends SimpleView
{
    public WallView(Composite composite)
    {
        super(composite);
    }

    @Override
    protected String getImageView()
    {
        return Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/wallImage.jpg";
    }
}
