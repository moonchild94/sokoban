package src.darya.view;

import org.eclipse.swt.widgets.Composite;

import src.darya.common.Constants;

public class HomeView extends SimpleView
{
    public HomeView(Composite composite)
    {
        super(composite);
    }

    @Override
    protected String getImageView()
    {
        return Constants.DIRECTORY_PREFIX + "/homeImage.png";
    }

}