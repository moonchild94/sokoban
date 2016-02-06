package darya.common;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import darya.utils.ResourceHelper;
import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Класс для работы с музыкой в игре.
 * @author Калмыкова Д.В.
 * @sinse 4 февр. 2016 г.
 */
public class GameMusic
{
    private static MediaPlayer mediaPlayer;
    private static boolean isPlay = false;

    public static void init(Shell shell)
    {
        FXCanvas canvas = new FXCanvas(shell, SWT.NONE);
        Scene scene = new Scene(new BorderPane());
        canvas.setScene(scene);
        File audioFile = ResourceHelper.getFile(Constants.DIRECTORY_COMMON_PREFIX + "/soundtrack.mp3");
        Media hit = new Media(audioFile.getAbsoluteFile().toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.setCycleCount(Integer.MAX_VALUE);
    }

    public static boolean isPlay()
    {
        return isPlay;
    }

    public static void play()
    {
        mediaPlayer.play();
        isPlay = true;
    }

    public static void stop()
    {
        mediaPlayer.pause();
        isPlay = false;
    }
}
