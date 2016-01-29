package src.darya.controller;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractController implements IController
{
	private Display display;
	private Shell shell;
	
	public AbstractController(Display display, Shell shell) 
	{
		this.display = display;
		this.shell = shell;
	}
	
	public Display getDisplay() 
	{
		return display;
	}
	
	public Shell getShell() 
	{
		return shell;
	}
	
	protected abstract void createView();
}
