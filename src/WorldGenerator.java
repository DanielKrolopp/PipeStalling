import com.polaris.engine.util.MathHelper;

public class WorldGenerator {


	//worldWidth and worldHeight are width/height of the untrimmed world, Killian	
	public static World generateWorld(GameSettings gameSettings, int widthTrim, int heightTrim, int[] players) {
		double worldWidth = 1920;
		double worldHeight = 1080;
		World generated = new World(worldWidth, worldHeight, gameSettings, players);
		double xBase = worldWidth % 10 / 2 + 100;
		double yBase = worldHeight % 10 / 2 + 100;
		int size = 50;
		
		double blockX;
		double blockY;
		double baseVal = 30;
		
		int i = 0;
		while(i < 15){ 
			blockX = xBase + (int) (Math.random() * (worldWidth - size * 2) / size) * size;
			blockY = yBase + (int) (Math.random() * (worldHeight - size) / size) * size;
			int widthMultiplier = MathHelper.random(2, 5);
			int heightMultiplier;
			if(widthMultiplier < 3) {
				heightMultiplier = MathHelper.random(3, 5);
			} else {
				heightMultiplier = MathHelper.random(1, 3);
			}
			double blockWidth = baseVal * widthMultiplier;
			double blockHeight = baseVal * heightMultiplier;
		
			Block block = new Block(blockX, blockY, blockHeight, blockWidth);
			
			boolean collision = false;
			for(Block b : generated.getBlocks()){
				if(block.collidesWith(b)){
					collision = true;
					break;
				}
			}
			if(!collision){
				generated.addBlock(block);
				i++;
			}
		}
		generated.addBlock(new Block(-200, 0, 2500, 30));
		generated.addBlock(new Block(-200, worldHeight - 30 , 2500, 30));
		return generated;
	}
}
