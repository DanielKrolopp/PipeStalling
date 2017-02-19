import org.joml.Vector3d;
import org.joml.Vector4d;

public class Mine extends Block {
	private static final double MINE_SIZE = 20;
	private static final double BLAST_RADIUS = 75;
	private static final int BLAST_STRENGTH = 6;
	
	public boolean visible;
	
	public Mine(double x, double y) {
		super(x, y, MINE_SIZE, MINE_SIZE);
		visible = true;
	}
	/*
	 * Checks if a player is touching the mine, dealing damage to 
	 * all within a designated radius. Boom. Bye bye Madadder.
 	 */
	
	public void detonate(Player player){	
		if(player.characterType != CharacterType.LOAD){
			GuiWorld.world.effectTimer.setEffect(new Vector4d(4, 0, 0, 1));
			damage(player); //Go kaboom
			visible = false;
			GuiWorld.world.getMines().remove(this);
		}
	}
	
	public void damage(Player player){ 
		Explosion explode = new Explosion(BLAST_RADIUS, 16, (2 * this.getXPos() + MINE_SIZE)/2, 
				(2 * this.getYPos() + MINE_SIZE)/2, player, true);
		explode.explode(3, BLAST_STRENGTH);
	}
	
	public void render(double delta, Vector3d p, double d) 
	{
		//if(visible)
		//{
			super.render(delta, p, d);
		//}
	}
}
