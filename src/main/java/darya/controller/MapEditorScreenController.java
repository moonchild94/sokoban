package darya.controller;

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

import darya.common.Constants;
import darya.model.Box;
import darya.model.GameObjects;
import darya.model.Home;
import darya.model.LevelData;
import darya.model.Player;
import darya.model.Wall;
import darya.utils.XMLHelper;
import darya.view.Cell;
import darya.view.MapEditorView;
import darya.view.TypeContent;

/**
 * ���������� ��������� ����
 * @author ��������� �.�.
 * @sinse 4 ����. 2016 �.
 */
public class MapEditorScreenController extends AbstractController
{
    private KeyListener keyListener;

    public MapEditorScreenController(Composite composite)
    {
        super(composite);
    }

    @Override
    protected void createView()
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
                                            + "2. Add box - Two clicks\n"
                                            + "3. Add home - Three clicks\n"
                                            + "4. Add player - Four clicks\n"
                                            + "5. Clear cell - Five clicks\n"
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
                    ScreenSwitchController.getInstance().goToScene(ScreenType.MENU);
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
                    ScreenSwitchController gameController = ScreenSwitchController.getInstance();
                    GameScreenController gameScreenController = (GameScreenController)gameController
                            .getController(ScreenType.GAME);
                    int maxLevel = gameScreenController.incrementMaxLevel();

                    String pattern = "/level%d.xml";
                    File savePath = new File(
                            LevelData.additionalLevelsDirectory.getAbsolutePath() + String.format(pattern, maxLevel));
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