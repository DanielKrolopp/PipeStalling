
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
