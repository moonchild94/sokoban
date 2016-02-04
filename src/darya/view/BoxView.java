package src.darya.view;

import org.eclipse.swt.widgets.Composite;

import src.darya.common.Constants;

/**
 * Представление ящика.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public class BoxView extends SimpleView
{
    public BoxView(Composite composite)
    {
        super(composite);
    }

    @Override
    protected String getImageView()
    {
        return Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/boxImage.jpg";
    }
}
