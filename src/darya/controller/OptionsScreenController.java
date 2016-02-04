package src.darya.controller;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import src.darya.common.GameMusic;
import src.darya.view.OptionsView;

public class OptionsScreenController extends AbstractController
{
    private KeyListener keyListener;

    public OptionsScreenController(Display display, Composite composite)
    {
        super(display, composite);
    }

    @Override
    protected void createView(boolean... params)
    {
        OptionsView optionsView = new OptionsView(getComposite());
        setView(optionsView);
        addSelectionListener(optionsView);
        addKeyListener();
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
                    removeListeners();
                    GameController.getInstance().goToScene(ScreenType.MENU);
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0)
            {

            }
        };
        getComposite().addKeyListener(keyListener);
    }

    private void addSelectionListener(OptionsView optionsView)
    {
        Button switcher = optionsView.getSwitcher();

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

    private void removeListeners()
    {
        getComposite().removeKeyListener(keyListener);
    }
}
