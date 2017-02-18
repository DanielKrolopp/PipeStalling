import java.util.ArrayList;
import java.util.List;

public class Shockwave {
	
	double xPos;
	double yPos;
	double height;
	double width;
	
	public Shockwave(double x, double y, double h, double w)
	{
		xPos = x;
		yPos = y;
		height = h;
		width = w;
	}
	
	public List<Player> detectTargets()		//This implementation is pretty crappy
	{
		List<Player> hitlist = new ArrayList<Player>();
		for(Player pBlock : World.getPlayers())
		{
			if(isColliding(pBlock))
			{
				hitlist.add(pBlock);
			}
				
		}
		return hitlist;
	}
	
	public boolean isColliding(Player p1) {
		return (p1.getXPos() < xPos + width &&
				   p1.getXPos() + p1.getWidth() > xPos &&
				   p1.getYPos() < yPos + height &&
				   p1.getHeight() + p1.getYPos() > yPos);
	}

}
