import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.polaris.engine.network.Network;
import com.polaris.engine.network.Packet;

public class PacketExplosion extends Packet
{
	private double[] distances;
	private double midX;
	private double midY;

	public PacketExplosion() {}

	public PacketExplosion(double[] d, double mx, double my)
	{
		distances = d;
		midX = mx;
		midY = my;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException 
	{
		output.writeInt(distances.length);
		for(int i = 0; i < distances.length; i++)
		{
			output.writeDouble(distances[i]);
		}
		output.writeDouble(midX);
		output.writeDouble(midY);
	}

	@Override
	public void copy(DataInputStream data) throws IOException
	{
		distances = new double[data.readInt()];
		
		for(int i = 0; i < distances.length; i++)
		{
			distances[i] = data.readDouble();
		}
		
		midX = data.readDouble();
		midY = data.readDouble();
	}

	@Override
	public void handle(Network network)
	{

	}

}
