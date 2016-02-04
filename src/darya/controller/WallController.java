package src.darya.controller;

import org.eclipse.swt.widgets.Composite;

import src.darya.view.WallView;

/**
 * Контроллер игрового объекта стены.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public class WallController extends SimpleController
{
    public WallController(Composite composite)
    {
        super(composite);
        createView();
    }

    @Override
    protected void createView()
    {
        setView(new WallView(getComposite()));
    }
}