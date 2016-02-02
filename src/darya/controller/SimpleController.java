package src.darya.controller;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import src.darya.view.SimpleView;

public abstract class SimpleController extends AbstractController
{
    protected SimpleView view;

    public SimpleController(Display display, Composite composite)
    {
        super(display, composite);
    }

    @Override
    public SimpleView getView()
    {
        return view;
    }

    public void setView(SimpleView view)
    {
        this.view = view;
    }
}
