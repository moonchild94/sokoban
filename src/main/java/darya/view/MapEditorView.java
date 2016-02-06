package darya.view;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import darya.common.Constants;
import darya.utils.ResourceHelper;

/**
 * Представление редактора карт.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public class MapEditorView implements View
{
    private Composite composite;

    private Cell[][] field;
    private Button saveButton;
    private Button helpButton;

    public MapEditorView(Composite composite)
    {
        this.composite = composite;
        init();
    }

    public Cell[][] getField()
    {
        return field;
    }

    public Button getHelpButton()
    {
        return helpButton;
    }

    public Button getSaveButton()
    {
        return saveButton;
    }

    private void addHelpButton(Composite composite)
    {
        helpButton = new Button(composite, SWT.PUSH);
        helpButton.setBounds(110, 0, 35, 35);
        InputStream helpButtonStream = ResourceHelper.getStream(Constants.DIRECTORY_COMMON_PREFIX + "/question.png");
        Image helpButtonImage = new Image(composite.getDisplay(), helpButtonStream);
        helpButton.setImage(helpButtonImage);
    }

    private void addSaveButton(Composite composite)
    {
        saveButton = new Button(composite, SWT.BORDER);
        InputStream saveStream = ResourceHelper.getStream(Constants.DIRECTORY_COMMON_PREFIX + "/saveImage.jpg");
        Image saveImage = new Image(composite.getDisplay(), saveStream);
        saveButton.setImage(saveImage);
        saveButton.setBounds(0, 0, 98, 35);
    }

    private Cell[][] createField(Composite composite)
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

    private void init()
    {
        field = createField(composite);
        addSaveButton(composite);
        addHelpButton(composite);
    }
}
