package src.darya.controller;

import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
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
import src.darya.view.SimpleView;

public class GameScreenController extends AbstractController
{
    private LevelData levelData;
    private Map<GameObject, AbstractController> gameObjectSimpleControllersMap = Maps.newHashMap();

    public GameScreenController(Display display, Composite composite)
    {
        super(display, composite);
        levelData = new LevelData();
    }

    public void setLevel(int level)
    {
        levelData.startLevel(level);
    }

    @Override
    protected void createView()
    {
        setLevel(1);
        createSimpleViews();
        createTimer();
        addListeners();
    }

    private void addListeners()
    {
        getComposite().addKeyListener(new KeyListener()
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
                case SWT.ESC:
                    GameController.getInstance().goToScene(ScreenType.MENU);
                    break;
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0)
            {

            }
        });
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

    private void completed()
    {
        MessageBox messageBox = new MessageBox(getComposite().getShell());
        String message = "Level completed!";
        messageBox.setMessage(message);
        messageBox.open();
    }

    private void createSimpleViews()
    {
        for (GameObject gameObject : levelData.getGameObjects().getAll())
        {
            AbstractController simpleViewController = SimpleViewControllerFactory
                    .getSimpleViewController(gameObject.getClass(), getDisplay(), getComposite());
            if (simpleViewController != null)
            {
                ((SimpleView)(simpleViewController.getView())).setCoordinates(gameObject.getX(), gameObject.getY());
                gameObjectSimpleControllersMap.put(gameObject, simpleViewController);
            }
        }
    }

    private void createTimer()
    {
        Label timerLabel = new Label(getComposite(), SWT.NONE);
        timerLabel.setBounds(5, 5, 50, 15);
        timerLabel.setBackground(new Color(getDisplay(), new RGB(0, 0, 0), 0));
        timerLabel.setForeground(new Color(getDisplay(), new RGB(255, 255, 255)));
        timerLabel.setText("0");
        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                Display.getDefault().asyncExec(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        int time = Integer.parseInt(timerLabel.getText());
                        timerLabel.setText(String.valueOf(++time));
                    }
                });
            }
        }, 1, 1000);

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
        AbstractController simpleController = gameObjectSimpleControllersMap.get(movableObject);
        ((SimpleView)(simpleController.getView())).setCoordinates(movableObject.getX(), movableObject.getY());
    }
}
