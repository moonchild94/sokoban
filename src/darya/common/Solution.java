package src.darya.common;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Solution 
{
	public static void main(String args[])
	{
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setText("Sokoban");
		GridLayout gd = new GridLayout(1, true);
		shell.setLayout(gd);
		
		Font boldFont = new Font(display, new FontData("Arial Black", 15, SWT.BOLD));
		Image image = new Image(display, "./resources/button.png");
		
		Button newGameButton = new Button(shell, SWT.PUSH);
		newGameButton.setText("Play");
		newGameButton.setImage(image);
		newGameButton.setFont(boldFont);
		newGameButton.setSize(100, 30);
		newGameButton.pack();
		
		Button continueButton = new Button(shell, SWT.PUSH);
		continueButton.setText("continue");
		continueButton.setFont(boldFont);
		continueButton.pack();
		
		Button optionsButton = new Button(shell, SWT.PUSH);
		optionsButton.setText("options");
		optionsButton.setFont(boldFont);
		optionsButton.pack();
		
		Button exitButton = new Button(shell, SWT.PUSH);
		exitButton.setText("exit");
		exitButton.setFont(boldFont);
		exitButton.pack();
		
		shell.pack();
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
