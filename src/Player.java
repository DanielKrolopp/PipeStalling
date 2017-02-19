import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3d;
import org.lwjgl.opengl.GL11;

import com.polaris.engine.util.MathHelper;

public class Player extends Block
{
	protected boolean jumping;
	protected boolean slamming;
	protected boolean dashing;
	protected boolean usingBeam;
	protected boolean facingLeft;
	protected boolean usingSpecial;	//State to determine if the unique ability of character is used
	protected boolean immuneToDamage;
	protected boolean alive;
	
	protected double xVel;
	protected double yVel;
	protected double xAcc;
	protected double yAcc;
	
	protected long jumpTime;
	
	protected int health;		//Maxes out at 100, no effect on size
	protected int jumpCount;
	protected int damageCounter;
	protected static int size = 100;
	
	private double ticks = 0;
	
	protected CharacterType characterType;	//Can be JUMP, ADD, LOAD, or STORE
	
	public Player(double x, double y)
	{
		super(x, y, Player.size, Player.size);
		health = 100;
		alive = true;
		jumpCount = 2;
		xAcc = 0;
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
	
	public void setXAcc(double x)
	{
		xAcc = x;
	}
	
	public void setYAcc(double y)
	{
		yAcc = y;
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
		yAcc = -2;					//Sets acceleration for falling, aka gravity
	}
	
	public void jump()
	{
		if(jumpCount > 0 && System.currentTimeMillis() - jumpTime >= 250)
		{
			jumpTime = System.currentTimeMillis();
			jumping = true;
			jumpCount--;
			yVel = 35;				//Sets velocity for normal jumping
			yAcc = -2;				//Sets acceleration for normal jumping, aka gravity
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
			yAcc = -8;				//Sets acceleration for slamming			
		}
	}
	
	public boolean isSlamming()
	{
		return slamming;
	}

	public void land()				//Lands character on the ground, updating necessary fields
	{
		jumping = false;
		slamming = false;
		jumpCount = 2;
		yVel = 0;
		yAcc = -0.5;
	}
	
	public void heal(int amount){
		health += amount;
	}
	
	public void damage(int amount, Player enemy){
		if(!enemy.immuneToDamage)
		{
			enemy.health -= amount;
			if(enemy.health < 0){
				enemy.alive = false;
			}
		}
		else
		{
			enemy.damageCounter += amount;
		}
		health -= Math.round(amount/5.00);
		if(health < 0){
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void miss(int amount)
	{
		health -= Math.round(amount/7.00);
		if(health < 0){
			alive = false;
		}
	}
	
	public boolean getFacingLeft(){
		return facingLeft;
	}
	
	public void shootBeam()
	{
		
	}
	
	public void render(double delta, Vector3d vec)
	{	
		double mul = (1 - Math.min(ticks, .25) * 4);
		double mul1 = (ticks > .25 ? 0 : 1);
		if(health <= 0.001)
		{
			health = 0;
			ticks += delta;
			
			if(MathHelper.isEqual(mul, 0))
			{
				xPos = -1000;
				yPos = -1000;
			}
			
			GL11.glPushMatrix();

			GL11.glColor4d(vec.x, vec.y, vec.z, 1);
			GL11.glTranslated(xPos + width / 2 - 960, -500 + height / 2 * mul + yPos, -999);
			GL11.glScaled(width / 2 * (mul / 2), height / 2 * (mul / 2), 7.5 * (mul / 2));
			
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
			genCube();
			
			GL11.glColor4f(0, 0, 0, 1);
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
			GL11.glLineWidth(1f);
			GL11.glScaled(1.01d, 1.02d, 1.02d);
			genCube();
			
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
			
			GL11.glPopMatrix();
		}
		else
		{
			super.render(delta, vec);
		}
		
		GL11.glPushMatrix();

		GL11.glTranslated(xPos + width / 2 - 960, -500 + yPos + height * 1.5, -999);
		
		GL11.glColor4f(0, 0, 0, 1);
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		GL11.glLineWidth(1.5f);
		GL11.glScaled(width / 2 * mul, 7.5 * mul1, 7.5 * mul1);
		GL11.glScaled(1.02d, 1.02d, 1.02d);
		genCube();
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslated(xPos + width / 2 * (health / 100d) - 960, -500 + yPos + height * 1.5, -999);
		GL11.glScaled(width / 2 * (health / 100d), 7.5 * (MathHelper.isEqual(health, 0) ? 0 : 1), 7.5 * (MathHelper.isEqual(health, 0) ? 0 : 1));
		GL11.glColor4d(vec.x * .7, vec.y * .7, vec.z * .7, 1);
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		genCube();
		
		GL11.glPopMatrix();
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Player)){
			return false;
		}
		Player p = (Player) o;
		if(p.getXPos() == this.getXPos() && p.getYPos() == this.getYPos() && p.getHeight() == this.getHeight() && p.getWidth() == this.getWidth()){
			if(p.characterType == characterType){
				return true;
			}
		}
		return false;
	}
	
	public void special() {}
	public void stopSpecial() {}
}
