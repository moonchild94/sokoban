package src.darya.controller;

import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

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

    private boolean isGameStarted = false;
    private Composite composite;
    private Map<ScreenType, AbstractController> screens = Maps.newHashMap();

    private GameController()
    {

    }

    public void exit()
    {
        composite.getDisplay().dispose();
        System.exit(0);
    }

    public AbstractController getController(ScreenType screenType)
    {
        return screens.get(screenType);
    }

    public void goToScene(ScreenType screenType)
    {
        clear();
        if (ScreenType.GAME.equals(screenType))
        {
            screens.get(screenType).createView(true);
        }
        else
        {
            screens.get(screenType).createView();
        }
    }

    public void init(Composite composite)
    {
        this.composite = composite;

        screens.put(ScreenType.MENU, new MenuScreenController(composite));
        screens.put(ScreenType.GAME, new GameScreenController(composite));
        screens.put(ScreenType.CONTINUE, screens.get(ScreenType.GAME));
        screens.put(ScreenType.OPTIONS, new OptionsScreenController(composite));
        screens.put(ScreenType.MAP_EDITOR, new MapEditorScreenController(composite));
    }

    public boolean isGameStarted()
    {
        return isGameStarted;
    }

    public void setGameStarted(boolean isGameStarted)
    {
        this.isGameStarted = isGameStarted;
    }

    private void clear()
    {
        for (Control child : composite.getChildren())
        {
            child.dispose();
        }
    }
}
