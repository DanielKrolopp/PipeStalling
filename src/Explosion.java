import java.util.List;

import org.lwjgl.opengl.GL11;

public class Explosion {
	
	private double radius;
	private int rays;
	private double increment;
	private double[] distances;
	private double[] slopes;
	private double midX;
	private double midY;
	private Player attacker;
	private boolean selfDamage;
	private long startTime;
	private Player collidedPlayer;
	
	private double ticks = 0;
	//explosion constructor takes midpoint of explosion, blast radius, 
	//number of rays (possible chances for someone to take damage) 
	//and x incrememnt value (the smaller, the more accurate)
	public Explosion(double radius, int rays, double midX, 
			double midY, Player attacker, boolean selfDamage) {
		this.radius = radius;
		this.rays = rays;
		this.selfDamage = selfDamage;
		this.attacker = attacker;
		this.midX = midX;
		this.midY = midY;
		increment = 2*(Math.PI) / rays;
		distances = new double[rays];
		slopes = new double[rays];
		startTimer();
		GuiWorld.world.addExplosion(this);				
	}
	
	public void startTimer() {
		startTime = System.currentTimeMillis();
	}
	
	public long getTime() {
		return System.currentTimeMillis() - startTime;
	}
	
	public void explode() {
		for(int i = 1; i <= rays; i++) {
			slopes[i-1] = Math.tan(i*increment);
			double xIncr = Math.cos(i*increment);
			double yIncr = Math.sin(i*increment);
			distances[i-1] = 0;
			double x = midX+xIncr;
			double y = midY+yIncr;
			
			int collidedWith = lineIsColliding(x, y);
			while(collidedWith == 0 && distances[i-1] <= radius) {
				distances[i-1] = calculateDistance(x, y);
				x += xIncr;
				y += yIncr;
				collidedWith = lineIsColliding(x, y);
			}
			
			if(collidedWith == 1) { //player found
				attacker.damage(+10, collidedPlayer);
			}
			collidedPlayer = null;
		}
	}
	
	public void explode(double base, double multiplier) {
		for(int i = 1; i <= rays; i++) {
			slopes[i-1] = Math.tan(i*increment);
			double xIncr = Math.cos(i*increment);
			double yIncr = Math.sin(i*increment);
			distances[i-1] = 0;
			double x = midX+xIncr;
			double y = midY+yIncr;
			
			int collidedWith = lineIsColliding(x, y);
			while(collidedWith == 0 && distances[i-1] <= radius) {
				distances[i-1] = calculateDistance(x, y);
				x += xIncr;
				y += yIncr;
				collidedWith = lineIsColliding(x, y);
			}
			
			if(collidedWith == 1) { //player found
				attacker.damage((int) (multiplier/3+base), collidedPlayer);
			}
			collidedPlayer = null;
		}
	}
	
	public int lineIsColliding(double x, double y) {
		List<Player> playersList = GuiWorld.world.getPlayers();
		List<Block> blockList = GuiWorld.world.getBlocks();
		for(Player player : playersList) {
			if(player.getCharacter() != attacker.getCharacter() || 
					selfDamage) {
				if(x > player.getXPos() && x < player.getXPos() + 
					player.getWidth() && y > player.getYPos() &&
					y < player.getYPos() + player.getHeight()) {
					collidedPlayer = player;
					return 1;
				}
			}
		}	
		for(Block block : blockList) {
			if(x > block.getXPos() && x < block.getXPos() + 
					block.getWidth() && y > block.getYPos() &&
					y < block.getYPos() + block.getHeight()) {
					return 2;
				}
		}
		return 0;
	}
	
	public double calculateDistance(double x, double y) {
		return Math.sqrt((x-midX)*(x-midX)+(y-midY)*(y-midY));
	}
	
	public void render(double delta) 
	{
		ticks += delta;
		
		GL11.glColor4f(0, 0, 0, 1);
		GL11.glBegin(GL11.GL_LINES);
		
		double mul1 = Math.max(.8 - 10 * ticks, 0);
		double mul2 = Math.max(1 - 10 * ticks, 0);
		
		for(int i = 1; i <= rays; i ++)
		{
			double rad = i*increment;
			double cos = Math.cos(rad) * distances[i-1];
			double sin = Math.sin(rad) * this.distances[i-1];

			GL11.glVertex3d(this.midX + cos * mul1, 1080 - (this.midY + sin * mul1), 0);
			GL11.glVertex3d(this.midX + cos * mul2, 1080 - (this.midY + sin * mul2), 0);
		}
		GL11.glEnd();
		
	}
}