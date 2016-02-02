package src.darya.controller;

import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Maps;

public class GameController implements IController
{
    private static GameController instance;

    public static GameController getInstance()
    {
        if (instance == null)
        {
            instance = new GameController();
        }
        return instance;
    }

    private Display display;
    private Composite composite;

    private Map<ScreenType, AbstractController> screens = Maps.newHashMap();

    private GameController()
    {

    }

    public void exit()
    {
        display.dispose();
        System.exit(0);
    }

    public void goToScene(ScreenType screenType)
    {
        clear();
        screens.get(screenType).createView();
    }

    public void init(Display display, Composite composite)
    {
        this.display = display;
        this.composite = composite;

        screens.put(ScreenType.MENU, new MenuScreenController(display, composite));
        screens.put(ScreenType.GAME, new GameScreenController(display, composite));
        screens.put(ScreenType.RATING, new RatingScreenController(display, composite));
        screens.put(ScreenType.OPTIONS, new OptionsScreenController(display, composite));
    }

    private void clear()
    {
        for (Control child : composite.getChildren())
        {
            child.dispose();
        }
    }
}
