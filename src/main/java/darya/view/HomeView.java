package darya.view;

import java.io.InputStream;

import org.eclipse.swt.widgets.Composite;

import darya.common.Constants;
import darya.utils.ResourceHelper;

/**
 * Представление черной дыры.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public class HomeView extends SimpleView
{
    public HomeView(Composite composite)
    {
        super(composite);
    }

    @Override
    protected InputStream getImageView()
    {
        return ResourceHelper.getStream(Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/homeImage.png");
    }
}