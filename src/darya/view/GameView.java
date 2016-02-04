package src.darya.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import src.darya.common.Constants;

public class GameView implements View
{
    private Composite composite;
    private Button helpButton;
    private Label levelLabel;
    private Label timerLabel;

    public GameView(Composite composite)
    {
        this.composite = composite;
        init();
    }

    public Label createTimerLabel()
    {
        timerLabel = new Label(composite, SWT.NONE);
        timerLabel.setBounds(5, 5, 100, 35);
        timerLabel.setFont(new Font(composite.getDisplay(), "Arial", 20, SWT.NONE));
        timerLabel.setBackground(new Color(composite.getDisplay(), new RGB(0, 0, 0), 0));
        timerLabel.setForeground(new Color(composite.getDisplay(), new RGB(255, 255, 255)));

        return timerLabel;
    }

    public Button getHelpButton()
    {
        return helpButton;
    }

    public Label getLevelLabel()
    {
        return levelLabel;
    }

    public Label getTimerLabel()
    {
        return timerLabel;
    }

    private void init()
    {
        helpButton = new Button(composite, SWT.PUSH);
        helpButton.setBounds(220, 5, 35, 35);
        Image helpButtonImage = new Image(composite.getDisplay(), Constants.DIRECTORY_PREFIX + "/question.png");
        helpButton.setImage(helpButtonImage);

        levelLabel = new Label(composite, SWT.NONE);
        levelLabel.setBounds(110, 5, 100, 35);
        levelLabel.setFont(new Font(composite.getDisplay(), "Arial", 20, SWT.NONE));
        levelLabel.setBackground(new Color(composite.getDisplay(), new RGB(0, 0, 0), 0));
        levelLabel.setForeground(new Color(composite.getDisplay(), new RGB(255, 255, 255)));
    }
}
