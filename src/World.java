import java.util.ArrayList;
import java.util.List;

public class World 
{
	
	public static World generateWorld(GameSettings settings, int worldWidth, int worldHeight)
	{
		return null;
	}
	
	private List<Player> playerList;
	private List<Block> blockList;
	
	public World()
	{
		playerList = new ArrayList<Player>();
		blockList = new ArrayList<Block>();	
	}
	
<<<<<<< HEAD
	public List<Player> getPlayers(){
		return playerList;
	}
	public List<Block> getBlocks(){
		return blockList;
	}
	/*
	 * Adds a block to the world. Returns false if already in world.
	 */
	public boolean addBlock(Block block){
		if(blockList.contains(block)){
			return false;
		}
		blockList.add(block);
		return true;
	}
=======
	public void update(double delta)
	{
		
	}

>>>>>>> branch 'master' of https://github.com/DanielKrolopp/PipeStalling.git
}
