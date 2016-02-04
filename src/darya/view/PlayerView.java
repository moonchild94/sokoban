package src.darya.view;

import org.eclipse.swt.widgets.Composite;

import src.darya.common.Constants;

public class PlayerView extends SimpleView
{
    public PlayerView(Composite composite)
    {
        super(composite);
    }

    @Override
    protected String getImageView()
    {
        return Constants.DIRECTORY_PREFIX + "/playerImage.png";
    }

}
