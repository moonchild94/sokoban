package src.darya.controller;

import java.util.Set;

import src.darya.model.Box;
import src.darya.model.CollisionObject;
import src.darya.model.Direction;
import src.darya.model.Home;
import src.darya.model.LevelData;
import src.darya.model.MovableObject;
import src.darya.model.Player;
import src.darya.model.Wall;

/**
 * Игровая логика сокобана.
 * @author Калмыкова Д.В.
 * @sinse 4 февр. 2016 г.
 */
public class GameLevelLogic
{
    private GameScreenController gameScreen;
    private LevelData levelData;

    public GameLevelLogic(GameScreenController gameScreen, LevelData levelData)
    {
        this.levelData = levelData;
        this.gameScreen = gameScreen;
    }

    public void checkCompletion(boolean isContinue)
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
            completed(isContinue);
        }
    }

    public void move(Direction direction)
    {
        Player player = levelData.getGameObjects().getPlayer();

        if (checkLevelBounds(player, direction))
        {
            return;
        }

        if (checkWallCollision(player, direction))
        {
            return;
        }

        if (checkBoxCollision(direction))
        {
            return;
        }

        moveGameObject(player, direction);

        checkCompletion(false);
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
            hasCollision |= checkLevelBounds(collisionBox, direction);

            if (!hasCollision)
            {
                moveGameObject(collisionBox, direction);
            }
        }

        return hasCollision;
    }

    private boolean checkLevelBounds(MovableObject movableObject, Direction direction)
    {
        movableObject.move(direction);
        int newX = movableObject.getX();
        int newY = movableObject.getY();

        Direction revert = direction.revert();
        movableObject.move(revert);

        if (newX <= 0 || newX >= 760 || newY <= 0 || newY >= 560)
        {
            return true;
        }
        return false;
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

    private void completed(boolean isContinue)
    {
        gameScreen.getTimerTask().cancel();
        String message = "Level completed!\nYou needed " + gameScreen.getLevelTime() + " sec to do it.";
        gameScreen.showMessage(message);

        if (isLastDefaultLevel())
        {
            if (!isContinue)
            {
                gameScreen.setTotalTime(gameScreen.getTotalTime() + gameScreen.getLevelTime());
            }
            message = "Congratulations! All default levels completed!\n" + "You needed " + gameScreen.getTotalTime()
                    + " sec to do it.";
            gameScreen.showMessage(message);
        }

        if (isLastLevel())
        {
            gameScreen.showMessage("Use map editor to play more level.");
            gameScreen.goToMenu();
        }
        else
        {
            gameScreen.clear();
            levelData.startNextLevel();
            gameScreen.setTotalTime(gameScreen.getTotalTime() + gameScreen.getLevelTime());
            gameScreen.setLevelTime(0);
            gameScreen.updateLevelLabel();
            gameScreen.createTimer();
            gameScreen.createGameObjectsViews();
        }
    }

    private boolean isLastDefaultLevel()
    {
        return levelData.getCurrentLevel() == levelData.getMaxDefaultLevel();
    }

    private boolean isLastLevel()
    {
        return levelData.getCurrentLevel() == levelData.getMaxLevel();
    }

    private void moveGameObject(MovableObject movableObject, Direction direction)
    {
        movableObject.move(direction);
        SimpleController simpleController = gameScreen.getObjectController(movableObject);
        simpleController.getView().setCoordinates(movableObject.getX(), movableObject.getY());
    }
}
