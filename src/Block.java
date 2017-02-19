import org.joml.Vector3d;
import org.lwjgl.opengl.GL11;

public class Block {
	
	double xPos, yPos, height, width;
	
	public Block(double x, double y, double width, double height){
		this.xPos = x;
		this.yPos = y;
		this.height = height;
		this.width = width;
	}
	
	public double getXPos(){
		return xPos;
	}
	public double getYPos(){
		return yPos;
	}
	public void setXPos(double amount){
		xPos = amount;
	}
	public void setYPos(double amount){
		yPos = amount;
	}
	public double getHeight(){
		return height;
	}
	public double getWidth(){
		return width;
	}
	
	public void render(double delta, Vector3d vec)
	{
		GL11.glPushMatrix();
		
		GL11.glColor4d(vec.x, vec.y, vec.z, 1);
		GL11.glTranslated(xPos + width / 2 - 960, -550  + height / 2 + yPos, -999);
		GL11.glScaled(width / 2, height / 2, 7.5);
		
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
	
	public boolean collidesWith(Block block){
		/*if(getXPos() < block.getXPos() && getXPos() + getWidth() > block.getXPos()){
			return true;
		}
		else if(block.getXPos() < getXPos() && block.getXPos() + block.getWidth() > getXPos()){
			return true;
		}
		else if(getYPos() < block.getYPos() && getYPos() + getHeight() > block.getYPos()){
			return true;
		}
		else if(block.getYPos() < getYPos() && block.getYPos() + block.getHeight() > getYPos()){
			return true;
		}
		return false;*/
		
		return (getXPos() < block.getXPos() + block.getWidth() &&
				getXPos() + getWidth() > block.getXPos() &&
				getYPos() < block.getYPos() + block.getHeight() &&
				getHeight() + getYPos() > block.getYPos());
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Block)){
			return false;
		}
		Block b = (Block) o;
		if(b.getXPos() == this.getXPos() && b.getYPos() == this.getYPos() && b.getHeight() == this.getHeight() && b.getWidth() == this.getWidth()){
			return true;
		}
		return false;
	}
	
	public void genCube()
	{
		GL11.glBegin(GL11.GL_QUADS);                // Begin drawing the color cube with 6 quads
		// Top face (y = 1.0f)
		// Define vertices in counter-clockwise (CCW) order with normal pointing out
		GL11.glVertex3f( 1.0f, 1.0f, -1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
		GL11. glVertex3f(-1.0f, 1.0f,  1.0f);
		GL11.glVertex3f( 1.0f, 1.0f,  1.0f);
		
		GL11.glVertex3f( 1.0f,  -1.0f, -1.0f);
		GL11.glVertex3f(-1.0f,  -1.0f, -1.0f);
		GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
		GL11.glVertex3f( 1.0f, -1.0f, 1.0f);

		// Front face  (z = 1.0f)
		GL11.glVertex3f( 1.0f,  1.0f, 1.0f);
		GL11.glVertex3f(-1.0f,  1.0f, 1.0f);
		GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
		GL11.glVertex3f( 1.0f, -1.0f, 1.0f);

		// Back face (z = -1.0f)
		GL11.glVertex3f( 1.0f, -1.0f, -1.0f);
		GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
		GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
		GL11.glVertex3f( 1.0f,  1.0f, -1.0f);

		// Left face (x = -1.0f)    // Blue
		GL11.glVertex3f(-1.0f,  1.0f,  1.0f);
		GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
		GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
		GL11.glVertex3f(-1.0f, -1.0f,  1.0f);

		// Right face (x = 1.0f)  // Magenta
		GL11.glVertex3f(1.0f,  1.0f, -1.0f);
		GL11.glVertex3f(1.0f,  1.0f,  1.0f);
		GL11.glVertex3f(1.0f, -1.0f,  1.0f);
		GL11.glVertex3f(1.0f, -1.0f, -1.0f);
		GL11.glEnd();  // End of drawing color-cube
	}
}
