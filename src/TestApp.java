import com.polaris.engine.App;
import com.polaris.engine.options.Settings;

public class TestApp extends App<Settings>
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
	protected Settings loadSettings() 
	{
		return new Settings();
	}

}
