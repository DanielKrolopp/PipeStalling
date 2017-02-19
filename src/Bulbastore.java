import org.joml.Vector3d;

public class Bulbastore extends Player {
	
	private long startTime;
	private long currentTime;
	private long cooldownStart;
	private boolean cooling;
	
	public Bulbastore(double x, double y) {
		super(x, y);
		characterType = CharacterType.STORE;
	}
	
	public void special() {
		if(usingSpecial || cooling) {
			return;
		}
		usingSpecial = true;
		immuneToDamage = true;
		damageCounter = 0;
		
	}
	
	public void stopSpecial() {
		usingSpecial = false;
		immuneToDamage = false;
		Explosion explode = new Explosion(175+damageCounter/3, 36, 
				this.getXPos() + (this.getWidth())/2, this.getYPos() + (this.getHeight())/2, this, false);
		explode.explode(10, damageCounter);
		GuiWorld.world.effectTimer.setEffect(new Vector3d(0, 8, 0));
		explode.explode(5, damageCounter);
		startCooldown();
		cooling = true;
	}
	
	public void startCooldown() {
		cooldownStart = System.currentTimeMillis();
	}
	
	public void updateCooldown() {
		if(System.currentTimeMillis() - cooldownStart > 3000) {
			cooling = false;
		}
	}
	
	public void startTimer() {
		startTime = System.currentTimeMillis();
	}
	
	public void updateTimer() {
		currentTime = System.currentTimeMillis();
		if(currentTime - startTime > 2000 && usingSpecial) {
			stopSpecial();
		}
	}
	
	public void render(double delta, Vector3d vec, Vector3d rotate)
	{
		super.render(delta, usingSpecial && currentTime % 150 < 75 ? new Vector3d(1) : vec, rotate);
		
		
	}
}
