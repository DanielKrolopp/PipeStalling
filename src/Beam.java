import org.lwjgl.opengl.GL11;

import net.jafama.FastMath;

public class Beam {

	private double xPos;
	private double yPos;
	private double xPosEnd;

	private boolean adderBeam;

	private int damage;

	private long startTime;

	Player shooter;

	public Beam(Player pBlock, int amount, boolean add)
	{
		xPos = pBlock.getXPos() + pBlock.getWidth()/2;
		yPos = pBlock.getYPos() + pBlock.getHeight()/2;
		adderBeam = add;
		shooter = pBlock;
		damage = amount;
		startTimer();
		GuiWorld.world.addBeam(this);
	}

	public void startTimer() {
		startTime = System.currentTimeMillis();
	}

	public long getTime() {
		return System.currentTimeMillis() - startTime;
	}

	public boolean isAdder()
	{
		return adderBeam;
	}

	public boolean shootBeam()	//True means hit, false is a miss
	{
		if(xPos > GuiWorld.world.getWidth()/2)
		{
			xPosEnd = GuiWorld.world.getWidth();
		}
		else
			xPosEnd = 0;
		double minDistanceLeft = Double.MAX_VALUE;
		double minDistanceRight = Double.MAX_VALUE;
		Block closestVictimLeft = null;
		Block closestVictimRight = null;
		//The following code is really ugly. Could be improved by creating a list of all
		//Blocks and Players and iterating over it
		for(Player player : GuiWorld.world.getPlayers()){
			if(shooter.getCharacter() == player.getCharacter())
				continue;
			double other_top = player.getYPos();
			double other_bottom = player.getYPos() + player.getHeight();
			double other_centerY = (2* player.getYPos() + player.getHeight())/2;
			double other_centerX = (2 * player.getXPos() + player.getWidth())/2; 
			if(player.getXPos() < xPos){ //Checks if victim is left of player
				if(other_top < yPos && other_bottom > yPos){ //Check y-value validity
					double distance = Math.sqrt(Math.pow((other_centerX - xPos), 2) + Math.pow((other_centerY - yPos), 2));
					if(distance < minDistanceLeft){ //If this is the closest opponent
						minDistanceLeft = distance;
						closestVictimLeft = player;
					}
				}
			}
		}
		for(Block block : GuiWorld.world.getBlocks()){
			double other_top = block.getYPos();
			double other_bottom = block.getYPos() + block.getHeight();
			double other_centerY = (2* block.getYPos() + block.getHeight())/2;
			double other_centerX = (2 * block.getXPos() + block.getWidth())/2; 
			if(block.getXPos() < xPos){ //Checks if block is left of player
				if(other_top < yPos && other_bottom > yPos){ //Check y-value validity
					double distance = Math.sqrt(Math.pow((other_centerX - xPos), 2) + Math.pow((other_centerY - xPos), 2));
					if(distance < minDistanceLeft){ //If this is the closest block
						minDistanceLeft = distance;
						closestVictimLeft = block;
					}
				}
			}
		}
		for(Player player : GuiWorld.world.getPlayers()){
			if(shooter.getCharacter() == player.getCharacter())
				continue;
			double other_top = player.getYPos();
			double other_bottom = player.getYPos() + player.getHeight();
			double other_centerY = (2* player.getYPos() + player.getHeight())/2;
			double other_centerX = (2 * player.getXPos() + player.getWidth())/2; 
			if(player.getXPos() > xPos){ //Checks if victim is left of player
				if(other_top < yPos && other_bottom > yPos){ //Check y-value validity
					double distance = Math.sqrt(Math.pow((other_centerX - xPos), 2) + Math.pow((other_centerY - yPos), 2));
					if(distance < minDistanceRight){ //If this is the closest opponent
						minDistanceRight = distance;
						closestVictimRight = player;
					}
				}
			}
		}
		for(Block block : GuiWorld.world.getBlocks()){
			double other_top = block.getYPos();
			double other_bottom = block.getYPos() + block.getHeight();
			double other_centerY = (2* block.getYPos() + block.getHeight())/2;
			double other_centerX = (2 * block.getXPos() + block.getWidth())/2; 
			if(block.getXPos() > xPos){ //Checks if block is left of player
				if(other_top < yPos && other_bottom > yPos){ //Check y-value validity
					double distance = Math.sqrt(Math.pow((other_centerX - xPos), 2) + Math.pow((other_centerY - xPos), 2));
					if(distance < minDistanceRight){ //If this is the closest block
						minDistanceRight = distance;
						closestVictimRight = block;
					}
				}
			}
		}
		yPos = shooter.getYPos();
		if(minDistanceLeft == Double.MAX_VALUE && minDistanceRight == Double.MAX_VALUE)
		{
			shooter.miss(damage);
			return false;
		}
		Block closestVictim = null;
		if(minDistanceLeft < minDistanceRight)
		{
			if(closestVictimLeft instanceof Player)
			{
				closestVictim = closestVictimLeft;
				xPos = shooter.getXPos();
				xPosEnd = closestVictimLeft.getXPos() + closestVictimLeft.getWidth();
			}
			
			else if(closestVictimRight instanceof Player)
			{
				closestVictim = closestVictimRight;
				xPos = shooter.getXPos() + shooter.getWidth();
				xPosEnd = closestVictimRight.getXPos();
			}
			else
			{
				xPos = shooter.getXPos();
				xPosEnd = closestVictimLeft.getXPos() + closestVictimLeft.getWidth();
			}
		}
		else
		{
			if(closestVictimRight instanceof Player)
			{
				closestVictim = closestVictimRight;
				xPos = shooter.getXPos() + shooter.getWidth();
				xPosEnd = closestVictimRight.getXPos();
			}
			else if(closestVictimLeft instanceof Player)
			{
				closestVictim = closestVictimLeft;
				xPos = shooter.getXPos();
				xPosEnd = closestVictimLeft.getXPos() + closestVictimLeft.getWidth();
			}
			else
			{
				xPos = shooter.getXPos() + shooter.getWidth();
				xPosEnd = closestVictimRight.getXPos();
			}
		}
		if(closestVictim != null && closestVictim instanceof Player){ //Means he hit someone
			shooter.damage(damage, (Player) closestVictim); //Janky casting is my specialty	
			return true;
		}
		else{ //Indicates a miss
			shooter.miss(damage);
			return false;
		}

	}

	public void render(double delta)
	{
		double time = (System.currentTimeMillis() - startTime) / 20f;
		GL11.glBegin(GL11.GL_LINES);
		GL11.glColor3f(0, 0, 0);
		if(xPos < xPosEnd)
		{
			double x = xPos - 960;
			double y = -500 + (yPos + FastMath.sin(time) * 10);
			for(int i = 0; i < (int)((xPosEnd - xPos) / 10); i++)
			{
				GL11.glVertex3d(x, y, -999);
				x += 10;
				y = -500 + (yPos + FastMath.sin(time + x - xPos) * 10);
				GL11.glVertex3d(x, y, -999);
			}
		}
		else
		{
			double x = xPosEnd - 960;
			double y = -500 + (yPos + FastMath.sin(time) * 10);
			for(int i = 0; i < (int)((xPos - xPosEnd) / 10); i++)
			{
				GL11.glVertex3d(x, y, -999);
				x += 10;
				y = -500 + (yPos + FastMath.sin(time + x - xPosEnd) * 10);
				GL11.glVertex3d(x, y, -999);
			}
		}
		GL11.glEnd();
	}

}