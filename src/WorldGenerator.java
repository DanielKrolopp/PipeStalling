
public class WorldGenerator {


	//worldWidth and worldHeight are width/height of the untrimmed world, Killian	
	public static World generateWorld(GameSettings gameSettings, int widthTrim, int heightTrim, int[] players) {
		double worldWidth = 1920 - 2 * widthTrim;
		double worldHeight = 1080 - 2 * heightTrim;
		World generated = new World(worldWidth, worldHeight, gameSettings, players);
		double xBase = worldWidth % 10 / 2 + 100;
		double yBase = worldHeight % 10 / 2 + 100;
		int size = 50;
		
		double blockWidth;
		double blockHeight;
		double blockX;
		double blockY;
		
		int i = 0;
		while(i < 20){ 
			blockX = xBase + (int) (Math.random() * (worldWidth - size * 2) / size) * size;
			blockY = yBase + (int) (Math.random() * (worldHeight - size) / size) * size;
			blockWidth = (int) (Math.random() * Math.min((worldWidth - blockX) / size, 5) + 1) * size;
			blockHeight = (int)(Math.random() * Math.min((worldHeight - blockY) / size, 2) + 2) * size;
		
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
		return generated;
	}
}
