package src.darya.controller;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import src.darya.view.MenuElement;
import src.darya.view.MenuView;

public class MenuScreenController extends AbstractController
{
    public MenuScreenController(Display display, Composite composite)
    {
        super(display, composite);
    }

    @Override
    protected void createView()
    {
        MenuView menu = new MenuView(getDisplay(), getComposite());
        Map<MenuElement, Button> buttons = menu.getButtons();

        buttons.get(MenuElement.GAME).addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                GameController.getInstance().goToScene(ScreenType.GAME);
            }
        });

        buttons.get(MenuElement.CONTINUE).addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                GameController.getInstance().goToScene(ScreenType.GAME);
            }
        });

        buttons.get(MenuElement.RATING).addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                GameController.getInstance().goToScene(ScreenType.RATING);
            }
        });

        buttons.get(MenuElement.OPTIONS).addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                GameController.getInstance().goToScene(ScreenType.OPTIONS);
            }
        });

        buttons.get(MenuElement.EXIT).addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                GameController.getInstance().exit();
                ;
            }
        });
    }
}
