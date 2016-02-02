package src.darya.model;

import src.darya.common.Constants;

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
