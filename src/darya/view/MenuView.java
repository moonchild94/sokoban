package src.darya.view;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.google.common.collect.Maps;

import src.darya.common.Constants;
import src.darya.controller.ScreenSwitchController;

/**
 * Представление главного меню.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public class MenuView implements View
{
    private static final int BUTTON_WIDTH = 220;
    private static final int BUTTON_HEIGHT = 77;

    private Composite composite;

    private Map<MenuElement, Button> buttons = Maps.newHashMap();

    public MenuView(Composite composite)
    {
        this.composite = composite;
        init();
    }

    public Map<MenuElement, Button> getButtons()
    {
        return buttons;
    }

    private int addContinueButton(int buttonX, int buttonY)
    {
        if (ScreenSwitchController.getInstance().isGameStarted())
        {
            Button continueButton = new Button(composite, SWT.PUSH);
            buttons.put(MenuElement.CONTINUE, continueButton);
            Image continueImage = new Image(composite.getDisplay(),
                    Constants.DIRECTORY_COMMON_PREFIX + "/continueImage.jpg");
            continueButton.setImage(continueImage);
            continueButton.setBounds(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
            buttonY += BUTTON_HEIGHT + 20;
        }
        return buttonY;
    }

    private void addExitButton(int buttonX, int buttonY)
    {
        Button exitButton = new Button(composite, SWT.PUSH);
        buttons.put(MenuElement.EXIT, exitButton);
        Image exitImage = new Image(composite.getDisplay(), Constants.DIRECTORY_COMMON_PREFIX + "/exitImage.jpg");
        exitButton.setImage(exitImage);
        exitButton.setBounds(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private int addMapEditorButton(int buttonX, int buttonY)
    {
        Button mapEditorButton = new Button(composite, SWT.PUSH);
        buttons.put(MenuElement.MAP_EDITOR, mapEditorButton);
        Image mapEditorImage = new Image(composite.getDisplay(),
                Constants.DIRECTORY_COMMON_PREFIX + "/mapEditorImage.jpg");
        mapEditorButton.setImage(mapEditorImage);
        mapEditorButton.setBounds(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonY += BUTTON_HEIGHT + 20;
        return buttonY;
    }

    private int addNewGameButton(int buttonX, int buttonY)
    {
        Button newGameButton = new Button(composite, SWT.PUSH);
        buttons.put(MenuElement.NEW_GAME, newGameButton);
        Image newGameImage = new Image(composite.getDisplay(), Constants.DIRECTORY_COMMON_PREFIX + "/newGameImage.jpg");
        newGameButton.setImage(newGameImage);
        newGameButton.setBounds(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonY += BUTTON_HEIGHT + 20;
        return buttonY;
    }

    private int addOptionsButton(int buttonX, int buttonY)
    {
        Button optionsButton = new Button(composite, SWT.PUSH);
        buttons.put(MenuElement.OPTIONS, optionsButton);
        Image optionsImage = new Image(composite.getDisplay(), Constants.DIRECTORY_COMMON_PREFIX + "/optionsImage.jpg");
        optionsButton.setImage(optionsImage);
        optionsButton.setBounds(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonY += BUTTON_HEIGHT + 20;
        return buttonY;
    }

    private void init()
    {
        int buttonX = (composite.getBounds().width - BUTTON_WIDTH) / 2;
        int buttonY = 20;

        buttonY = addNewGameButton(buttonX, buttonY);
        buttonY = addContinueButton(buttonX, buttonY);
        buttonY = addOptionsButton(buttonX, buttonY);
        buttonY = addMapEditorButton(buttonX, buttonY);
        addExitButton(buttonX, buttonY);
    }
}
