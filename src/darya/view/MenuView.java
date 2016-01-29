package src.darya.view;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.common.collect.Maps;

public class MenuView implements View
{
	private Display display;
	private Shell shell;
	private Map<MenuElement, Button> buttons = Maps.newHashMap();
	
	private static final int BUTTON_WIDTH = 220;
	private static final int BUTTON_HEIGHT = 77;
	
	public MenuView(Display display, Shell shell) 
	{
		this.display = display;
		this.shell = shell;
		init();
	}
	
	public Map<MenuElement, Button> getButtons() 
	{
		return buttons;
	}
	
	private void init()
	{
		shell.setLayout(null);
		
		Font boldFont = new Font(display, new FontData("Arial Black", 15, SWT.BOLD));
		int buttonX = (shell.getBounds().width - BUTTON_WIDTH) / 2;
		
		Button newGameButton = new Button(shell, SWT.PUSH);
		buttons.put(MenuElement.GAME, newGameButton);
		Image newGameImage = new Image(display, "./resources/newGameImage.jpg");
		newGameButton.setFont(boldFont);
		newGameButton.setImage(newGameImage);
		newGameButton.setBounds(buttonX, 20, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		Button continueButton = new Button(shell, SWT.PUSH);
		buttons.put(MenuElement.CONTINUE, continueButton);
		Image continueImage = new Image(display, "./resources/continueImage.jpg");
		continueButton.setFont(boldFont);
		continueButton.setImage(continueImage);
		continueButton.setBounds(buttonX, newGameButton.getBounds().y + BUTTON_HEIGHT + 20, 
				BUTTON_WIDTH, BUTTON_HEIGHT);
		
		Button ratingButton = new Button(shell, SWT.PUSH);
		buttons.put(MenuElement.RATING, ratingButton);
		Image ratingImage = new Image(display, "./resources/ratingImage.jpg");
		ratingButton.setFont(boldFont);
		ratingButton.setImage(ratingImage);
		ratingButton.setBounds(buttonX, continueButton.getBounds().y + BUTTON_HEIGHT + 20, 
				BUTTON_WIDTH, BUTTON_HEIGHT);
		
		Button optionsButton = new Button(shell, SWT.PUSH);
		buttons.put(MenuElement.OPTIONS, optionsButton);
		Image optionsImage = new Image(display, "./resources/optionsImage.jpg");
		optionsButton.setFont(boldFont);
		optionsButton.setImage(optionsImage);
		optionsButton.setBounds(buttonX, ratingButton.getBounds().y + BUTTON_HEIGHT + 20, 
				BUTTON_WIDTH, BUTTON_HEIGHT);
		
		Button exitButton = new Button(shell, SWT.PUSH);
		buttons.put(MenuElement.EXIT, exitButton);
		Image exitImage = new Image(display, "./resources/exitImage.jpg");
		exitButton.setFont(boldFont);
		exitButton.setImage(exitImage);
		exitButton.setBounds(buttonX, optionsButton.getBounds().y + BUTTON_HEIGHT + 20, 
				BUTTON_WIDTH, BUTTON_HEIGHT);
	}
}
