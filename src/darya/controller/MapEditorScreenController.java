package src.darya.controller;

import java.io.File;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

import com.google.common.collect.Sets;

import src.darya.common.Constants;
import src.darya.model.Box;
import src.darya.model.GameObjects;
import src.darya.model.Home;
import src.darya.model.Player;
import src.darya.model.Wall;
import src.darya.utils.XMLHelper;
import src.darya.view.Cell;
import src.darya.view.MapEditorView;
import src.darya.view.TypeContent;

public class MapEditorScreenController extends AbstractController
{
    private KeyListener keyListener;

    public MapEditorScreenController(Composite composite)
    {
        super(composite);
    }

    @Override
    protected void createView(boolean... params)
    {
        MapEditorView mapEditorView = new MapEditorView(getComposite());
        setView(mapEditorView);
        addSaveListener(mapEditorView);
        addHelpListener(mapEditorView);
        addKeyListener();
    }

    private void addHelpListener(MapEditorView mapEditorView)
    {
        Button helpButton = mapEditorView.getHelpButton();
        helpButton.addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                String message =
                //@formatter:off
                                            "Help:\n"
                                            + "1. Add wall - One click\n"
                                            + "2. Add box - Two click\n"
                                            + "3. Add home - Three click\n"
                                            + "4. Add player - Four click\n"
                                            + "5. Clear cell - Five click\n"
                                            + "6. Menu - ESC";
               //@formatter:on

                showMessage(message);

                getComposite().forceFocus();
            }
        });
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

    private void addSaveListener(MapEditorView mapEditorView)
    {
        Button saveButton = mapEditorView.getSaveButton();
        saveButton.addListener(SWT.MouseDown, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                String message;
                GameObjects gameObjects = getElements();

                if (isConfigurationCorrect(gameObjects))
                {
                    GameController gameController = GameController.getInstance();
                    GameScreenController gameScreenController = (GameScreenController)gameController
                            .getController(ScreenType.GAME);
                    int maxLevel = gameScreenController.incrementMaxLevel();

                    String pattern = "/level%d.xml";
                    File savePath = new File(Constants.DIRECTORY_PREFIX + String.format(pattern, maxLevel));
                    XMLHelper.wrapToXML(savePath, gameObjects);

                    clear();

                    message = "Saved!";
                }
                else
                {
                    message =
                    //@formatter:off
                                          "Incorrect configuration!\n"
                                        + "You need:\n"
                                        + "1. One player\n"
                                        + "2. At least one home\n"
                                        + "3. At least one box\n"
                                        + "4. The same number of homes and boxes";
                   //@formatter:on

                }

                showMessage(message);
                getComposite().forceFocus();
            }
        });
    }

    private void clear()
    {
        Cell[][] field = ((MapEditorView)getView()).getField();
        for (int i = 0; i < field.length; i++)
        {
            for (int j = 1; j < field[i].length; j++)
            {
                field[i][j].clear();
            }
        }
    }

    private GameObjects getElements()
    {
        Cell[][] field = ((MapEditorView)getView()).getField();

        Set<Box> boxes = Sets.newHashSet();
        Set<Home> homes = Sets.newHashSet();
        Set<Wall> walls = Sets.newHashSet();
        Player player = null;

        for (int i = 0; i < field.length; i++)
        {
            for (int j = 1; j < field[i].length; j++)
            {
                TypeContent typeContent = field[i][j].getTypeContent();
                switch (typeContent)
                {
                case WALL:
                    walls.add(
                            new Wall(i * Constants.FIELD_SELL_SIZE + Constants.OFFSET, j * Constants.FIELD_SELL_SIZE));
                    break;
                case BOX:
                    boxes.add(new Box(i * Constants.FIELD_SELL_SIZE + Constants.OFFSET, j * Constants.FIELD_SELL_SIZE));
                    break;
                case HOME:
                    homes.add(
                            new Home(i * Constants.FIELD_SELL_SIZE + Constants.OFFSET, j * Constants.FIELD_SELL_SIZE));
                    break;
                case PLAYER:
                    player = new Player(i * Constants.FIELD_SELL_SIZE + Constants.OFFSET,
                            j * Constants.FIELD_SELL_SIZE);
                    break;
                case EMPTY:
                    break;
                }
            }
        }

        return new GameObjects(walls, boxes, homes, player);
    }

    private boolean isConfigurationCorrect(GameObjects gameObjects)
    {
        Set<Box> boxes = gameObjects.getBoxes();
        Set<Home> homes = gameObjects.getHomes();
        Player player = gameObjects.getPlayer();

        return boxes.size() != 0 && homes.size() != 0 && boxes.size() == homes.size() && player != null;
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
}
