package darya.model;

import darya.common.Constants;

/**
 * Абстрактный класс моделей способных перемещаться.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public abstract class MovableObject extends CollisionObject
{
    public MovableObject()
    {
    }

    public MovableObject(int x, int y)
    {
        super(x, y);
    }

    public void move(Direction direction)
    {
        switch (direction)
        {
        case LEFT:
            this.setX(this.getX() - Constants.FIELD_SELL_SIZE);
            break;
        case RIGHT:
            this.setX(this.getX() + Constants.FIELD_SELL_SIZE);
            break;
        case UP:
            this.setY(this.getY() - Constants.FIELD_SELL_SIZE);
            break;
        case DOWN:
            this.setY(this.getY() + Constants.FIELD_SELL_SIZE);
            break;
        }
    }
}
