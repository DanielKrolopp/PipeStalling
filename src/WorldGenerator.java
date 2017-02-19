import java.util.ArrayList;
import java.util.Arrays;

import com.google.common.collect.Lists;
import com.polaris.engine.util.MathHelper;

public class WorldGenerator {
	//worldWidth and worldHeight are width/height of the untrimmed world, Killian	
	public static World generateWorld(GameSettings gameSettings, int widthTrim, int heightTrim, int[] players) {
		double worldWidth = 1920;
		double worldHeight = 1080;
		World generated = new World(worldWidth, worldHeight, gameSettings, players);

		//adds floor
		generated.addBlock(new Block(-200, 0, 2500, 30));

		ArrayList<Block> blocks = generateBlocks(generated, (int)worldWidth, (int)worldHeight, 15);
		System.out.println("generating pipes");
		ArrayList<Pipe> pipes = generatePipes(generated);
		System.out.println("done");
		for(Block b : blocks)
		{
			generated.addBlock(b);
		}
		for(Pipe p : pipes){
			generated.addPipe(p);
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

		int blocksAdded = 0;
		while(blocksAdded < numBlocks) {
			int layer = MathHelper.random(2);
			int half = MathHelper.random(1);
			
			int maxPerLayer = (int)(Math.ceil(numBlocks/3.0));
			int maxPerHalf = (int)(Math.ceil(numBlocks/2.0));
			int[] numBlocksInLayer = {0, 0, 0};
			int[] numBlocksInHalf = {0, 0};
			double blockX = 0;
			double blockY = 0;
			if(layer == 0) {
				if(numBlocksInLayer[0] < maxPerLayer) {
					blockY = MathHelper.random(131, height/3);
				}
			} else if(layer == 1) {
				if(numBlocksInLayer[1] < maxPerLayer) {
					blockY = MathHelper.random(height/3+25, 2*height/3);
				}
			} else {
				if(numBlocksInLayer[2] < maxPerLayer) {
					blockY = MathHelper.random(2*height/3+25, height-60);
				}
			}

			if(half == 0) {
				if(numBlocksInHalf[0] < maxPerHalf) {
					blockX = MathHelper.random(25, width/2 - 30);
				}
			} else {
				if(numBlocksInHalf[1] < maxPerHalf) {
					blockX = MathHelper.random(width/2 + 30, width - 25);
				}
			}

			double blockWidth, blockHeight;
			boolean notOverlapping = true;

			int size = 50;

			int numTallBlocks = 0;
			blockWidth = (int) (Math.random() * Math.min((width - blockX) / size, 6) + 1) * size;
			if(blockWidth <= 100 && numTallBlocks <= 2) {
				if(blockWidth == 100)
					blockWidth = 75;
				blockHeight = (int)(Math.random() * Math.min((height - blockY) / size, 4) + 2) * size;
				numTallBlocks++;
			}
			else
				blockHeight = (int) (Math.random() * Math.min((height - blockY) / size, 1) + 1) * size;

			notOverlapping = true;
			if(blockHeight < 10){
				blockHeight += 20;
			}
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
				blocksAdded++;
				numBlocksInLayer[layer]++;
				numBlocksInHalf[half]++;
			}
		}
		return blocks;
	}
	
	public static ArrayList<Pipe> generatePipes(World world){
		System.out.println("entered new method");
		Pipe top = Pipe.createLinkedPipes(true, world);
		Pipe bot = top.getMate();
		return new ArrayList<Pipe>(Arrays.asList(top, bot));
	}
}
