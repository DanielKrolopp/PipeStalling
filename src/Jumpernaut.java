
public class Jumpernaut extends Player {

	
	public Jumpernaut(double x, double y) {
		super(x, y);
		characterType = "JUMP";
	}
	
	public void slam() {
		//Jumpernaut has a special slam, overrides slam in player
		if(usingSpecial) {
			//special slam
			
		
		} else {
			//regular slam
		}
	}
	
	public void special() {
		usingSpecial = true;
	}
	
	public void render(double delta) {
		
	}
}
