package darya.controller;

import org.eclipse.swt.widgets.Composite;

import darya.view.BoxView;

/**
 * Контроллер ящика
 * @author Калмыкова Д.В.
 * @sinse 4 февр. 2016 г.
 */
public class BoxController extends SimpleController
{
    public BoxController(Composite composite)
    {
        super(composite);
        createView();
    }

    @Override
    protected void createView()
    {
        setView(new BoxView(getComposite()));
    }
}
