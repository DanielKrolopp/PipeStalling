
public class Loadstar extends Player {

	public Loadstar(double x, double y) {
		super(x, y);
		characterType = "LOAD";
	}
	
	public void special(){
		usingSpecial = true;
	}
	
	public void render(double delta) {
		
	}
}
