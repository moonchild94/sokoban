package src.darya.controller;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import src.darya.view.GameView;

public class GameScreenController extends AbstractController
{
	public GameScreenController(Display display, Shell shell) 
	{
		super(display, shell);
	}

	@Override
	protected void createView() 
	{
		new GameView(getDisplay(), getShell());
		
    	getShell().addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) 
			{
				switch (arg0.keyCode)
	            {
	                case SWT.ARROW_LEFT:
	                    break;
	                case SWT.ARROW_UP:
	                    break;
	                case SWT.ARROW_RIGHT:
	                    break;
	                case SWT.ARROW_DOWN:
	                    break;
	                case SWT.ESC:
	                	GameController.getInstance().goToScene(ScreenType.MENU);
	                    break;
	            }
			}
		});
	}

}
