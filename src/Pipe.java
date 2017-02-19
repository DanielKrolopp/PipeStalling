public class Pipe extends Block{
	
	private Pipe mate;
	public boolean top; //If true, Pipe is on top, if false, is bottom Pipe
	
	public Pipe(double x, double y, double width, double height, boolean top, Pipe mate){
		super(x, y, width, height);
		this.top = top;
		this.mate = mate;
	}
	public Pipe(double x, double y, double width, double height, boolean top){
		super(x, y, width, height);
		this.top = top;
	}
	
	public Pipe getMate(){
		return mate;
	}
	
	public void setMate(Pipe pipe){
		mate = pipe;
	}
	
	public boolean isTop(){
		return top;
	}
	/*
	 * boolean arg tells whether to return the top or bottom Pipe.
	 * true: return top Pipe
	 * false: return bottom Pipe
	 */
	public static Pipe createLinkedPipes(boolean top){
		double height = 25;
		double width = 70 + (int)(Math.random() * 50);
		double x = (int) (Math.random() * (GuiWorld.world.getWidth()- width - 50)) + 25; //Ensures its not right against the wall
		double y;
		if(top){
			y = 0;
			Pipe topPipe = new Pipe(x, y, width, height, top);
			int x2 = -1;
			while(x2 < 0 || Math.abs(x2 - x) > width){ //Make sure they don't overlap x vals
				x2 = (int) (Math.random() * (GuiWorld.world.getWidth()- width - 50)) + 25;
			}
			Pipe botPipe = new Pipe(x2, GuiWorld.world.getHeight() - 25, width, height, !top, topPipe);
			topPipe.setMate(botPipe);
			return topPipe;
		}
		else{
			y = GuiWorld.world.getHeight() - 25;
			Pipe botPipe = new Pipe(x, y, width, height, top);
			int x2 = -1;
			while(x2 < 0 || Math.abs(x2 - x) > width){ //Make sure they don't overlap x vals
				x2 = (int) (Math.random() * (GuiWorld.world.getWidth()- width - 50)) + 25;
			}
			Pipe topPipe = new Pipe(x2, GuiWorld.world.getHeight() - 25, width, height, !top, botPipe);
			botPipe.setMate(topPipe);
			return botPipe;
		}
	}
}