package src.darya.controller;

import org.eclipse.swt.widgets.Composite;

import src.darya.view.PlayerView;

public class PlayerController extends SimpleController
{
    public PlayerController(Composite composite)
    {
        super(composite);
        createView();
    }

    @Override
    protected void createView(boolean... params)
    {
        setView(new PlayerView(getComposite()));
    }
}
