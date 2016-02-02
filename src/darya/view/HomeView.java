package src.darya.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class HomeView extends SimpleView
{
    public HomeView(Display display, Composite composite)
    {
        super(display, composite);
    }

    @Override
    protected String getImageView()
    {
        return "./resources/homeImage.png";
    }

}