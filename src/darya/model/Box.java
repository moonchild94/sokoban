package src.darya.model;

public class Box extends MovableObject
{
	private String imageView = "./resources/boxImage.jpg";
	
    public Box(int x, int y)
    {
        super(x, y);
    }
    
	@Override
	public String getImageView() 
	{
		return imageView;
	}
}
