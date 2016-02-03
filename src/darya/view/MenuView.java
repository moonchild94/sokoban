package src.darya.view;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Maps;

public class MenuView implements View
{
    private static final int BUTTON_WIDTH = 220;
    private static final int BUTTON_HEIGHT = 77;
    private Display display;

    private Composite composite;
    private Map<MenuElement, Button> buttons = Maps.newHashMap();

    public MenuView(Display display, Composite composite)
    {
        this.display = display;
        this.composite = composite;
        init();
    }

    public Map<MenuElement, Button> getButtons()
    {
        return buttons;
    }

    private void init()
    {
        composite.setLayout(null);

        Font boldFont = new Font(display, new FontData("Arial Black", 15, SWT.BOLD));
        int buttonX = (composite.getBounds().width - BUTTON_WIDTH) / 2;

        Button newGameButton = new Button(composite, SWT.PUSH);
        buttons.put(MenuElement.NEW_GAME, newGameButton);
        Image newGameImage = new Image(display, "./resources/newGameImage.jpg");
        newGameButton.setFont(boldFont);
        newGameButton.setImage(newGameImage);
        newGameButton.setBounds(buttonX, 20, BUTTON_WIDTH, BUTTON_HEIGHT);

        Button continueButton = new Button(composite, SWT.PUSH);
        buttons.put(MenuElement.CONTINUE, continueButton);
        Image continueImage = new Image(display, "./resources/continueImage.jpg");
        continueButton.setFont(boldFont);
        continueButton.setImage(continueImage);
        continueButton.setBounds(buttonX, newGameButton.getBounds().y + BUTTON_HEIGHT + 20, BUTTON_WIDTH,
                BUTTON_HEIGHT);

        Button ratingButton = new Button(composite, SWT.PUSH);
        buttons.put(MenuElement.RATING, ratingButton);
        Image ratingImage = new Image(display, "./resources/ratingImage.jpg");
        ratingButton.setFont(boldFont);
        ratingButton.setImage(ratingImage);
        ratingButton.setBounds(buttonX, continueButton.getBounds().y + BUTTON_HEIGHT + 20, BUTTON_WIDTH, BUTTON_HEIGHT);

        Button mapEditorButton = new Button(composite, SWT.PUSH);
        buttons.put(MenuElement.MAP_EDITOR, mapEditorButton);
        Image optionsImage = new Image(display, "./resources/mapEditorImage.jpg");
        mapEditorButton.setFont(boldFont);
        mapEditorButton.setImage(optionsImage);
        mapEditorButton.setBounds(buttonX, ratingButton.getBounds().y + BUTTON_HEIGHT + 20, BUTTON_WIDTH,
                BUTTON_HEIGHT);

        Button exitButton = new Button(composite, SWT.PUSH);
        buttons.put(MenuElement.EXIT, exitButton);
        Image exitImage = new Image(display, "./resources/exitImage.jpg");
        exitButton.setFont(boldFont);
        exitButton.setImage(exitImage);
        exitButton.setBounds(buttonX, mapEditorButton.getBounds().y + BUTTON_HEIGHT + 20, BUTTON_WIDTH, BUTTON_HEIGHT);
    }
}
