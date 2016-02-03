package src.darya.controller;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import src.darya.view.HomeView;

public class HomeController extends SimpleController
{
    public HomeController(Display display, Composite composite)
    {
        super(display, composite);
        createView();
    }

    @Override
    protected void createView(boolean... params)
    {
        setView(new HomeView(getDisplay(), getComposite()));
    }
}
