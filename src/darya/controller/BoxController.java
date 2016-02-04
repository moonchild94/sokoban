package src.darya.controller;

import org.eclipse.swt.widgets.Composite;

import src.darya.view.BoxView;

public class BoxController extends SimpleController
{
    public BoxController(Composite composite)
    {
        super(composite);
        createView();
    }

    @Override
    protected void createView(boolean... params)
    {
        setView(new BoxView(getComposite()));
    }
}
