package src.darya.view;

import org.eclipse.swt.widgets.Composite;

import src.darya.common.Constants;

public class BoxView extends SimpleView
{
    public BoxView(Composite composite)
    {
        super(composite);
    }

    @Override
    protected String getImageView()
    {
        return Constants.DIRECTORY_PREFIX + "/boxImage.jpg";
    }

}
