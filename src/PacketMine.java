import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.polaris.engine.network.Network;
import com.polaris.engine.network.Packet;

public class PacketMine extends Packet
{
	private double posX;
	private double posY;
	
	public PacketMine() {}
	
	public PacketMine(double x, double y)
	{
		posX = x;
		posY = y;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException 
	{
		output.writeDouble(posX);
		output.writeDouble(posY);
	}

	@Override
	public void copy(DataInputStream data) throws IOException 
	{
		posX = data.readDouble();
		posY = data.readDouble();
	}

	@Override
	public void handle(Network network) 
	{
		
	}

}
