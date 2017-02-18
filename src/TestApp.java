import com.polaris.engine.App;

public class TestApp extends App<GameSettings>
{
	
	protected TestApp() 
	{
		super(true);
	}

	public static void main(String[] args)
	{
		App.start(new TestApp());
	}
	
	public void init()
	{
		super.init();
		this.initGui(new GuiTest(this));
	}

	@Override
	protected GameSettings loadSettings() 
	{
		return new GameSettings();
	}

}
