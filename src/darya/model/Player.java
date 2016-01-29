package src.darya.model;

public class Player extends MovableObject
{
	private String imageView = "./resources/playerImage.png";
	
    public Player(int x, int y)
    {
        super(x, y);
    }

	@Override
	public String getImageView() 
	{
		return imageView;
	}
}