
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
	protected double xPos;
	protected double yPos;
	
	protected int health;		//Maxes out at 100, no effect on size
	protected int jumpCount;
	protected static int size = 100;
	
	protected CharacterType characterType;	//Can be JUMP, ADD, LOAD, or STORE
	
	public Player(double x, double y)
	{
		super(x, y, Player.size, Player.size);
		health = 100;
		jumpCount = 2;
		xAcc = 1;
		yAcc = 0;
	}
	
	public CharacterType getCharacter()
	{
		return characterType;
	}
	
	public void setXPos(double x)
	{
		xPos = x;
	}
	
	public void setYPos(double y)
	{
		yPos = y;
	}
	
	public double getXVel()
	{
		return xVel;
	}
	
	public double getYVel()
	{
		return yVel;
	}
	
	public void setXVel(double x)
	{
		xVel = x;
	}
	
	public void setYVel(double y)
	{
		yVel = y;
	}
	
	public double getXAcc()
	{
		return xAcc;
	}
	
	public double getYAcc()
	{
		return yAcc;
	}
	
	public void updateXMotion()		//Update motion statistics as if no collision occured
	{
		xVel += xAcc;
		xPos += xVel;
	}
	
	public void updateYMotion()
	{
		yVel += yAcc;
		yPos += yVel;	
	}
	
	public void fall()
	{
		yAcc = 2;					//Sets acceleration for falling, aka gravity
		jumpCount = 1;
	}
	
	public void jump()
	{
		if(jumpCount > 0)
		{
			jumping = true;
			jumpCount--;
			yVel = -10;				//Sets velocity for normal jumping
			yAcc = 2;				//Sets acceleration for normal jumping, aka gravity
		}
	}
	
	public boolean isJumping()
	{
		return jumping;
	}
	public void slam()
	{
		if(!slamming && yAcc != -0.5)
		{
			slamming = true;
			yAcc = 5;				//Sets acceleration for slamming			
		}
	}
	
	public boolean isSlamming()
	{
		return slamming;
	}

	public void land()				//Lands character on the ground, updating necessary fields/
	{
		jumping = false;
		slamming = false;
		jumpCount = 2;
		yVel = 0;
		yAcc = 0.5;
	}
	
	public void heal(int amount){
		health += amount;
	}
	
	public void damage(int amount, Player enemy){
		enemy.health -= amount;
	}
}
