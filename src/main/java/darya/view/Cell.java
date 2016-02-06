package darya.view;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import darya.common.Constants;
import darya.utils.ResourceHelper;

/**
 * Отображение ячейке в редакторе карт.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public class Cell extends Composite
{
    private int i;
    private int j;
    private int count = 0;
    private TypeContent typeContent = TypeContent.EMPTY;
    private Composite inner;

    public Cell(Composite parent, int style)
    {
        super(parent, style);
    }

    public Cell(Composite parent, int style, int i, int j)
    {
        super(parent, style);
        setBounds(i * Constants.FIELD_SELL_SIZE + Constants.OFFSET, j * Constants.FIELD_SELL_SIZE,
                Constants.FIELD_SELL_SIZE, Constants.FIELD_SELL_SIZE);

        inner = new Composite(this, SWT.BORDER);
        inner.setBounds(0, 0, Constants.FIELD_SELL_SIZE, Constants.FIELD_SELL_SIZE);
        inner.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));

        this.i = i;
        this.j = j;
        addListener();
    }

    public void clear()
    {
        count = 0;
        typeContent = TypeContent.EMPTY;
        inner.setBackgroundImage(null);
    }

    public int getI()
    {
        return i;
    }

    public int getJ()
    {
        return j;
    }

    public TypeContent getTypeContent()
    {
        return typeContent;
    }

    private void addListener()
    {
        inner.addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                count++;
                switch (count)
                {
                case 1:
                    InputStream wallStream = ResourceHelper
                            .getStream(Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/wallImage.jpg");
                    Image wallImage = new Image(getDisplay(), wallStream);
                    inner.setBackgroundImage(wallImage);
                    typeContent = TypeContent.WALL;
                    break;
                case 2:
                    InputStream boxStream = ResourceHelper
                            .getStream(Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/boxImage.jpg");
                    Image boxImage = new Image(getDisplay(), boxStream);
                    inner.setBackgroundImage(boxImage);
                    typeContent = TypeContent.BOX;
                    break;
                case 3:
                    InputStream homeStream = ResourceHelper
                            .getStream(Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/homeImage.png");
                    Image homeImage = new Image(getDisplay(), homeStream);
                    inner.setBackgroundImage(homeImage);
                    typeContent = TypeContent.HOME;
                    break;
                case 4:
                    InputStream playerStream = ResourceHelper
                            .getStream(Constants.DIRECTORY_GAME_OBJECTS_PREFIX + "/playerImage.png");
                    Image playerImage = new Image(getDisplay(), playerStream);
                    inner.setBackgroundImage(playerImage);
                    typeContent = TypeContent.PLAYER;
                    break;
                case 5:
                    count = 0;
                    inner.setBackgroundImage(null);
                    typeContent = TypeContent.EMPTY;
                    break;
                }
            }
        });
    }
}
