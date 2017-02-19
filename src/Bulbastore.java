import org.joml.Vector3d;
import org.joml.Vector4d;

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
				this.getXPos() + (this.getWidth())/2, this.getYPos() + (this.getHeight())/2, this, false);
		explode.explode(10, damageCounter);
		GuiWorld.world.effectTimer.setEffect(new Vector3d(0, 8, 0));
		explode.explode(5, damageCounter);
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
}
