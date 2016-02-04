package src.darya.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.RGBA;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import src.darya.common.Constants;
import src.darya.common.GameMusic;

public class OptionsView implements View
{
    private Composite composite;
    private Button switcher;
    private Label label;
    private Button saveButton;

    public OptionsView(Composite composite)
    {
        this.composite = composite;
        init();
    }

    public Label getLabel()
    {
        return label;
    }

    public Button getSaveButton()
    {
        return saveButton;
    }

    public Button getSwitcher()
    {
        return switcher;
    }

    private void addLabel()
    {
        label = new Label(composite, SWT.NONE);
        label.setBackground(new Color(composite.getDisplay(), new RGBA(0, 0, 0, 0)));
        label.setFont(new Font(composite.getDisplay(), "Arial", 20, SWT.NONE));
        label.setForeground(new Color(composite.getDisplay(), new RGB(255, 255, 255)));
        label.setBounds(20, 10, 130, 50);
    }

    private void addMusicButton()
    {
        switcher = new Button(composite, SWT.TOGGLE);
        switcher.setBounds(160, 12, 30, 30);
        Image imageOn = new Image(composite.getDisplay(), Constants.DIRECTORY_COMMON_PREFIX + "/musicOnImage.jpg");
        Image imageOff = new Image(composite.getDisplay(), Constants.DIRECTORY_COMMON_PREFIX + "/musicOffImage.jpg");

        if (GameMusic.isPlay())
        {
            label.setText("Music on");
            switcher.setImage(imageOn);
        }
        else
        {
            label.setText("Music off");
            switcher.setImage(imageOff);
        }

        switcher.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                if (switcher.getSelection())
                {
                    switcher.setImage(imageOn);
                    label.setText("Music on");
                }
                else
                {
                    switcher.setImage(imageOff);
                    label.setText("Music off");
                }

                composite.forceFocus();
            }
        });
    }

    private void addSaveButton()
    {
        saveButton = new Button(composite, SWT.BORDER);
        Image saveImage = new Image(composite.getDisplay(), Constants.DIRECTORY_COMMON_PREFIX + "/smallExitImage.jpg");
        saveButton.setImage(saveImage);
        saveButton.setBounds(20, 60, 98, 35);
    }

    private void init()
    {
        addLabel();
        addMusicButton();
        addSaveButton();
    }
}