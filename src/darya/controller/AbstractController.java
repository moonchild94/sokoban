package src.darya.controller;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import src.darya.view.View;

public abstract class AbstractController implements IController
{
    private Display display;
    private Composite composite;
    private View view;

    public AbstractController(Display display, Composite composite)
    {
        this.display = display;
        this.composite = composite;
    }

    public Composite getComposite()
    {
        return composite;
    }

    public Display getDisplay()
    {
        return display;
    }

    public View getView()
    {
        return view;
    }

    public void setView(View view)
    {
        this.view = view;
    }

    protected abstract void createView(boolean... params);
}
