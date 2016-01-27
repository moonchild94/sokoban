import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;

public class Solution 
{
	public static void main(String args[])
	{
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setText("Sokoban");
		GridLayout gd = new GridLayout();
		shell.setLayout(gd);
		Composite composite = new Composite(shell, SWT.NONE);
		Button newGameButton = new Button(composite, SWT.PUSH);
		newGameButton.setText("play");
		Button continueButton = new Button(composite, SWT.PUSH);
		continueButton.setText("continue");
		Button optionsButton = new Button(composite, SWT.PUSH);
		optionsButton.setText("options");
		Button exitButton = new Button(composite, SWT.PUSH);
		exitButton.setText("exit");
		shell.open();
		
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
