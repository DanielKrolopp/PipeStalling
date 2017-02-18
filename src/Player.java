
public class Player extends Block
{
	protected boolean jumping;
	protected boolean slamming;
	protected boolean dashing;
	protected boolean usingBeam;
	protected boolean usingSpecial;	//State to determine if the unique ability of character is used
	
	protected double xVel;
	protected double yVel;
	protected double xAcc;
	protected double yAcc;
	
	protected int healthCount;		//Maxes out at 100, no effect on size
	protected int jumpCount;
	protected static int size = 100;
	
	protected CharacterType characterType;	//Can be JUMP, ADD, LOAD, or STORE
	
	public Player(double x, double y)
	{
		super(x, y, Player.size, Player.size);
		healthCount = 100;
		jumpCount = 2;
		xAcc = 1;
		yAcc = 0;
	}
	
	public void jump()
	{
		if(jumpCount > 0)
		{
			jumping = true;
			yVel = 10;				//Sets velocity for normal jumping
			yAcc = -2;				//Sets acceleration for normal jumping, aka gravity
			jumpCount--;
		}
	}

	public void stopJumping()
	{
		jumping = false;
		jumpCount = 2;
		yVel = 0;
		yAcc = 0;
	}
	
	public void slam()
	{
		if(!slamming && yAcc != 0)
		{
			yAcc = -5;				//Sets acceleration for slamming
		}
	}
	
	public boolean isSlamming()
	{
		return slamming;
	}
	
	public void stopSlam()
	{
		slamming = false;
		yVel = 0;
	}
	
	public void incrementHealth(int amount){
		healthCount += amount;
	}
	
	public void decrementHealth(int amount){
		healthCount -= amount;
	}
	
	public double getXVelocity(){
		return xVel;
	}
	
	public double getYVelocity(){
		return yVel;
	}
	
	public double getXAcceleration(){
		return xAcc;
	}
	
	public double getYAcceleartion(){
		return yAcc;
	}
	
	public void setXVelocity(double amount){
		xVel = amount;
	}
	
	public void setYVelocity(double amount){
		yVel = amount;
	}
}
