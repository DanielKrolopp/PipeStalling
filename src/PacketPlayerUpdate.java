import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.polaris.engine.network.Network;
import com.polaris.engine.network.Packet;

public class PacketPlayerUpdate extends Packet
{
	
	private int player;
	private int type;
	private double posX;
	private double posY;
	private int state;
	
	public PacketPlayerUpdate() {}
	
	public PacketPlayerUpdate(int p, int t, int x, int y, int s)
	{
		player = p;
		type = t;
		posX = x;
		posY = y;
		state = s;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException 
	{
		output.writeInt(player);
		output.writeInt(type);
		output.writeDouble(posX);
		output.writeDouble(posY);
		output.writeInt(state);
	}

	@Override
	public void copy(DataInputStream data) throws IOException 
	{
		player = data.readInt();
		type = data.readInt();
		posX = data.readDouble();
		posY = data.readDouble();
		state = data.readInt();
	}

	@Override
	public void handle(Network network) 
	{
		
	}

}
