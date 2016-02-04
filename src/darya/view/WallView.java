package src.darya.view;

import org.eclipse.swt.widgets.Composite;

import src.darya.common.Constants;

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
    protected String getImageView()
    {
        return Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/wallImage.jpg";
    }
}
