package darya.view;

import java.io.InputStream;

import org.eclipse.swt.widgets.Composite;

import darya.common.Constants;
import darya.utils.ResourceHelper;

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
    protected InputStream getImageView()
    {
        return ResourceHelper.getStream(Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/wallImage.jpg");
    }
}
