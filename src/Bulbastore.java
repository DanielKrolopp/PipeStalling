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
		Explosion explode = new Explosion(175+damageCounter/3, 36, 
				this.getXPos() + (this.getWidth())/2, this.getYPos() + (this.getHeight())/2, 
				10);
		explode.explode(damageCounter, this);
	}
	
	public void render(double delta) {
		
	}
}
