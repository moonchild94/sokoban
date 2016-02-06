package darya.model;

/**
 * Направление движения объекта.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public enum Direction
{
    LEFT, RIGHT, UP, DOWN;

    public Direction revert()
    {
        switch (this)
        {
        case DOWN:
            return UP;
        case LEFT:
            return RIGHT;
        case RIGHT:
            return LEFT;
        case UP:
            return DOWN;
        }
        return this;
    }
}
