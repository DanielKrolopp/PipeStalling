import java.util.ArrayList;
import java.util.List;

import com.polaris.engine.util.MathHelper;

public class Bulbastore extends Player {
	
	public Bulbastore(double x, double y) {
		super(x, y);
		characterType = CharacterType.STORE;
	}
	
	public void special() {
		if(usingSpecial) {
			return;
		}
		usingSpecial = true;
		immuneToDamage = true;
		damageCounter = 0;
		
	}
	
	public void stopSpecial() {
		usingSpecial = false;
		immuneToDamage = false;
		explode();
	}
	
	public void explode() {
		for(double i = 0; i < (Math.PI)*2; i += Math.PI/18) {
			double slope = Math.tan(i);
			double midX = this.getXPos() + (this.getWidth())/2;
			double midY = this.getYPos() + (this.getHeight())/2;
			double radius = 175 + damageCounter/3;
			double distance = 0;
			double xIncr = 10;
			double x = 10;
			double y = slope*x+midY;
			
			Block collidedWith = lineIsColliding(x, y);
			
			while(collidedWith == null && distance <= radius) {
				distance = MathHelper.pythagoreon(x-midX, y-midY);
				x += xIncr;
				y = slope*x+midY;
				collidedWith = lineIsColliding(x, y);
			}
			if(collidedWith instanceof Player) {
				this.damage(damageCounter/3, (Player)collidedWith);
			}
		}
		
	}
	
	public Block lineIsColliding(double x, double y) {
		List<Player> playersList = GuiWorld.world.getPlayers();
		List<Block> blockList = GuiWorld.world.getBlocks();
		for(Player player : playersList) {
			if(player.getCharacter() != CharacterType.STORE) {
				if(x > player.getXPos() && x < player.getXPos() + 
					player.getWidth() && y > player.getYPos() &&
					y < player.getYPos() + player.getHeight()) {
					return player;
				}
			}
		}	
		for(Block block : blockList) {
			if(x > block.getXPos() && x < block.getXPos() + 
					block.getWidth() && y > block.getYPos() &&
					y < block.getYPos() + block.getHeight()) {
					return block;
				}
		}
		return null;
	}
	
	public void render(double delta) {
		
	}
}
