package src.darya.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class PlayerView extends SimpleView
{
    public PlayerView(Display display, Composite composite)
    {
        super(display, composite);
    }

    @Override
    protected String getImageView()
    {
        return "./resources/playerImage.png";
    }

}
