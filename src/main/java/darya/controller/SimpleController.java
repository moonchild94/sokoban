package darya.controller;

import org.eclipse.swt.widgets.Composite;

import darya.view.SimpleView;

/**
 * Абстрактный класс контроллера, содержащего отображение.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public abstract class SimpleController extends AbstractController
{
    protected SimpleView view;

    public SimpleController(Composite composite)
    {
        super(composite);
    }

    @Override
    public SimpleView getView()
    {
        return view;
    }

    public void setView(SimpleView view)
    {
        this.view = view;
    }
}