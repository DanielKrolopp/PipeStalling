import java.util.ArrayList;
import java.util.List;

public class World 
{
	
	private static List<Player> playerList;
	private List<Block> blockList;
	
	public final double ACCELERATION = -9.8;
	
	public World()
	{
		playerList = new ArrayList<Player>();
		blockList = new ArrayList<Block>();	
	}
	
	public void update(double delta)
	{
		for(Player player : playerList) {
			updateVelocityAcceleration(player);
			
			for(Block block : blockList) {
				if(isColliding(player, block)){
					//y-axis
					if(player.getYVel() > 0) {
						player.setYPos(block.getYPos() - player.getHeight());
						player.land();
					} else {
						player.setYPos(block.getYPos()+block.getHeight());
						player.setYVel(0);
					}
					//x-axis
					if(player.getXVel() > 0) {
						player.setXPos(block.getXPos()-player.getWidth());
						player.setXVel(0);
					} else {
						player.setXPos(block.getXPos()+block.getWidth());
						player.setXVel(0);
					}
				}
			}
		}
		
	}
	
	public void render(double delta)
	{
		
	}
	
	public static List<Player> getPlayers(){
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
	
	public void updateVelocityAcceleration(Player player){
			double yVel = player.getYVel();
			double xVel = player.getXVel();
			double yAcc = player.getYAcc();
			double xAcc = player.getXAcc();
			double oldX = player.getXPos();
			double oldY = player.getYPos();
			
			player.setYVel(yVel + yAcc);
			player.setXVel(xVel + xAcc);
			player.setX(oldX + xVel);
			player.setY(oldY + yVel);
	}
	
	public boolean isColliding(Block p1, Block p2) {
		return (p1.getXPos() < p2.getXPos() + p2.getWidth() &&
				   p1.getXPos() + p1.getWidth() > p2.getXPos() &&
				   p1.getYPos() < p2.getYPos() + p2.getHeight() &&
				   p1.getHeight() + p1.getYPos() > p2.getYPos());
	}
	
	public boolean isLowerBoundColliding(Block p1, Block p2) {
		return !(p1.getHeight() + p1.getYPos() > p2.getYPos());
	}
}
