import org.joml.Vector3d;

public class Bulbastore extends Player {
	
	private long startTime;
	private long currentTime;
	
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
		Explosion explode = new Explosion(175+damageCounter/3, 36, 
				this.getXPos() + (this.getWidth())/2, this.getYPos() + (this.getHeight())/2, 
				this, false);
		explode.explode(damageCounter);
	}
	
	public void startTimer() {
		startTime = System.currentTimeMillis();
	}
	
	public void updateTimer() {
		currentTime = System.currentTimeMillis();
		if(currentTime - startTime > 500 && usingSpecial) {
			stopSpecial();
		}
	}
}
