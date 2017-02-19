
public class Mine extends Block {
	private static final double MINE_SIZE = 20;
	private static final double BLAST_RADIUS = 50;
	private static final int BLAST_STRENGTH = 25;
	
	public boolean visible;
	
	public Mine(double x, double y) {
		super(x, y, MINE_SIZE, MINE_SIZE);
		visible = true;
	}
	/*
	 * Checks if a player is touching the mine, dealing damage to 
	 * all within a designated radius. Boom. Bye bye Madadder.
 	 */
	public void detonate(){
		double centerX = (2 * this.getXPos() + MINE_SIZE)/2;
		double centerY = (2 * this.getYPos() + MINE_SIZE)/2;
		for(Player player : GuiWorld.world.getPlayers()){ //Iterates over players
			double playerCenterX = (2 * player.getXPos() + MINE_SIZE)/2;
			double playerCenterY = (2 * player.getYPos() + MINE_SIZE)/2;
			double distance = Math.sqrt(Math.pow(centerX - playerCenterX, 2) + Math.pow(centerY - playerCenterY, 2));
			if(distance <= MINE_SIZE + 5){ //If within tolerant distance of mine
				this.damage(player); //Go kaboom
				visible = false;
			}
		}
	}
	
	public void damage(Player player){ //Jordan Buckmaster please fix this for me
		/*double centerX = (2 * this.getXPos() + MINE_SIZE)/2;
		double centerY = (2 * this.getYPos() + MINE_SIZE)/2;
		for(Player player : GuiWorld.world.getPlayers()){ //Iterates over players
			double playerCenterX = (2 * player.getXPos() + MINE_SIZE)/2;
			double playerCenterY = (2 * player.getYPos() + MINE_SIZE)/2;
			double distance = Math.sqrt(Math.pow(centerX - playerCenterX, 2) + Math.pow(centerY - playerCenterY, 2));
			if(distance < BLAST_RADIUS){ //If within blast radius, damage the player
				player.damage(BLAST_STRENGTH, player);
			}
		}*/
		Explosion explode = new Explosion(BLAST_RADIUS, 8, (2 * this.getXPos() + MINE_SIZE)/2, 
				(2 * this.getYPos() + MINE_SIZE)/2, 5, player, true);
		explode.explode(BLAST_STRENGTH);
	}
	
	public void render(double delta) {
		
	}
}
