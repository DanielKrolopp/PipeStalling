
public class Jumpernaut extends Player {

	
	public Jumpernaut(double x, double y) {
		super(x, y);
		characterType = CharacterType.STORE;
	}
	
	public void slam() {
		//Jumpernaut has a special slam, overrides slam in player
		if(!slamming && yAcc != -0.5) {
			if(usingSpecial) {
				//special slam
				yAcc = 0.75; //temp value;
			
			} else {
				super.slam();
			}
		}
		
	}
	
	public void special() {
		usingSpecial = true;
	}
	
	public void render(double delta) {
		
	}
}
