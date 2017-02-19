import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.polaris.engine.network.Network;
import com.polaris.engine.network.Packet;

public class PacketPlayers extends Packet
{
	
	private int[] playerIds;
	
	public PacketPlayers() {}
	
	public PacketPlayers(int ... ids) 
	{
		playerIds = ids;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException 
	{
		output.writeInt(playerIds.length);
		for(int i : playerIds)
		{
			output.writeInt(playerIds[i]);
		}
	}

	@Override
	public void copy(DataInputStream data) throws IOException 
	{
		int length = data.readInt();
		playerIds = new int[length];
		for(int i = 0; i < length; i++)
		{
			playerIds[i] = data.readInt();
		}
	}

	@Override
	public void handle(Network network) 
	{
		
	}

}
