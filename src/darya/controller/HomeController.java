package src.darya.controller;

import org.eclipse.swt.widgets.Composite;

import src.darya.view.HomeView;

/**
 * Контроллер черной дыры.
 * @author Калмыкова Д.В.
 * @sinse 4 февр. 2016 г.
 */
public class HomeController extends SimpleController
{
    public HomeController(Composite composite)
    {
        super(composite);
        createView();
    }

    @Override
    protected void createView()
    {
        setView(new HomeView(getComposite()));
    }
}
