package darya.model;

/**
 * ����������� �������� �������.
 * @author ��������� �.�.
 * @sinse 5 ����. 2016 �.
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
