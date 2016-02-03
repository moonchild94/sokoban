package src.darya.levelBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import src.darya.common.Constants;

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
                    Image wallImage = new Image(getDisplay(), "./resources/wallImage.jpg");
                    inner.setBackgroundImage(wallImage);
                    typeContent = TypeContent.WALL;
                    break;
                case 2:
                    Image boxImage = new Image(getDisplay(), "./resources/boxImage.jpg");
                    inner.setBackgroundImage(boxImage);
                    typeContent = TypeContent.BOX;
                    break;
                case 3:
                    Image homeImage = new Image(getDisplay(), "./resources/homeImage.png");
                    inner.setBackgroundImage(homeImage);
                    typeContent = TypeContent.HOME;
                    break;
                case 4:
                    Image playerImage = new Image(getDisplay(), "./resources/playerImage.png");
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
