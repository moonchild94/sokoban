package darya.view;

import java.io.InputStream;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.google.common.collect.Maps;

import darya.common.Constants;
import darya.controller.ScreenSwitchController;
import darya.utils.ResourceHelper;

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
            InputStream continueStream = ResourceHelper
                    .getStream(Constants.DIRECTORY_COMMON_PREFIX + "/continueImage.jpg");
            Image continueImage = new Image(composite.getDisplay(), continueStream);
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
        InputStream exitStream = ResourceHelper.getStream(Constants.DIRECTORY_COMMON_PREFIX + "/exitImage.jpg");
        Image exitImage = new Image(composite.getDisplay(), exitStream);
        exitButton.setImage(exitImage);
        exitButton.setBounds(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private int addMapEditorButton(int buttonX, int buttonY)
    {
        Button mapEditorButton = new Button(composite, SWT.PUSH);
        buttons.put(MenuElement.MAP_EDITOR, mapEditorButton);
        InputStream mapEditorStream = ResourceHelper
                .getStream(Constants.DIRECTORY_COMMON_PREFIX + "/mapEditorImage.jpg");
        Image mapEditorImage = new Image(composite.getDisplay(), mapEditorStream);
        mapEditorButton.setImage(mapEditorImage);
        mapEditorButton.setBounds(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonY += BUTTON_HEIGHT + 20;
        return buttonY;
    }

    private int addNewGameButton(int buttonX, int buttonY)
    {
        Button newGameButton = new Button(composite, SWT.PUSH);
        buttons.put(MenuElement.NEW_GAME, newGameButton);
        InputStream newGameStream = ResourceHelper.getStream(Constants.DIRECTORY_COMMON_PREFIX + "/newGameImage.jpg");
        Image newGameImage = new Image(composite.getDisplay(), newGameStream);
        newGameButton.setImage(newGameImage);
        newGameButton.setBounds(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonY += BUTTON_HEIGHT + 20;
        return buttonY;
    }

    private int addOptionsButton(int buttonX, int buttonY)
    {
        Button optionsButton = new Button(composite, SWT.PUSH);
        buttons.put(MenuElement.OPTIONS, optionsButton);
        InputStream optionsStream = ResourceHelper.getStream(Constants.DIRECTORY_COMMON_PREFIX + "/optionsImage.jpg");
        Image optionsImage = new Image(composite.getDisplay(), optionsStream);
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
