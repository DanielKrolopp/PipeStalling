import java.util.ArrayList;
import java.util.List;

public class World 
{
	
	private List<Player> playerList;
	private List<Block> blockList;
	
	public final double ACCELERATION = -9.8;
	
	public World()
	{
		playerList = new ArrayList<Player>();
		blockList = new ArrayList<Block>();	
	}
	
	public void update(double delta)
	{
		for(Player player : playerList){
			double yVel = player.getYVelocity();
			double xVel = player.getXVelocity();
			double oldX = player.getX();
			double oldY = player.getY();
			double yAcc = player.ge
			player.setYVelocity(yVel + );
			
		}
	}
	
	public void render(double delta)
	{
		
	}
	
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
	/*
	 * Adds a player to the world. Returns false if already in world.
	 */
	public boolean addPlayer(Player player){
		if(playerList.contains(player)){
			return false;
		}
		playerList.add(player);
		return true;
	}
}
