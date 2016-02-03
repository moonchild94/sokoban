package src.darya.levelBuilder;

import java.io.File;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.google.common.collect.Sets;

import src.darya.common.Constants;
import src.darya.model.Box;
import src.darya.model.GameObjects;
import src.darya.model.Home;
import src.darya.model.Player;
import src.darya.model.Wall;
import src.darya.view.Cell;

public class MapRedactor
{
    private static Cell[][] field;

    public static void main(String[] args)
    {
        Display display = Display.getDefault();
        Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
        shell.setSize(800, 600);
        field = createField(shell);
        addSaveButton(shell);
        shell.open();
        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
            {
                display.sleep();
            }
        }

        display.dispose();
    }

    private static void addSaveButton(Composite composite)
    {
        Button saveButton = new Button(composite, SWT.BORDER);
        Image saveImage = new Image(composite.getDisplay(), "./resources/saveImage.jpg");
        saveButton.setImage(saveImage);
        saveButton.setBounds(0, 0, 98, 35);

        saveButton.addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                GameObjects gameObjects = save();
                XMLHelper.wrapToXML(new File("./resources/level5.xml"), gameObjects);
            }
        });
    }

    private static Cell[][] createField(Composite composite)
    {
        Cell[][] field = new Cell[19][14];
        for (int i = 0; i < field.length; i++)
        {
            for (int j = 1; j < field[i].length; j++)
            {
                field[i][j] = new Cell(composite, SWT.NONE, i, j);
            }
        }
        return field;
    }

    private static GameObjects save()
    {
        Set<Box> boxes = Sets.newHashSet();
        Set<Home> homes = Sets.newHashSet();
        Set<Wall> walls = Sets.newHashSet();
        Player player = null;

        for (int i = 0; i < field.length; i++)
        {
            for (int j = 1; j < field[i].length; j++)
            {
                TypeContent typeContent = field[i][j].getTypeContent();
                switch (typeContent)
                {
                case WALL:
                    walls.add(
                            new Wall(i * Constants.FIELD_SELL_SIZE + Constants.OFFSET, j * Constants.FIELD_SELL_SIZE));
                    break;
                case BOX:
                    boxes.add(new Box(i * Constants.FIELD_SELL_SIZE + Constants.OFFSET, j * Constants.FIELD_SELL_SIZE));
                    break;
                case HOME:
                    homes.add(
                            new Home(i * Constants.FIELD_SELL_SIZE + Constants.OFFSET, j * Constants.FIELD_SELL_SIZE));
                    break;
                case PLAYER:
                    player = new Player(i * Constants.FIELD_SELL_SIZE + Constants.OFFSET,
                            j * Constants.FIELD_SELL_SIZE);
                    break;
                case EMPTY:
                    break;
                }
            }
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}
