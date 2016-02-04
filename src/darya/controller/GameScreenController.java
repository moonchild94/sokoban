package src.darya.controller;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

import com.google.common.collect.Maps;

import src.darya.model.Direction;
import src.darya.model.GameObject;
import src.darya.model.LevelData;
import src.darya.view.GameView;

/**
 * Контроллер игрового экрана. 
 * @author Калмыкова Д.В.
 * @sinse 4 февр. 2016 г.
 */
public class GameScreenController extends AbstractController
{
    private class GameKeyListener implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent arg0)
        {
            switch (arg0.keyCode)
            {
            case SWT.ARROW_LEFT:
                levelLogic.move(Direction.LEFT);
                break;
            case SWT.ARROW_UP:
                levelLogic.move(Direction.UP);
                break;
            case SWT.ARROW_RIGHT:
                levelLogic.move(Direction.RIGHT);
                break;
            case SWT.ARROW_DOWN:
                levelLogic.move(Direction.DOWN);
                break;
            case SWT.F5:
                restartLevel();
                break;
            case SWT.ESC:
                timerTask.cancel();
                goToMenu();
                break;
            }
        }

        @Override
        public void keyReleased(KeyEvent arg0)
        {
        }
    }

    private class StopwatchTask extends TimerTask
    {
        {
            timerLabel = ((GameView)getView()).createTimerLabel();
            timerLabel.setText(String.valueOf(levelTime));
        }

        @Override
        public void run()
        {
            Display.getDefault().asyncExec(new Runnable()
            {
                @Override
                public void run()
                {
                    levelTime++;
                    timerLabel.setText(String.valueOf(levelTime));
                }
            });
        }
    }

    private GameLevelLogic levelLogic;
    private LevelData levelData;
    private Map<GameObject, SimpleController> levelObjectsControllers = Maps.newHashMap();

    private TimerTask timerTask;
    private Label levelLabel, timerLabel;
    private KeyListener keyListener;
    private long levelTime = 0;
    private long totalTime = 0;

    public GameScreenController(Composite composite)
    {
        super(composite);
        levelData = new LevelData();
        levelLogic = new GameLevelLogic(this, levelData);
    }

    public void clear()
    {
        for (Map.Entry<GameObject, SimpleController> pair : levelObjectsControllers.entrySet())
        {
            pair.getValue().getView().getComposite().dispose();
        }

        levelObjectsControllers.clear();
        timerLabel.dispose();
    }

    public void continueGame()
    {
        drawLevel();
    }

    public long getLevelTime()
    {
        return levelTime;
    }

    public SimpleController getObjectController(GameObject gameObject)
    {
        return levelObjectsControllers.get(gameObject);
    }

    public TimerTask getTimerTask()
    {
        return timerTask;
    }

    public long getTotalTime()
    {
        return totalTime;
    }

    public void goToMenu()
    {
        getComposite().removeKeyListener(keyListener);
        ScreenSwitchController.getInstance().goToScene(ScreenType.MENU);
    }

    public int incrementMaxLevel()
    {
        return levelData.incrementMaxLevel();
    }

    public void setLevelTime(long levelTime)
    {
        this.levelTime = levelTime;
    }

    public void setTotalTime(long totalTime)
    {
        this.totalTime = totalTime;
    }

    public void showMessage(String message)
    {
        MessageBox messageBox = new MessageBox(getComposite().getShell());
        messageBox.setMessage(message);
        messageBox.open();
    }

    protected void createGameObjectsViews()
    {
        for (GameObject gameObject : levelData.getGameObjects().getAll())
        {
            SimpleController simpleController = SimpleControllerFactory.getSimpleViewController(gameObject.getClass(),
                    getComposite().getDisplay(), getComposite());
            if (simpleController != null)
            {
                simpleController.getView().setCoordinates(gameObject.getX(), gameObject.getY());
                levelObjectsControllers.put(gameObject, simpleController);
            }
        }
    }

    protected void createTimer()
    {
        Timer timer = new Timer();
        timerTask = new StopwatchTask();
        timer.schedule(timerTask, 1, 1000);
    }

    @Override
    protected void createView()
    {
        ScreenSwitchController.getInstance().setGameStarted(true);
        setLevel(1);
        setTotalTime(0);
        drawLevel();
    }

    protected void updateLevelLabel()
    {
        String currentLevel = String.valueOf(levelData.getCurrentLevel());
        levelLabel.setText(currentLevel + " level");
    }

    private void addGameKeyListener()
    {
        keyListener = new GameKeyListener();
        getComposite().addKeyListener(keyListener);
    }

    private void addHelpButtonListener()
    {
        Button helpButton = ((GameView)getView()).getHelpButton();
        helpButton.addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                //@formatter:off
                String message =
                                    "Help:\n"
                                    + "1. Restart level - F5\n"
                                    + "2. Menu - ESC\n"
                                    + "3. Up - Arrow up\n"
                                    + "4. Down - Arrow down\n"
                                    + "5. Right - Arrow right\n"
                                    + "6. Left - Arrow left\n"
                                    + "7. Jump - Space";
                //@formatter:on
                showMessage(message);
                getComposite().forceFocus();
            }
        });
    }

    private void addListeners()
    {
        addHelpButtonListener();
        addGameKeyListener();
    }

    private void drawLevel()
    {
        setView(new GameView(getComposite()));
        createGameObjectsViews();
        addListeners();
        createTimer();
        initLevelLabel();
        levelLogic.checkCompletion(true);
    }

    private void initLevelLabel()
    {
        levelLabel = ((GameView)getView()).getLevelLabel();
        updateLevelLabel();
    }

    private void restartLevel()
    {
        timerTask.cancel();
        clear();
        setLevel(levelData.getCurrentLevel());
        createTimer();
        createGameObjectsViews();
    }

    private void setLevel(int level)
    {
        levelData.startLevel(level);
        levelTime = 0;
    }
}
