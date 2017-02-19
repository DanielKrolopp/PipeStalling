import java.util.List;

import org.joml.Vector3d;

public class Jumpernaut extends Player {

	
	public Jumpernaut(double x, double y) {
		super(x, y);
		characterType = CharacterType.JUMP;
	}
	
	public void slam() {
		//Jumpernaut has a special slam, overrides slam in player
		if(!slamming && yAcc != -0.5) {
			if(usingSpecial) {
				//special slam
				slamming = true;
				yAcc = -16; //temp value;
			
			} else {
				super.slam();
			}
		}
		
	}
	
	public void land()
	{
		usingSpecial= false;
		super.land();
	}
	
	public void special() {
		if(usingSpecial)
			return;
		usingSpecial = true;
		jumping = true;
		jumpCount = 0;
		yVel = 40;
		yAcc = -1;
		
	}
	
	public void render(double delta, Vector3d vec) {
		super.render(delta, vec);
	}
}
