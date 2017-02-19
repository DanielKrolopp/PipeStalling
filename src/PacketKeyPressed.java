import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.polaris.engine.network.Network;
import com.polaris.engine.network.Packet;

public class PacketKeyPressed extends Packet
{
	
	private int[][] keysPressed;
	
	public PacketKeyPressed() {}
	
	public PacketKeyPressed(int[] ... is)
	{
		keysPressed = is;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException 
	{
		output.writeInt(keysPressed.length);
		for(int[] i : keysPressed)
		{
			output.writeInt(i[0]);
		}
	}

	@Override
	public void copy(DataInputStream data) throws IOException 
	{
		keysPressed = new int[data.readInt()][];
		for(int i = 0; i < keysPressed.length; i++)
		{
			keysPressed[i] = new int[] {data.readInt()};
		}
	}

	@Override
	public void handle(Network network) 
	{
		
	}

}
