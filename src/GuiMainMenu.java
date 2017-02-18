import java.io.File;

import org.joml.Vector3d;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.polaris.engine.App;
import com.polaris.engine.gui.GuiScreen;
import com.polaris.engine.render.Shader;
import com.polaris.engine.util.MathHelper;

public class GuiMainMenu extends GuiScreen<GameSettings>
{
	
	private Shader colorShader;
	private int time;
	
    private char[] sequence = "PIPE STALLING".toCharArray();
    private float stackSmashingStartX = 0;
    private float[] ticks = new float[sequence.length];
    private float[] toTicks = new float[sequence.length];
	
	public GuiMainMenu(App<GameSettings> app) 
	{
		super(app);
	}
	
	public void init()
	{
		super.init();
		
		colorShader = Shader.createShader(new File("shaders/colorShader.vert"), new File("shaders/colorShader.frag"));
		
		time = GL20.glGetUniformLocation(colorShader.getShaderId(), "time");
		
		stackSmashingStartX = 1920 / 2 - gameSettings.getFont().getWidth(new String(sequence)) / 2f;
	}
	
	public void render(double delta)
	{
		super.render(delta);
		
		colorShader.bind();
		GL20.glUniform1f(time, (System.currentTimeMillis() % 1024) / 1024f);
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(0, 0);
		GL11.glVertex2d(0, 1080);
		GL11.glVertex2d(1920, 1080);
		GL11.glVertex2d(1920, 0);
		GL11.glEnd();
		
		colorShader.unbind();
		
		GL11.glPushMatrix();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1920, 1080, 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		gameSettings.getFont().bind();
		
		Vector3d soylent = new Vector3d(195 / 255d, 201 / 255d, 13 / 255d);
		Vector3d toGreen = new Vector3d(((136 - 195) / 255d) / sequence.length, ((241 - 201) / 255d) / sequence.length, ((48 - 13) / 255d) / sequence.length);
		
		GL11.glColor4d(soylent.x, soylent.y, soylent.z, 1d);
        
        float shiftX = stackSmashingStartX;
        
        for(int i = 0; i < sequence.length; i++)
        {
            soylent.x += toGreen.x;
            soylent.y += toGreen.y;
            soylent.z += toGreen.z;
    		GL11.glColor4d(soylent.x, soylent.y, soylent.z, 1d);
            gameSettings.getFont().draw("" + sequence[i], shiftX, 200 + ticks[i], .5f, 1f);
            shiftX += gameSettings.getFont().getWidth("" + sequence[i]);
        }
        
        /*if(input.getMouseX() >= 1920 / 2 - gameSettings.getFont().getWidth("START A GAME", 48 / 128f) && input.getMouseX() <= 1920 / 2 + gameSettings.getFont().getWidth("START A GAME", 48f / 128f))
        {
            if(input.getMouseY() >= 600 - 32 && input.getMouseY() <= 600 + 32)
            {
                GL11.glColor4f(1, 1, 1, 1);
            }
        }*/
        
        gameSettings.getFont().draw("START A GAME", 1920 / 2f, 400f, .5f, 48f / 128f);
		
		gameSettings.getFont().unbind();
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-1, 1, -1, 1, -1, 1);
		GL11.glPopMatrix();
		
		colorShader.bind();
		colorShader.unbind();
	}

	public void update(double delta)
	{
		for(int i = 0; i < ticks.length; i++)
        {
            if(MathHelper.isEqual(ticks[i], toTicks[i]))
            {
                toTicks[i] = (float) (Math.random() * 20 - 10);
            }
            else
            {
                ticks[i] = (float) MathHelper.getExpValue(ticks[i], toTicks[i], .25, delta);
            }
        }
	}
	
}
