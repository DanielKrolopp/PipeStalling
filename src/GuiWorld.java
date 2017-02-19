import java.io.File;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.polaris.engine.App;
import com.polaris.engine.gui.GuiScreen;
import com.polaris.engine.render.Shader;

public class GuiWorld extends GuiScreen<GameSettings>
{

	public static World world;
	
	private Shader colorShader;
	private int time;
	
	private int[] playerCharacters;
	
	public GuiWorld(App<GameSettings> app, Shader s, int t, int count, int ... pc) 
	{
		super(app);
		playerCharacters = pc;
		
		colorShader = s;
		time = t;
	}
	
	public void init()
	{
		super.init();
		world = WorldGenerator.generateWorld(gameSettings, 10, 20, playerCharacters);
	}
	
	public void update(double delta)
	{
		super.update(delta);
		
		world.update(delta);
	}
	
	public void render(double delta)
	{
		super.render(delta);
		
		/*colorShader.bind();
		GL20.glUniform1f(time, (System.currentTimeMillis() % 1024) / 1024f);
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(0, 0);
		GL11.glVertex2d(0, 1080);
		GL11.glVertex2d(1920, 1080);
		GL11.glVertex2d(1920, 0);
		GL11.glEnd();
		
		colorShader.unbind();*/
		
		GL11.glPushMatrix();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1920, 1080, 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		world.render(delta);
	}

}
