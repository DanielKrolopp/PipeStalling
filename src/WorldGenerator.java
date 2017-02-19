public class WorldGenerator {
	public static World generateWorld(GameSettings gameSettings, int widthTrim, int heightTrim, int[] players) {
		double worldWidth = 1920 - 2 * widthTrim;
		double worldHeight = 1080 - 2 * heightTrim;
		World generated = new World(worldWidth, worldHeight, gameSettings);
		int i = 0;
		while(i < 20){ //Blocks will NOT overlap (at least they shouldn't)
			int width = (int)(Math.random() * 40) + 20;
			int height = (int)(Math.random() * 40) + 20;
			int x = (int)(Math.random() * (worldWidth - width));
			int y = (int)(Math.random() * (worldHeight - height));
			Block block = new Block(x, y, height, width);
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
