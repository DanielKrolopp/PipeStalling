
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
		for(double i = 0; i < (Math.PI)*2; i += Math.PI/180) {
			
		}
		
	}
	
	public void render(double delta) {
		
	}
}
