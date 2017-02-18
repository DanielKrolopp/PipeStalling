import java.util.ArrayList;
import java.util.List;

public class World 
{
	
	private List<Player> playerList;
	private List<Block> blockList;
	
	public World()
	{
		playerList = new ArrayList<Player>();
		blockList = new ArrayList<Block>();	
	}

}
