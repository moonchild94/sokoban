package src.darya.controller;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import src.darya.view.MenuElement;
import src.darya.view.MenuView;

/**
 * Контроллер главного меню.
 * @author Калмыкова Д.В.
 * @sinse 4 февр. 2016 г.
 */
public class MenuScreenController extends AbstractController
{
    public MenuScreenController(Composite composite)
    {
        super(composite);
    }

    @Override
    protected void createView()
    {
        MenuView menu = new MenuView(getComposite());
        setView(menu);
        Map<MenuElement, Button> buttons = menu.getButtons();

        buttons.get(MenuElement.NEW_GAME).addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                ScreenSwitchController.getInstance().goToScene(ScreenType.GAME);
            }
        });

        if (ScreenSwitchController.getInstance().isGameStarted())
        {
            buttons.get(MenuElement.CONTINUE).addListener(SWT.MouseDown, new Listener()
            {
                @Override
                public void handleEvent(Event arg0)
                {
                    ScreenSwitchController.getInstance().goToScene(ScreenType.CONTINUE);
                }
            });
        }

        buttons.get(MenuElement.OPTIONS).addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                ScreenSwitchController.getInstance().goToScene(ScreenType.OPTIONS);
            }
        });

        buttons.get(MenuElement.MAP_EDITOR).addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                ScreenSwitchController.getInstance().goToScene(ScreenType.MAP_EDITOR);
            }
        });

        buttons.get(MenuElement.EXIT).addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                ScreenSwitchController.getInstance().exit();
            }
        });
    }
}