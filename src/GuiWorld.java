import com.polaris.engine.App;
import com.polaris.engine.gui.GuiScreen;

public class GuiWorld extends GuiScreen<GameSettings>
{

	public static World world;
	
	public GuiWorld(App<GameSettings> app) 
	{
		super(app);
	}
	
	public void init()
	{
		super.init();
		world = World.generateWorld(gameSettings, gameSettings.getWorldWidth(), gameSettings.getWorldHeight());
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
