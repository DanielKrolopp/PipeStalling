
public class Loadstar extends Player {

	public Loadstar(double x, double y) {
		super(x, y);
		characterType = CharacterType.LOAD;
	}
	
	public void special(){
		usingSpecial = true;
		Mine mine = new Mine(getXPos(), getYPos());
		GuiWorld.world.addMine(mine);
	}
	
	public void render(double delta) {
		super.render(delta);
	}
}
