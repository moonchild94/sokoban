package src.darya.view;

import org.eclipse.swt.widgets.Composite;

public class BoxView extends SimpleView
{
    public BoxView(Composite composite)
    {
        super(composite);
    }

    @Override
    protected String getImageView()
    {
        return "./resources/boxImage.jpg";
    }

}
