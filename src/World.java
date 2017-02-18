import java.util.ArrayList;
import java.util.List;

public class World 
{
	
	private static List<Player> playerList;
	private List<Block> blockList;

	private double width;
	private double height;
	public final double ACCELERATION = -9.8;

	public World(double width, double height)
	{
		this.width = width;
		this.height = height;
		playerList = new ArrayList<Player>();
		blockList = new ArrayList<Block>();	
	}

	public void update(double delta)
	{
		for(Player player : playerList) {
			//block collisions, y-axis
			player.updateYMotion();
			if(player.getYPos() >= height) {
				player.setYPos(player.getHeight());
			} else if(player.getYPos() <= 0) {
				player.setYPos(height);
			} else {
				for(Block block : blockList) {
					if(isColliding(player, block)){
						if(player.getYVel() > 0) {
							player.setYPos(block.getYPos() - player.getHeight());
							player.land();
						} else {
							player.setYPos(block.getYPos()+block.getHeight());
							player.setYVel(0);
						}
					}
				}
				//player collisions, y-axis
				for(Player otherPlayer : playerList) {
					if(otherPlayer.getCharacter() != player.getCharacter()) 
					{
						if(isColliding(player, otherPlayer)){
							if(player.getYVel() > 0) {
								player.setYPos(otherPlayer.getYPos() - player.getHeight());
								player.land();
							} else {
								player.setYPos(otherPlayer.getYPos()+otherPlayer.getHeight());
								player.setYVel(0);
								if(player.getCharacter() == CharacterType.JUMP && player.usingSpecial)
								{
									player.damage(10, otherPlayer);
								}
							}
						}
					}
				}
			}

			//block collisions, x-axis
			player.updateXMotion();
			if(player.getXPos() >= width) {
				player.setXPos(0-player.getWidth());
			} else if (player.getXPos() <= 0) {
				player.setXPos(width);
			} else {
				for(Block block : blockList) {
					if(isColliding(player, block)) {
						if(player.getXVel() > 0) {
							player.setXPos(block.getXPos()-player.getWidth());
							player.setXVel(0);
						} else {
							player.setXPos(block.getXPos()+block.getWidth());
							player.setXVel(0);
						}
					}
				}
				//player collisions, x-axis
				for(Player otherPlayer : playerList) {
					if(otherPlayer.getCharacter() != player.getCharacter())
						if(isColliding(player, otherPlayer)) {
							if(player.getXVel() > 0) {
								player.setXPos(otherPlayer.getXPos()-player.getWidth());
								player.setXVel(0);
							} else {
								player.setXPos(otherPlayer.getXPos()+otherPlayer.getWidth());
								player.setXVel(0);
							}
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
