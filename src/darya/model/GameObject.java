package src.darya.model;

/**
 * Модель игрового объекта на уровне.
 * @author Калмыкова Д.В.
 * @sinse 5 февр. 2016 г.
 */
public abstract class GameObject
{
    private int x, y;

    public GameObject()
    {
    }

    public GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
