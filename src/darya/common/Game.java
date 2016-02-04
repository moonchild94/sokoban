package src.darya.common;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import src.darya.controller.ScreenSwitchController;
import src.darya.controller.ScreenType;

/**
 * Главный класс, с которого начинается игра.
 * @author Калмыкова Д.В.
 * @sinse 4 февр. 2016 г.
 */
public class Game
{
    public static void main(String args[])
    {
        Display display = Display.getDefault();
        Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
        init(display, shell);

        shell.open();

        GameMusic.init(shell);
        GameMusic.play();

        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
            {
                display.sleep();
            }
        }

        display.dispose();
    }

    private static void init(Display display, Shell shell)
    {
        Composite composite = new Composite(shell, SWT.NONE);
        shell.setText("Sokoban");

        Image background = new Image(display, Constants.DIRECTORY_COMMON_PREFIX + "/sokobanBackground.jpg");
        Image icon = new Image(display, Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/boxImage.jpg");

        shell.setImage(icon);
        shell.setSize(800, 600);
        shell.setLayout(new FillLayout());

        composite.setBackgroundImage(background);
        composite.setSize(800, 600);

        ScreenSwitchController gameController = ScreenSwitchController.getInstance();
        gameController.init(composite);
        gameController.goToScene(ScreenType.MENU);
    }
}
