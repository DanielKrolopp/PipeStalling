/**
 * 
 */


import org.lwjgl.glfw.GLFW;

import com.polaris.engine.options.Input;
import com.polaris.engine.options.Key;
import com.polaris.engine.options.Settings;

/**
 * @author lec50
 *
 */
public class GameSettings extends Settings
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8852791905224500366L;
	
	private int worldWidth;
	private int worldHeight;
	
	private Key[] jumpKey;
	private Key[] smashKey;
	private Key[] leftKey;
	private Key[] rightKey;
	private Key[] beamKey;
	private Key[] superKey;
	private Key[] runKey;
	
	public void init(Input i)
	{
		super.init(i);
		
		this.worldWidth = 500;
		this.worldHeight = 500;
		
		this.jumpKey = new Key[]{i.getKey(GLFW.GLFW_KEY_W), i.getKey(GLFW.GLFW_KEY_I)};
		this.smashKey = new Key[]{i.getKey(GLFW.GLFW_KEY_S), i.getKey(GLFW.GLFW_KEY_K)};
		this.leftKey = new Key[]{i.getKey(GLFW.GLFW_KEY_A), i.getKey(GLFW.GLFW_KEY_J)};
		this.rightKey = new Key[]{i.getKey(GLFW.GLFW_KEY_D), i.getKey(GLFW.GLFW_KEY_L)};
		this.beamKey = new Key[]{i.getKey(GLFW.GLFW_KEY_E), i.getKey(GLFW.GLFW_KEY_O)};
		this.superKey = new Key[]{i.getKey(GLFW.GLFW_KEY_Q), i.getKey(GLFW.GLFW_KEY_U)};
		this.runKey = new Key[]{i.getKey(GLFW.GLFW_KEY_LEFT_SHIFT), i.getKey(GLFW.GLFW_KEY_RIGHT_SHIFT)};
	}
	
	public int getWorldWidth()
	{
		return worldWidth;
	}
	
	public int getWorldHeight()
	{
		return worldHeight;
	}
	
	public Key getPlayerJump(int i)
	{
		return jumpKey[i];
	}
	
	public Key getPlayerSmash(int i)
	{
		return smashKey[i];
	}
	
	public Key getPlayerLeft(int i)
	{
		return leftKey[i];
	}
	
	public Key getPlayerRight(int i)
	{
		return rightKey[i];
	}
	
	public Key getPlayerBeam(int i)
	{
		return beamKey[i];
	}
	
	public Key getPlayerSuper(int i)
	{
		return superKey[i];
	}
	
	public Key getPlayerRun(int i)
	{
		return runKey[i];
	}
	
}
