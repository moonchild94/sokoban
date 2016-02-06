package darya.view;

import java.io.InputStream;

import org.eclipse.swt.widgets.Composite;

import darya.common.Constants;
import darya.utils.ResourceHelper;

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
    protected InputStream getImageView()
    {
        return ResourceHelper.getStream(Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/boxImage.jpg");
    }
}
