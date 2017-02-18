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
		this.updateVelocityAcceleration();
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
	
	public void updateVelocityAcceleration(){
		for(Player player : playerList){
			double yVel = player.getYVelocity();
			double xVel = player.getXVelocity();
			double yAcc = player.getYAcceleration();
			double xAcc = player.getXAcceleration();
			double oldX = player.getX();
			double oldY = player.getY();
			
			player.setYVelocity(yVel + yAcc);
			player.setXVelocity(xVel + xAcc);
			player.setX(oldX + xVel);
			player.setY(oldY + yVel);
		}
	}
	
	public boolean isColliding(Block p1, Block p2) {
		return (p1.getX() < p2.getX() + p2.getWidth() &&
				   p1.getX() + p1.getWidth() > p2.getX() &&
				   p1.getY() < p2.getY() + p2.getHeight() &&
				   p1.getHeight() + p1.getY() > p2.getY());
	}
	
	public boolean isLowerBoundColliding(Block p1, Block p2) {
		return !(p1.getHeight() + p1.getY() > p2.getY());
	}
}
