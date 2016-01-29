package src.darya.controller;

import java.util.Map;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.common.collect.Maps;

public class GameController implements IController 
{
	private static GameController instance;
	
	private Display display;
	private Shell shell;
	private Map<ScreenType, AbstractController> screens = Maps.newHashMap();
	
	private GameController()
	{
		
	}
	
	public static GameController getInstance()
	{
		if (instance == null)
		{
			instance = new GameController();
		}
		return instance;
	}
	
	public void init(Display display, Shell shell)
	{
		this.display = display;
		this.shell = shell;
		
		screens.put(ScreenType.MENU, new MenuScreenController(display, shell));
		screens.put(ScreenType.GAME, new GameScreenController(display, shell));
		screens.put(ScreenType.RATING, new RatingScreenController(display, shell));
		screens.put(ScreenType.OPTIONS, new OptionsScreenController(display, shell));
	}
	
	public void goToScene(ScreenType screenType)
	{
		clear();
		screens.get(screenType).createView();
	}
	
	public void exit()
	{
		display.dispose();
		System.exit(0);
	}
	
	private void clear()
	{
		for (Control kid : shell.getChildren()) 
		{
	          kid.dispose();
	    }
	}
}
