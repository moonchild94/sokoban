package darya.view;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import darya.common.Constants;

/**
 * Представление игрового объекта размером с ячейку игрового поля.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public abstract class SimpleView implements View
{
    private Composite smallComposite;
    private Composite composite;

    public SimpleView(Composite composite)
    {
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
        Image boxImage = new Image(composite.getDisplay(), getImageView());
        smallComposite.setBackgroundImage(boxImage);
    }

    public void setCoordinates(int x, int y)
    {
        smallComposite.setBounds(x, y, Constants.FIELD_SELL_SIZE, Constants.FIELD_SELL_SIZE);
    }

    protected abstract InputStream getImageView();
}
