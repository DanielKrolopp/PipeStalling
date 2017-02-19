
public class Block {
	
	double xPos, yPos, height, width;
	
	public Block(double x, double y, double height, double width){
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
	
	public boolean collidesWith(Block block){
		if(getXPos() < block.getXPos() && getXPos() + getWidth() > block.getXPos()){
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
		return false;
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
}
