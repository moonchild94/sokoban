package darya.view;

import java.io.InputStream;

import org.eclipse.swt.widgets.Composite;

import darya.common.Constants;
import darya.utils.ResourceHelper;

/**
 * ������������� �����.
 * @author ��������� �.�.
 * @sinse 5 ����. 2016 �.
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
