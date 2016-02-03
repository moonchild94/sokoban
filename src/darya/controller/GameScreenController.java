package src.darya.controller;

import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

import com.google.common.collect.Maps;

import src.darya.model.Box;
import src.darya.model.CollisionObject;
import src.darya.model.Direction;
import src.darya.model.GameObject;
import src.darya.model.Home;
import src.darya.model.LevelData;
import src.darya.model.MovableObject;
import src.darya.model.Player;
import src.darya.model.Wall;

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
                move(Direction.LEFT);
                break;
            case SWT.ARROW_UP:
                move(Direction.UP);
                break;
            case SWT.ARROW_RIGHT:
                move(Direction.RIGHT);
                break;
            case SWT.ARROW_DOWN:
                move(Direction.DOWN);
                break;
            case SWT.F5:
                update();
                break;
            case SWT.ESC:
                timerTask.cancel();
                removeListeners();
                GameController.getInstance().goToScene(ScreenType.MENU);
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
            timerLabel = new Label(getComposite(), SWT.NONE);
            timerLabel.setBounds(5, 5, 100, 35);
            timerLabel.setFont(new Font(getDisplay(), "Arial", 20, SWT.NONE));
            timerLabel.setBackground(new Color(getDisplay(), new RGB(0, 0, 0), 0));
            timerLabel.setForeground(new Color(getDisplay(), new RGB(255, 255, 255)));
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
                    timerLabel.setText(String.valueOf(++levelTime));
                }
            });
        }
    }

    private LevelData levelData;
    private Map<GameObject, SimpleController> simpleControllers = Maps.newHashMap();

    private TimerTask timerTask;
    private Label levelLabel, timerLabel;
    private KeyListener keyListener;
    private long levelTime = 0;

    public GameScreenController(Display display, Composite composite)
    {
        super(display, composite);
        levelData = new LevelData();
    }

    public int getMaxLevel()
    {
        return levelData.getMaxLevel();
    }

    public int incrementMaxLevel()
    {
        return levelData.incrementMaxLevel();
    }

    public void setLevel(int level)
    {
        levelData.startLevel(level);
        levelTime = 0;
    }

    @Override
    protected void createView(boolean... isNewGame)
    {
        if (isNewGame.length == 1 && isNewGame[0])
        {
            setLevel(1);
        }

        addHelpButton();
        createSimpleViews();
        addListeners();
        createTimer();
        initLevelLabel();
        checkCompletion();
    }

    private void addHelpButton()
    {
        Button helpButton = new Button(getComposite(), SWT.PUSH);
        helpButton.setBounds(220, 5, 35, 35);
        Image helpButtonImage = new Image(getDisplay(), "./resources/question.png");
        helpButton.setImage(helpButtonImage);
        helpButton.addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                String message =
                //@formatter:off
                                    "Help:\n"
                                    + "1. Restart - F5\n"
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
        keyListener = new GameKeyListener();
        getComposite().addKeyListener(keyListener);
    }

    private boolean checkBoxCollision(Direction direction)
    {
        Player player = levelData.getGameObjects().getPlayer();
        Set<Box> boxes = levelData.getGameObjects().getBoxes();
        boolean hasCollision = false;
        Box collisionBox = null;
        for (Box box : boxes)
        {
            if (player.isCollision(box, direction))
            {
                collisionBox = box;
                break;
            }
        }

        if (collisionBox != null)
        {
            for (Box box : boxes)
            {
                if (collisionBox.isCollision(box, direction))
                {
                    hasCollision = true;
                    break;
                }
            }

            hasCollision |= checkWallCollision(collisionBox, direction);

            if (!hasCollision)
            {
                moveGameObject(collisionBox, direction);
            }
        }

        return hasCollision;
    }

    private void checkCompletion()
    {
        Set<Box> boxes = levelData.getGameObjects().getBoxes();
        Set<Home> homes = levelData.getGameObjects().getHomes();
        boolean wasCompleted = true;

        if (boxes.size() != homes.size())
        {
            return;
        }

        for (Box box : boxes)
        {
            boolean isCurrentBoxInHome = false;

            for (Home home : homes)
            {
                if (box.getX() == home.getX() && box.getY() == home.getY())
                {
                    isCurrentBoxInHome = true;
                    break;
                }
            }

            wasCompleted &= isCurrentBoxInHome;
        }

        if (wasCompleted)
        {
            completed();
        }
    }

    private boolean checkWallCollision(CollisionObject gameObject, Direction direction)
    {
        Set<Wall> walls = levelData.getGameObjects().getWalls();
        for (Wall wall : walls)
        {
            if (gameObject.isCollision(wall, direction))
            {
                return true;
            }
        }
        return false;
    }

    private void clear()
    {
        for (Map.Entry<GameObject, SimpleController> pair : simpleControllers.entrySet())
        {
            pair.getValue().getView().getComposite().dispose();
        }

        simpleControllers.clear();

        timerLabel.dispose();
    }

    private void completed()
    {
        timerTask.cancel();
        if (isLastLevel())
        {
            String message = isLastDefaultLevel() ? "Congratulations! You won!" : "An additional level completed!";
            showMessage(message);
            showMessage("Use map editor to play more level");

            removeListeners();
            GameController.getInstance().goToScene(ScreenType.MENU);
        }
        else
        {
            showMessage("Level completed!");
            clear();
            levelData.startNextLevel();
            levelTime = 0;
            updateLevelLabel();
            createTimer();
            createSimpleViews();
        }
    }

    private void createSimpleViews()
    {
        for (GameObject gameObject : levelData.getGameObjects().getAll())
        {
            SimpleController simpleController = SimpleControllerFactory.getSimpleViewController(gameObject.getClass(),
                    getDisplay(), getComposite());
            if (simpleController != null)
            {
                simpleController.getView().setCoordinates(gameObject.getX(), gameObject.getY());
                simpleControllers.put(gameObject, simpleController);
            }
        }
    }

    private void createTimer()
    {
        Timer timer = new Timer();
        timerTask = new StopwatchTask();
        timer.schedule(timerTask, 1, 1000);
    }

    private void initLevelLabel()
    {
        levelLabel = new Label(getComposite(), SWT.NONE);
        levelLabel.setBounds(110, 5, 100, 35);
        levelLabel.setFont(new Font(getDisplay(), "Arial", 20, SWT.NONE));
        levelLabel.setBackground(new Color(getDisplay(), new RGB(0, 0, 0), 0));
        levelLabel.setForeground(new Color(getDisplay(), new RGB(255, 255, 255)));
        updateLevelLabel();

    }

    private boolean isLastDefaultLevel()
    {
        return levelData.getCurrentLevel() == levelData.getMaxDefaultLevel();
    }

    private boolean isLastLevel()
    {
        return levelData.getCurrentLevel() == levelData.getMaxLevel();
    }

    private void move(Direction direction)
    {
        Player player = levelData.getGameObjects().getPlayer();

        if (checkWallCollision(player, direction))
        {
            return;
        }

        if (checkBoxCollision(direction))
        {
            return;
        }

        moveGameObject(player, direction);

        checkCompletion();
    }

    private void moveGameObject(MovableObject movableObject, Direction direction)
    {
        movableObject.move(direction);
        SimpleController simpleController = simpleControllers.get(movableObject);
        simpleController.getView().setCoordinates(movableObject.getX(), movableObject.getY());
    }

    private void removeListeners()
    {
        getComposite().removeKeyListener(keyListener);
    }

    private void showMessage(String message)
    {
        MessageBox messageBox = new MessageBox(getComposite().getShell());
        messageBox.setMessage(message);
        messageBox.open();
    }

    private void update()
    {
        timerTask.cancel();
        clear();
        setLevel(levelData.getCurrentLevel());
        createTimer();
        createSimpleViews();
    }

    private void updateLevelLabel()
    {
        String currentLevel = String.valueOf(levelData.getCurrentLevel());
        levelLabel.setText(currentLevel + " level");
    }
}
