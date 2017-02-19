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
		if(usingSpecial)
		{
			GuiWorld.world.effectTimer.setEffect(new Vector3d(4, 0, 0));

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
		yVel = 40;
		yAcc = -1;
		
	}
	/*
	Shockwave attack = new Shockwave(player.getXPos()-player.getWidth(), player.getYPos()-player.getHeight()*0.25, player.getWidth()*3, player.getHeight()*0.5);
	List<Player> hitlist = attack.detectTargets();
	for(Player pBlock : hitlist)
	{
		if(pBlock.getCharacter() == otherPlayer.getCharacter())
			player.damage(15, pBlock);
	}
	*/
}
