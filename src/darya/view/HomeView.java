package src.darya.view;

import org.eclipse.swt.widgets.Composite;

public class HomeView extends SimpleView
{
    public HomeView(Composite composite)
    {
        super(composite);
    }

    @Override
    protected String getImageView()
    {
        return "./resources/homeImage.png";
    }

}