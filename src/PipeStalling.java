import com.polaris.engine.App;

public class PipeStalling extends App<GameSettings>
{
	
	protected PipeStalling() 
	{
		super(true);
	}

	public static void main(String[] args)
	{
		App.start(new PipeStalling());
	}
	
	public void init()
	{
		super.init();
		gameSettings.createFonts();
		gameSettings.getKeys();
		this.initGui(new GuiMainMenu(this));
	}

	@Override
	protected GameSettings loadSettings() 
	{
		return new GameSettings();
	}

}
