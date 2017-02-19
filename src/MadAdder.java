
public class MadAdder extends Player {
	
	private final int SPECIAL_DAMAGE = 2; //Can be modified
	
	public MadAdder(double x, double y) {
		super(x, y);
		characterType = CharacterType.ADD;
	}
	
	public void special() {
		usingSpecial = true;
		double centerY = (2* getYPos() + size)/2;
		double centerX = (2 * getXPos() + size)/2;
		double minDistance = Double.MAX_VALUE;
		Block closestVictim = null;
		//The following code is really ugly. Could be improved by creating a list of all
		//Blocks and Players and iterating over it
		if(facingLeft){ 
			for(Player player : GuiWorld.world.getPlayers()){
				double other_top = player.getYPos();
				double other_bottom = player.getYPos() + size;
				double other_centerY = (2* player.getYPos() + size)/2;
				double other_centerX = (2 * player.getXPos() + size)/2; 
				if(player.getXPos() < centerX){ //Checks if victim is left of player
					if(other_top < centerY && other_bottom > centerY){ //Check y-value validity
						double distance = Math.sqrt(Math.pow((other_centerX - centerX), 2) + Math.pow((other_centerY - centerY), 2));
						if(distance < minDistance){ //If this is the closest opponent
							minDistance = distance;
							closestVictim = player;
						}
					}
				}
			}
			for(Block block : GuiWorld.world.getBlocks()){
				double other_top = block.getYPos();
				double other_bottom = block.getYPos() + size;
				double other_centerY = (2* block.getYPos() + size)/2;
				double other_centerX = (2 * block.getXPos() + size)/2; 
				if(block.getXPos() < centerX){ //Checks if block is left of player
					if(other_top < centerY && other_bottom > centerY){ //Check y-value validity
						double distance = Math.sqrt(Math.pow((other_centerX - centerX), 2) + Math.pow((other_centerY - centerY), 2));
						if(distance < minDistance){ //If this is the closest block
							minDistance = distance;
							closestVictim = block;
						}
					}
				}
			}
		}
		else{
			for(Player player : GuiWorld.world.getPlayers()){
				double other_top = player.getYPos();
				double other_bottom = player.getYPos() + size;
				double other_centerY = (2* player.getYPos() + size)/2;
				double other_centerX = (2 * player.getXPos() + size)/2; 
				if(player.getXPos() > centerX){ //Checks if victim is right of player
					if(other_top < centerY && other_bottom > centerY){ //Check y-value validity
						double distance = Math.sqrt(Math.pow((other_centerX - centerX), 2) + Math.pow((other_centerY - centerY), 2));
						if(distance < minDistance){ //If this is the closest opponent
							minDistance = distance;
							closestVictim = player;
						}
					}
				}
			}
			for(Block block : GuiWorld.world.getBlocks()){
				double other_top = block.getYPos();
				double other_bottom = block.getYPos() + size;
				double other_centerY = (2* block.getYPos() + size)/2;
				double other_centerX = (2 * block.getXPos() + size)/2; 
				if(block.getXPos() > centerX){ //Checks if block is right of player
					if(other_top < centerY && other_bottom > centerY){ //Check y-value validity
						double distance = Math.sqrt(Math.pow((other_centerX - centerX), 2) + Math.pow((other_centerY - centerY), 2));
						if(distance < minDistance){ //If this is the closest block
							minDistance = distance;
							closestVictim = block;
						}
					}
				}
			}
		}
		if(closestVictim != null && closestVictim instanceof Player){ //Means he hit someone
			this.damage(SPECIAL_DAMAGE, (Player) closestVictim); //Janky casting is my specialty
			this.heal(SPECIAL_DAMAGE);
		}
		else{ //Indicates a miss
			this.miss(SPECIAL_DAMAGE);
		}
	}
	
	public void render(double delta) {
		
	}
}
