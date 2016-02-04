package src.darya.model;

import src.darya.common.Constants;

/**
 * Абстрактный класс для объектов взаимодействующих с
 * другими игровыми объектами.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public abstract class CollisionObject extends GameObject
{
    public CollisionObject()
    {
    }

    public CollisionObject(int x, int y)
    {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction)
    {
        int newX = this.getX();
        int newY = this.getY();

        switch (direction)
        {
        case LEFT:
            newX -= Constants.FIELD_SELL_SIZE;
            break;
        case RIGHT:
            newX += Constants.FIELD_SELL_SIZE;
            break;
        case UP:
            newY -= Constants.FIELD_SELL_SIZE;
            break;
        case DOWN:
            newY += Constants.FIELD_SELL_SIZE;
            break;
        }

        return newX == gameObject.getX() && newY == gameObject.getY();
    }
}
