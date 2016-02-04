package src.darya.controller;

import org.eclipse.swt.widgets.Composite;

import src.darya.view.HomeView;

public class HomeController extends SimpleController
{
    public HomeController(Composite composite)
    {
        super(composite);
        createView();
    }

    @Override
    protected void createView(boolean... params)
    {
        setView(new HomeView(getComposite()));
    }
}
