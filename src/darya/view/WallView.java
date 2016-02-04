package src.darya.view;

import org.eclipse.swt.widgets.Composite;

public class WallView extends SimpleView
{
    public WallView(Composite composite)
    {
        super(composite);
    }

    @Override
    protected String getImageView()
    {
        return "./resources/wallImage.jpg";
    }

}
