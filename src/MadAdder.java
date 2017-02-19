public class MadAdder extends Player {
	
	private final int damage = 6; //Can be modified
	
	public MadAdder(double x, double y) {
		super(x, y);
		characterType = CharacterType.ADD;
	}
	
	public void special() {
		usingSpecial = true;
		Beam shoot = new Beam(this, damage, false, 500);
		if(shoot.shootBeam())
		{
			if(health >= 125)
			{
				health = 125;
				return;
			}
			if(health >= 100)
			{
				this.heal((int)Math.round(damage*2.00/3.00));
				return;
			}
			this.heal((int)Math.round(damage));
		}
	}
}
