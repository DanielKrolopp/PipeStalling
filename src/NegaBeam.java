
public class NegaBeam {
	
	private double xPos;
	private double yPos;
	private double xPosEnd;
	
	private boolean facingLeft;
	
	private final int damage = 4;
	
	Player shooter;
	
	public NegaBeam(Player pBlock)
	{
		xPos = pBlock.getXPos()+pBlock.getWidth()/2;
		yPos = pBlock.getYPos()+pBlock.getHeight()/2;
		facingLeft = pBlock.getFacingLeft();
		shooter = pBlock;
	}

	public void shootBeam()
	{
		double minDistance = Double.MAX_VALUE;
		Block closestVictim = null;
		//The following code is really ugly. Could be improved by creating a list of all
		//Blocks and Players and iterating over it
		if(facingLeft){ 
			for(Player player : GuiWorld.world.getPlayers()){
				double other_top = player.getYPos();
				double other_bottom = player.getYPos() + player.getHeight();
				double other_centerY = (2* player.getYPos() + player.getHeight())/2;
				double other_centerX = (2 * player.getXPos() + player.getWidth())/2; 
				if(player.getXPos() < xPos){ //Checks if victim is left of player
					if(other_top < yPos && other_bottom > yPos){ //Check y-value validity
						double distance = Math.sqrt(Math.pow((other_centerX - xPos), 2) + Math.pow((other_centerY - yPos), 2));
						if(distance < minDistance){ //If this is the closest opponent
							minDistance = distance;
							closestVictim = player;
							xPosEnd = closestVictim.getXPos() + closestVictim.getWidth();
						}
					}
				}
			}
			for(Block block : GuiWorld.world.getBlocks()){
				double other_top = block.getYPos();
				double other_bottom = block.getYPos() + block.getHeight();
				double other_centerY = (2* block.getYPos() + block.getHeight())/2;
				double other_centerX = (2 * block.getXPos() + block.getWidth())/2; 
				if(block.getXPos() < xPos){ //Checks if block is left of player
					if(other_top < yPos && other_bottom > yPos){ //Check y-value validity
						double distance = Math.sqrt(Math.pow((other_centerX - xPos), 2) + Math.pow((other_centerY - xPos), 2));
						if(distance < minDistance){ //If this is the closest block
							minDistance = distance;
							closestVictim = block;
							xPosEnd = closestVictim.getXPos() + closestVictim.getWidth();
						}
					}
				}
			}
		}
		else{
			for(Player player : GuiWorld.world.getPlayers()){
				double other_top = player.getYPos();
				double other_bottom = player.getYPos() + player.getHeight();
				double other_centerY = (2* player.getYPos() + player.getHeight())/2;
				double other_centerX = (2 * player.getXPos() + player.getWidth())/2; 
				if(player.getXPos() > xPos){ //Checks if victim is left of player
					if(other_top < yPos && other_bottom > yPos){ //Check y-value validity
						double distance = Math.sqrt(Math.pow((other_centerX - xPos), 2) + Math.pow((other_centerY - yPos), 2));
						if(distance < minDistance){ //If this is the closest opponent
							minDistance = distance;
							closestVictim = player;
							xPosEnd = closestVictim.getXPos();
						}
					}
				}
			}
			for(Block block : GuiWorld.world.getBlocks()){
				double other_top = block.getYPos();
				double other_bottom = block.getYPos() + block.getHeight();
				double other_centerY = (2* block.getYPos() + block.getHeight())/2;
				double other_centerX = (2 * block.getXPos() + block.getWidth())/2; 
				if(block.getXPos() > xPos){ //Checks if block is left of player
					if(other_top < yPos && other_bottom > yPos){ //Check y-value validity
						double distance = Math.sqrt(Math.pow((other_centerX - xPos), 2) + Math.pow((other_centerY - xPos), 2));
						if(distance < minDistance){ //If this is the closest block
							minDistance = distance;
							closestVictim = block;
							xPosEnd = closestVictim.getXPos();
						}
					}
				}
			}
		}
		if(closestVictim != null && closestVictim instanceof Player){ //Means he hit someone
			shooter.damage(damage, (Player) closestVictim); //Janky casting is my specialty	
			shooter.heal(damage/2);
		}
		else{ //Indicates a miss
			shooter.miss(damage);
		}
	
	}

}
