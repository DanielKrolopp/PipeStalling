
public class Player
{
	private boolean isJumping;
	private boolean isSlamming;
	private boolean isDashing;
	private boolean isUsingBeam;
	private boolean isUsingSpecial;	//State to determine if the unique ability of character is used
	
	private double xPos;
	private double yPos;
	private double xVel;
	private double yVel;
	
	private int healthCount;		//Maxes out at 100, no effect on size
	
	protected String characterType;	//Can be JUMP, ADD, LOAD, or STORE
	
	public Player(double x, double y)
	{
		xPos = x;
		yPos = y;
		healthCount = 100;
	}
	
	public void slam()
	{
		
	}
	
	public void stopSlam()
	{
		isSlamming = false;
		yVel = 0;
	}
	
	public void computeGravity(){
		
	}
}
