package src.darya.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class BoxView extends SimpleView
{
    public BoxView(Display display, Composite composite)
    {
        super(display, composite);
    }

    @Override
    protected String getImageView()
    {
        return "./resources/boxImage.jpg";
    }

}
