import com.polaris.engine.App;
import com.polaris.engine.gui.GuiScreen;

public class GuiWorld extends GuiScreen<GameSettings>
{

	private World logicWorld;
	
	public GuiWorld(App<GameSettings> app) 
	{
		super(app);
	}
	
	public void init()
	{
		super.init();
		logicWorld = World.generateWorld(gameSettings, gameSettings.getWorldWidth(), gameSettings.getWorldHeight());
	}
	
	public void update(double delta)
	{
		super.update(delta);
		logicWorld.update(delta);
	}
	
	public void render(double delta)
	{
		super.render(delta);
	}

}
