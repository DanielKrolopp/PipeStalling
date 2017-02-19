import com.polaris.engine.App;
import com.polaris.engine.network.Packet;

public class PipeStalling extends App<GameSettings>
{
	
	protected PipeStalling() 
	{
		super(true);
	}

	public static void main(String[] args)
	{
		Packet.addPacket(PacketBeam.class);
		Packet.addPacket(PacketExplosion.class);
		Packet.addPacket(PacketKeyPressed.class);
		Packet.addPacket(PacketPlayers.class);
		Packet.addPacket(PacketPlayerUpdate.class);
		Packet.addPacket(PacketWorld.class);
		
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
