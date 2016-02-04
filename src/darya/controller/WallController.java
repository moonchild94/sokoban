package src.darya.controller;

import org.eclipse.swt.widgets.Composite;

import src.darya.view.WallView;

public class WallController extends SimpleController
{
    public WallController(Composite composite)
    {
        super(composite);
        createView();
    }

    @Override
    protected void createView(boolean... params)
    {
        setView(new WallView(getComposite()));
    }
}
