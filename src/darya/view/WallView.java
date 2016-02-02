package src.darya.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class WallView extends SimpleView
{
    public WallView(Display display, Composite composite)
    {
        super(display, composite);
    }

    @Override
    protected String getImageView()
    {
        return "./resources/wallImage.jpg";
    }

}
