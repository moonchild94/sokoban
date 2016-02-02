package src.darya.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import src.darya.common.Constants;

public abstract class SimpleView implements View
{
    private Composite smallComposite;
    private Composite composite;
    private Display display;

    public SimpleView(Display display, Composite composite)
    {
        this.display = display;
        this.composite = composite;
        init();
    }

    public Composite getComposite()
    {
        return smallComposite;
    }

    public void init()
    {
        smallComposite = new Composite(composite, SWT.NONE);
        FillLayout fl = new FillLayout();
        smallComposite.setLayout(fl);
        Image boxImage = new Image(display, getImageView());
        smallComposite.setBackgroundImage(boxImage);
    }

    public void setCoordinates(int x, int y)
    {
        smallComposite.setBounds(x, y, Constants.FIELD_SELL_SIZE, Constants.FIELD_SELL_SIZE);
    }

    protected abstract String getImageView();
}