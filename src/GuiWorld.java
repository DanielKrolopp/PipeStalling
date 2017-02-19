import com.polaris.engine.App;
import com.polaris.engine.gui.GuiScreen;

public class GuiWorld extends GuiScreen<GameSettings>
{

	public static World world;
	private int playerCount;
	private int[] playerCharacters;
	
	public GuiWorld(App<GameSettings> app, int count, int ... pc) 
	{
		super(app);
		playerCount = count;
		playerCharacters = pc;
	}
	
	public void init()
	{
		super.init();
		world = WorldGenerator.generateWorld(gameSettings, 10, 20, playerCount, playerCharacters);
	}
	
	public void update(double delta)
	{
		super.update(delta);
		world.update(delta);
	}
	
	public void render(double delta)
	{
		super.render(delta);
		world.render(delta);
	}

}
