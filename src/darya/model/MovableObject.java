package src.darya.model;

public abstract class MovableObject extends CollisionObject
{
    public MovableObject(int x, int y) 
    {
		super(x, y);
	}

	public void move(Direction direction)
    {
    	switch (direction)
        {
            case LEFT:
                this.setX(this.getX() - Model.FIELD_SELL_SIZE);
                break;
            case RIGHT:
            	this.setX(this.getX() + Model.FIELD_SELL_SIZE);
                break;
            case UP:
            	this.setY(this.getY() - Model.FIELD_SELL_SIZE);
                break;
            case DOWN:
            	this.setY(this.getY() + Model.FIELD_SELL_SIZE);
                break;
        }
    }
}
