package src.darya.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import src.darya.model.GameObject;

public class DrawHelper 
{
	public static void drawGameObject(Display display, Shell shell, GameObject gameObject)
	{
		Composite composite = new Composite(shell, SWT.NONE);
    	
    	FillLayout fl = new FillLayout();
    	composite.setLayout(fl);
    	composite.setBounds(gameObject.getX(), gameObject.getY(), 
    			gameObject.getWidth(), gameObject.getHeight());
    	
    	Image boxImage = new Image(display, gameObject.getImageView());
    	composite.setBackgroundImage(boxImage);
	}
}
