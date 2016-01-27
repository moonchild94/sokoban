package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class Menu extends Group
{
	public Menu(Composite parent, int style) 
	{
		super(parent, style);
		init();
	}
	
	private void init()
	{
		Button newGameButton = new Button(this, SWT.PUSH);
		newGameButton.setText("play");
		Button continueButton = new Button(this, SWT.PUSH);
		continueButton.setText("continue");
		Button optionsButton = new Button(this, SWT.PUSH);
		optionsButton.setText("options");
		Button exitButton = new Button(this, SWT.PUSH);
		exitButton.setText("exit");
	}
}
