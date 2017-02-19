import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.polaris.engine.network.Network;
import com.polaris.engine.network.Packet;

public class PacketWorld extends Packet
{

	private double[][] blocks;
	private double[] pipe;

	public PacketWorld() {}

	public PacketWorld(double[] d, double[] ... ds)
	{
		blocks = ds;
		pipe = d;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException 
	{
		output.writeInt(blocks.length);
		for(int i = 0; i < blocks.length; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				output.writeDouble(blocks[i][j]);
			}
		}

		output.writeInt(pipe.length);
		for(int i = 0; i < pipe.length; i++)
		{
			output.writeDouble(pipe[i]);
		}
	}

	@Override
	public void copy(DataInputStream data) throws IOException 
	{
		blocks = new double[data.readInt()][];
		for(int i = 0; i < blocks.length; i ++)
		{
			blocks[i] = new double[4];
			for(int j = 0; j < 4; j++)
			{
				blocks[i][j] = data.readDouble();
			}
		}
		
		pipe = new double[data.readInt()];
		for(int i = 0; i < pipe.length; i++)
		{
			pipe[i] = data.readDouble();
		}
	}

	@Override
	public void handle(Network network) 
	{

	}

}
