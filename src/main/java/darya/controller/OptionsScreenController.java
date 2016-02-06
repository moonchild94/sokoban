package darya.controller;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import darya.common.GameMusic;
import darya.view.OptionsView;

/**
 * Контроллер экрана опций.
 * @author Калмыкова Д.В.
 * @sinse 4 февр. 2016 г.
 */
public class OptionsScreenController extends AbstractController
{
    private KeyListener keyListener;

    public OptionsScreenController(Composite composite)
    {
        super(composite);
    }

    @Override
    protected void createView()
    {
        OptionsView optionsView = new OptionsView(getComposite());
        setView(optionsView);
        addSelectionListener(optionsView);
        addKeyListener();
        addSaveListener(optionsView);
    }

    private void addKeyListener()
    {
        keyListener = new KeyListener()
        {
            @Override
            public void keyPressed(KeyEvent arg0)
            {
                if (arg0.keyCode == SWT.ESC)
                {
                    goToMenu();
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0)
            {
            }
        };
        getComposite().addKeyListener(keyListener);
    }

    private void addSaveListener(OptionsView optionsView)
    {
        Button saveButton = optionsView.getSaveButton();
        saveButton.addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                goToMenu();
            }
        });
    }

    private void addSelectionListener(OptionsView optionsView)
    {
        final Button switcher = optionsView.getSwitcher();
        switcher.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                if (switcher.getSelection())
                {
                    GameMusic.play();
                }
                else
                {
                    GameMusic.stop();
                }

                getComposite().forceFocus();
            }
        });

        if (GameMusic.isPlay())
        {
            switcher.setSelection(true);
        }
        else
        {
            switcher.setSelection(false);
        }
    }

    private void goToMenu()
    {
        getComposite().removeKeyListener(keyListener);
        ScreenSwitchController.getInstance().goToScene(ScreenType.MENU);
    }
}
