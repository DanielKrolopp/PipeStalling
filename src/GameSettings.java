/**
 * 
 */


import java.io.File;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

import com.polaris.engine.options.Input;
import com.polaris.engine.options.Key;
import com.polaris.engine.options.Settings;
import com.polaris.engine.render.Font;

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
	
	private Font gameFont;
	
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
		
		GLFWVidMode video = this.getMonitor().getVideoMode();
		
		this.setWindowWidth((int) (video.width() / 1.5));
		this.setWindowHeight((int) (video.height() / 1.5));
	}
	
	public void createFonts()
	{
		gameFont = Font.createFont(new File("font/copper.ttf"), 128);
	}
	
	public void getKeys()
	{
		
		this.jumpKey = new Key[]{input.getKey(GLFW.GLFW_KEY_W), input.getKey(GLFW.GLFW_KEY_I), input.getKey(GLFW.GLFW_KEY_T), input.getKey(GLFW.GLFW_KEY_UP)};
		this.smashKey = new Key[]{input.getKey(GLFW.GLFW_KEY_S), input.getKey(GLFW.GLFW_KEY_K), input.getKey(GLFW.GLFW_KEY_G), input.getKey(GLFW.GLFW_KEY_DOWN)};
		this.leftKey = new Key[]{input.getKey(GLFW.GLFW_KEY_A), input.getKey(GLFW.GLFW_KEY_J), input.getKey(GLFW.GLFW_KEY_F), input.getKey(GLFW.GLFW_KEY_LEFT)};
		this.rightKey = new Key[]{input.getKey(GLFW.GLFW_KEY_D), input.getKey(GLFW.GLFW_KEY_L), input.getKey(GLFW.GLFW_KEY_H), input.getKey(GLFW.GLFW_KEY_RIGHT)};
		this.beamKey = new Key[]{input.getKey(GLFW.GLFW_KEY_E), input.getKey(GLFW.GLFW_KEY_O), input.getKey(GLFW.GLFW_KEY_Y), input.getKey(GLFW.GLFW_KEY_RIGHT_SHIFT)};
		this.superKey = new Key[]{input.getKey(GLFW.GLFW_KEY_Q), input.getKey(GLFW.GLFW_KEY_U), input.getKey(GLFW.GLFW_KEY_R), input.getKey(GLFW.GLFW_KEY_SLASH)};
	}
	
	public String getTitle()
	{
		return "Pipe Stalling";
	}
	
	public Font getFont()
	{
		return gameFont;
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
