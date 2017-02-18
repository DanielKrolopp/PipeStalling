
public class Block {
	
	double x, y, height, width;
	
	public Block(double x, double y, double height, double width){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	public double getXPos(){
		return x;
	}
	public double getYPos(){
		return y;
	}
	public void setX(double amount){
		x = amount;
	}
	public void setY(double amount){
		y = amount;
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
