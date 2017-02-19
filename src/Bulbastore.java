import org.joml.Vector3d;

public class Bulbastore extends Player {
	
	private long startTime;
	private long currentTime;
	
	public Bulbastore(double x, double y) {
		super(x, y, CharacterType.STORE);
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
		Explosion explode = new Explosion(175+damageCounter/3, 36, 
				this.getXPos() + (this.getWidth())/2, this.getYPos() + (this.getHeight())/2, 
				10, this, false);
		explode.explode(damageCounter);
	}
	
	public void startTimer() {
		startTime = System.currentTimeMillis();
	}
	
	public void updateTimer() {
		currentTime = System.currentTimeMillis();
		if(currentTime - startTime > 4000 && usingSpecial) {
			stopSpecial();
		}
	}
	
	public void render(double delta, Vector3d vec) {
		super.render(delta, vec);
	}
}
