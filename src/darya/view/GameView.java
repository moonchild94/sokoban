package src.darya.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import src.darya.model.Box;
import src.darya.model.Player;

public class GameView implements View
{
	private Display display;
	private Shell shell;
	
	public GameView(Display display, Shell shell) 
	{
		this.display = display;
		this.shell = shell;
		init();
	}
	
	private void init()
	{
    	shell.setLayout(null);
		DrawHelper.drawGameObject(display, shell, new Box(100, 100));
		DrawHelper.drawGameObject(display, shell, new Player(70, 70));
	}
}
