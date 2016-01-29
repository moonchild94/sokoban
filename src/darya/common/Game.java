package src.darya.common;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import src.darya.controller.GameController;
import src.darya.controller.ScreenType;

public class Game 
{
	public static void main(String args[])
	{
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		
		shell.open();
		
		shell.setText("Sokoban");
		Image background = new Image(display, "./resources/sokobanBackground.jpg");
		shell.setBackgroundImage(background);
		Image icon = new Image(display, "./resources/boxImage.jpg");
		shell.setImage(icon);
		shell.setSize(800, 600);
		
		GameController gameController = GameController.getInstance();
		gameController.init(display, shell);
		gameController.goToScene(ScreenType.MENU);

		while(!shell.isDisposed())
		{
			if(!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		
		display.dispose();
	}
}
