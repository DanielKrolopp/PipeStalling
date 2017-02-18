
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
		Player closestVictim = null;
		if(facingLeft){
			for(Player player : GuiWorld.world.getPlayers()){
				double other_top = player.getYPos();
				double other_bottom = player.getYPos() + this.size;
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
		}
		else{
			for(Player player : GuiWorld.world.getPlayers()){
				double other_top = player.getYPos();
				double other_bottom = player.getYPos() + this.size;
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
		}
		if(closestVictim != null){ //Means he hit someone
			this.damage(SPECIAL_DAMAGE, closestVictim);
			this.heal(SPECIAL_DAMAGE);
		}
		else{ //Indicates a miss
			this.damage(SPECIAL_DAMAGE, this);
		}
	}
	
	public void render(double delta) {
		
	}
}
