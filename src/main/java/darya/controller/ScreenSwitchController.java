package darya.controller;

import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.google.common.collect.Maps;

/**
 * Класс, отвечающий за переходы между экранами в игре.
 * @author Калмыкова Д.В.
 * @sinse 4 февр. 2016 г.
 */
public class ScreenSwitchController implements IController
{
    private static ScreenSwitchController instance;

    public static ScreenSwitchController getInstance()
    {
        if (instance == null)
        {
            instance = new ScreenSwitchController();
        }
        return instance;
    }

    private boolean isGameStarted = false;
    private Composite composite;
    private Map<ScreenType, AbstractController> screens = Maps.newHashMap();

    private ScreenSwitchController()
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
        if (screenType == ScreenType.CONTINUE)
        {
            ((GameScreenController)screens.get(screenType)).continueGame();
            return;
        }

        screens.get(screenType).createView();
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
