
public class Bulbastore extends Player {
	
	public Bulbastore(double x, double y) {
		super(x, y);
		characterType = CharacterType.STORE;
	}
	
	public void special() {
		usingSpecial = true;
	}
	
	public void render(double delta) {
		
	}
}
