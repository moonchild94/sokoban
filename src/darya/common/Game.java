package src.darya.common;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

        FXCanvas canvas = new FXCanvas(shell, SWT.NONE);
        Scene scene = new Scene(new BorderPane());
        canvas.setScene(scene);
        File audioFile = new File("./resources/soundtrack.mp3");
        Media hit = new Media(audioFile.getAbsoluteFile().toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.setCycleCount(Integer.MAX_VALUE);
        mediaPlayer.play();

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
