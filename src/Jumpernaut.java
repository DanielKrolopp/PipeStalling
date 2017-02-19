import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3d;

public class Jumpernaut extends Player {

	
	public Jumpernaut(double x, double y) {
		super(x, y, CharacterType.JUMP);
		characterType = CharacterType.JUMP;
	}
	
	public void slam() {
		//Jumpernaut has a special slam, overrides slam in player
		if(!slamming && yAcc != -0.5) {
			if(usingSpecial) {
				//special slam
				yAcc = 8; //temp value;
			
			} else {
				super.slam();
			}
		}
		
	}
	
	public void land()
	{
		if(usingSpecial && slamming)
		{
			Shockwave attack = new Shockwave(xPos-size, yPos+size*0.75, size*3, size*0.5);
			List<Player> hitlist = attack.detectTargets();
			for(Player pBlock : hitlist)
			{
				this.damage(20, pBlock);
			}			
		}
		usingSpecial= false;
		super.land();
	}
	
	public void special() {
		if(usingSpecial)
			return;
		usingSpecial = true;
		jumping = true;
		jumpCount = 0;
		yVel = -15;
		yAcc = 1.5;
		
	}
	
	public void render(double delta, Vector3d vec) {
		super.render(delta, vec);
	}
}
