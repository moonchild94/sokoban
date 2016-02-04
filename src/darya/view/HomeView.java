package src.darya.view;

import org.eclipse.swt.widgets.Composite;

import src.darya.common.Constants;

/**
 * ������������� ������ ����.
 * @author ��������� �.�.
 * @sinse 5 ����. 2016 �.
 */
public class HomeView extends SimpleView
{
    public HomeView(Composite composite)
    {
        super(composite);
    }

    @Override
    protected String getImageView()
    {
        return Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/homeImage.png";
    }
}