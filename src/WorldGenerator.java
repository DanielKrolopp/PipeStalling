import java.util.ArrayList;

public class WorldGenerator {


	//worldWidth and worldHeight are width/height of the untrimmed world, Killian	
	public static World generateWorld(GameSettings gameSettings, int widthTrim, int heightTrim, int[] players) {
		double worldWidth = 1920;
		double worldHeight = 1080;
		World generated = new World(worldWidth, worldHeight, gameSettings, players);
		
		//adds ceiling and floor
		generated.addBlock(new Block(-200, 0, 2500, 30));
		//generated.addBlock(new Block(-200, worldHeight - 30 , 2500, 30));

		/*double xBase = worldWidth % 10 / 2 + 100;
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
		}*/
		
		ArrayList<Block> blocks = generateBlocks(generated, 1920, 1080, 15);
		for(Block b : blocks)
		{
			generated.addBlock(b);
		}
		return generated;
	}

	public static ArrayList<Block> generateBlocks(World world, int width, int height, int numBlocks)
	{
		ArrayList<Block> blocks = new ArrayList<Block>();

		int baseX = width % 10 / 2 + 100;
		int baseY = height % 10 / 2 + 100;

		width -= baseX + baseX;
		height -= baseY + 100;

		double blockX, blockY, blockWidth, blockHeight;
		boolean notOverlapping = true;

		int size = 50;

		for(int i = 0; i < numBlocks; i++)
		{	
			int numTallBlocks = 0;
			blockX = baseX + (int) (Math.random() * (width - size * 2) / size) * size;
			blockY = baseY + (int) (Math.random() * (height - size) / size) * size;
			blockWidth = (int) (Math.random() * Math.min((width - blockX) / size, 5) + 1) * size;
			if(blockWidth <= 100 && numTallBlocks <= 4) {
				if(blockWidth == 100)
					blockWidth = 75;
				blockHeight = (int)(Math.random() * Math.min((height - blockY) / size, 5) + 2) * size;
				numTallBlocks++;
			}
			else
				blockHeight = (int) (Math.random() * Math.min((height - blockY) / size, 2) + 1) * size;

			notOverlapping = true;

			Block block = new Block(blockX, blockY, blockWidth, blockHeight);

			for(int j = 0; j < blocks.size(); j++)
			{
				Block collider = blocks.get(j);
				Block newCollider = new Block(collider.getXPos() - 20, collider.getYPos() - 20, collider.getWidth() + 40, collider.getHeight() + 40);
				if(world.isColliding(block, newCollider))
				{
					notOverlapping = false;
					j = blocks.size();
				}
			}
			if(notOverlapping)
			{
				blocks.add(block);
			}
		}
		return blocks;
	}
}
