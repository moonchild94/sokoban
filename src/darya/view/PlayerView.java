package src.darya.view;

import org.eclipse.swt.widgets.Composite;

public class PlayerView extends SimpleView
{
    public PlayerView(Composite composite)
    {
        super(composite);
    }

    @Override
    protected String getImageView()
    {
        return "./resources/playerImage.png";
    }

}
