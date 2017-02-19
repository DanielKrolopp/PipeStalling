public class WorldGenerator {
	//worldWidth and worldHeight are width/height of the untrimmed world, Killian
	public static World generateWorld(GameSettings gameSettings, int widthTrim, int heightTrim, int[] playerCharacters) {
		World generated = new World(widthTrim, heightTrim, gameSettings);
		int i = 0;
		while(i < 20){ //Blocks will NOT overlap (at least they shouldn't)
			int width = (int)(Math.random() * 40) + 20;
			int height = (int)(Math.random() * 40) + 20;
			int x = (int)(Math.random() * (widthTrim - width));
			int y = (int)(Math.random() * (heightTrim - height));
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
