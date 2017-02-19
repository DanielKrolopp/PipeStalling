import org.joml.Vector3d;

public class Pipe extends Block{
	
	private Pipe mate;
	public boolean top; //If true, Pipe is on top, if false, is bottom Pipe
	
	//Each pipe contains a reference to its linked pipe (mate)
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
	public static Pipe createLinkedPipes(boolean top, World world){
		double height = 30;
		double width = Player.size + 20; //Pipe openings will only be 20 wider than player
		double x = (int) (Math.random() * (world.getWidth()- width - 50)) + 25; //Ensures its not right against the wall
		double y;
		if(top){
			y = 0;
			Pipe topPipe = new Pipe(x, y, width, height, top);
			int x2 = -1;
			while(x2 < 0 || Math.abs(x2 - x) < width){ //Make sure they don't overlap x vals
				x2 = (int) (Math.random() * (world.getWidth() - width - 50)) + 25;
			}
			Pipe botPipe = new Pipe(x2, world.getHeight() - 25, width, height, !top, topPipe);
			topPipe.setMate(botPipe);
			return topPipe;
		}
		else{
			y = world.getHeight() - 25;
			Pipe botPipe = new Pipe(x, y, width, height, top);
			int x2 = -1;
			while(x2 < 0 || Math.abs(x2 - x) < width){ //Make sure they don't overlap x vals
				x2 = (int) (Math.random() * (world.getWidth() - width - 50)) + 25;
			}
			Pipe topPipe = new Pipe(x2, world.getHeight() - 25, width, height, !top, botPipe);
			botPipe.setMate(topPipe);
			return botPipe;
		}
	}
	/*
	 * Returns the x position the player will pop out of.
	 * Will spawn player's center in the center of the pipe (like so):
	 * ----------------------------------
	 * -                                -
	 * ----------------------------------
	 *              XXXXXXXX
	 *              XXXXXXXX
	 *              XXXXXXXX
	 *              XXXXXXXX
	 */
	public double getSpawnX(){
		return getXPos() + (width/2) - (Player.size/2);
	}
	/*
	 * Returns the y position the player will pop out of
	 */
	public double getSpawnY(){
		if(!top){
			return getYPos();
		}
		else{
			return getYPos() - height;
		}
	}
}