package src.darya.controller;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import src.darya.view.WallView;

public class WallController extends AbstractController
{
    public WallController(Display display, Composite composite)
    {
        super(display, composite);
        createView();
    }

    @Override
    protected void createView()
    {
        setView(new WallView(getDisplay(), getComposite()));
    }
}
