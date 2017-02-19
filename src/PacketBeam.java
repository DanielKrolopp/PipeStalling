import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.polaris.engine.network.Network;
import com.polaris.engine.network.Packet;

public class PacketBeam extends Packet
{
	
	private double xPos;
	private double yPos;
	private double endXPos;
	private long startTime;
	
	public PacketBeam() {}
	
	public PacketBeam(double x, double y, double x1, long t) 
	{
		xPos = x;
		yPos = y;
		endXPos = x1;
		startTime = t;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException 
	{
		output.writeDouble(xPos);
		output.writeDouble(yPos);
		output.writeDouble(endXPos);
		output.writeLong(startTime);
	}

	@Override
	public void copy(DataInputStream data) throws IOException 
	{
		xPos = data.readDouble();
		yPos = data.readDouble();
		endXPos = data.readDouble();
		startTime = data.readLong();
	}

	@Override
	public void handle(Network network) 
	{
		
	}

}
