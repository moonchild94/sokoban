package darya.controller;

import org.eclipse.swt.widgets.Composite;

import darya.view.PlayerView;

/**
 * Контроллер игрока на карте.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public class PlayerController extends SimpleController
{
    public PlayerController(Composite composite)
    {
        super(composite);
        createView();
    }

    @Override
    protected void createView()
    {
        setView(new PlayerView(getComposite()));
    }
}
