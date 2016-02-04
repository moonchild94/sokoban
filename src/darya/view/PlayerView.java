package src.darya.view;

import org.eclipse.swt.widgets.Composite;

import src.darya.common.Constants;

/**
 * Представление игрока.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public class PlayerView extends SimpleView
{
    public PlayerView(Composite composite)
    {
        super(composite);
    }

    @Override
    protected String getImageView()
    {
        return Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/playerImage.png";
    }
}
