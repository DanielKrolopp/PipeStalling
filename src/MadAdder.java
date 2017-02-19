import org.joml.Vector3d;

public class MadAdder extends Player {
	
	private final int damage = 4; //Can be modified
	
	public MadAdder(double x, double y) {
		super(x, y);
		characterType = CharacterType.ADD;
	}
	
	public void special() {
		usingSpecial = true;
		Beam shoot = new Beam(this, damage);
		if(shoot.shootBeam())
			this.heal(damage/2);
	}
	
	public void render(double delta, Vector3d vec) {
		super.render(delta, vec);
	}
}
