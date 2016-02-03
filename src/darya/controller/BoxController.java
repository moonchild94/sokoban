package src.darya.controller;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import src.darya.view.BoxView;

public class BoxController extends SimpleController
{
    public BoxController(Display display, Composite composite)
    {
        super(display, composite);
        createView();
    }

    @Override
    protected void createView(boolean... params)
    {
        setView(new BoxView(getDisplay(), getComposite()));
    }
}
