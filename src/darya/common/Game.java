package src.darya.common;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import src.darya.controller.GameController;
import src.darya.controller.ScreenType;

public class Game
{
    public static void main(String args[])
    {
        Display display = Display.getDefault();
        Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);

        Composite composite = new Composite(shell, SWT.NONE);
        shell.setText("Sokoban");
        Image background = new Image(display, "./resources/sokobanBackground.jpg");
        composite.setBackgroundImage(background);
        Image icon = new Image(display, "./resources/boxImage.jpg");
        shell.setImage(icon);
        shell.setSize(800, 600);
        shell.setLayout(new FillLayout());
        composite.setSize(800, 600);

        GameController gameController = GameController.getInstance();
        gameController.init(display, composite);
        gameController.goToScene(ScreenType.MENU);

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
}
