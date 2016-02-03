package src.darya.controller;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import src.darya.view.PlayerView;

public class PlayerController extends SimpleController
{
    public PlayerController(Display display, Composite composite)
    {
        super(display, composite);
        createView();
    }

    @Override
    protected void createView(boolean... params)
    {
        setView(new PlayerView(getDisplay(), getComposite()));
    }
}
