import org.joml.Vector3d;

public enum CharacterType {
	JUMP(new Vector3d(0, 191 / 255d, 1d)), ADD(new Vector3d(128 / 255d, 0, 0)), LOAD(new Vector3d(204 / 255d, 204 / 255d, 0)), STORE(new Vector3d(50 / 255d, 205 / 255d, 50 / 255d));

	private Vector3d color;

	private CharacterType(Vector3d vec)
	{
		color = vec;
	}

	public Vector3d getColor()
	{
		return color;
	}

	public static CharacterType getCharacter(int index)
	{
		switch(index)
		{
		case 0:
			return LOAD;
		case 1:
			return STORE;
		case 2:
			return JUMP;
		default:
			return ADD;
		}
	}	//STORE > LOAD > ADD > JUMP > STORE
}
