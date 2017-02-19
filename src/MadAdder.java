public class MadAdder extends Player {
	
	private final int damage = 2; //Can be modified
	
	public MadAdder(double x, double y) {
		super(x, y);
		characterType = CharacterType.ADD;
	}
	
	public void special() {
		usingSpecial = true;
		Beam shoot = new Beam(this, damage, true);
		if(shoot.shootBeam())
		{
			if(health >= 125)
			{
				health = 125;
				return;
			}
			this.heal((int)Math.round(damage));
		}
	}
}
