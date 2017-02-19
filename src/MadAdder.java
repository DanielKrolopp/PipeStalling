public class MadAdder extends Player {
	
	private final int damage = 6; //Can be modified
	
	public MadAdder(double x, double y) {
		super(x, y);
		characterType = CharacterType.ADD;
	}
	
	public void special() {
		usingSpecial = true;
		Beam shoot = new Beam(this, damage, false);
		if(shoot.shootBeam())
		{
			if(health >= 110)
				return;
			if(health >= 100)
				this.heal((int)Math.round(damage/3.00));
			this.heal((int)Math.round(damage*2.00/3.00));
		}
	}
}
