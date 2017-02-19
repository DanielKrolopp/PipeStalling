import java.util.List;

import com.polaris.engine.util.MathHelper;

public class Explosion {
	
	private double radius;
	private int rays;
	private double increment;
	private int xIncr;
	private double[] distances;
	private double[] slopes;
	private double midX;
	private double midY;
	
	public Explosion(double radius, int rays, double midX, double midY, int xIncr) {
		this.radius = radius;
		this.rays = rays;
		increment = 2*(Math.PI) / rays;
		distances = new double[rays];
		slopes = new double[rays];
				
	}
	
	public void explode(Player attacker) {
		for(int i = 0; i < rays; i++) {
			slopes[i] = Math.tan(i*increment);
			distances[i] = 0;
			double x = xIncr;
			double y = slopes[i]*x+midY;
			
			Block collidedWith = lineIsColliding(x, y);
			
			while(collidedWith == null && distances[i] <= radius) {
				distances[i] = MathHelper.pythagoreon(x-midX, y-midY);
				x += xIncr;
				y = slopes[i]*x+midY;
				collidedWith = lineIsColliding(x, y);
			}
			if(collidedWith instanceof Player) {
				attacker.damage(10, (Player)collidedWith);
			}
		}
	}
	
	public void explode(int base, Player attacker) {
		for(int i = 0; i < rays; i++) {
			slopes[i] = Math.tan(i*increment);
			distances[i] = 0;
			double x = xIncr;
			double y = slopes[i]*x+midY;
			
			Block collidedWith = lineIsColliding(x, y);
			
			while(collidedWith == null && distances[i] <= radius) {
				distances[i] = MathHelper.pythagoreon(x-midX, y-midY);
				x += xIncr;
				y = slopes[i]*x+midY;
				collidedWith = lineIsColliding(x, y);
			}
			if(collidedWith instanceof Player) {
				attacker.damage(base/3, (Player)collidedWith);
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
