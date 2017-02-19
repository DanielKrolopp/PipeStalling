
public class MadAdder extends Player {
	
	private final int SPECIAL_DAMAGE = 2; //Can be modified
	
	public MadAdder(double x, double y) {
		super(x, y);
		characterType = CharacterType.ADD;
	}
	
	public void special() {
		usingSpecial = true;
		NegaBeam shoot = new NegaBeam(this);
		shoot.shootBeam();
	}
	
	public void render(double delta) {
		
	}
}
