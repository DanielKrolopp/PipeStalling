import java.io.File;

import org.joml.Vector3d;
import org.lwjgl.glfw.GLFW;
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
	private int[] color;
	
    private char[] sequence = "PIPE STALLING".toCharArray();
    private float stackSmashingStartX = 0;
    private float[] ticks = new float[sequence.length];
    private float[] toTicks = new float[sequence.length];
	
	public GuiMainMenu(App<GameSettings> app) 
	{
		super(app);
		
		colorShader = Shader.createShader(new File("shaders/backgroundShader.vert"), new File("shaders/backgroundShader.frag"));
		
		time = GL20.glGetUniformLocation(colorShader.getShaderId(), "time");
		color = new int[3];
		color[0] = GL20.glGetUniformLocation(colorShader.getShaderId(), "red");
		color[1] = GL20.glGetUniformLocation(colorShader.getShaderId(), "green");
		color[2] = GL20.glGetUniformLocation(colorShader.getShaderId(), "blue");
				
		stackSmashingStartX = 1920 / 2 - gameSettings.getFont().getWidth(new String(sequence)) / 2f;
	}
	
	public GuiMainMenu(App<GameSettings> app, Shader s, int t, int[] c) 
	{
		super(app);
		
		colorShader = s;
		time = t;
		color = c;
		
		stackSmashingStartX = 1920 / 2 - gameSettings.getFont().getWidth(new String(sequence)) / 2f;
	}
	
	public void render(double delta)
	{
		super.render(delta);
		
		colorShader.bind();
		
		GL20.glUniform1f(time, (System.currentTimeMillis() % 1024) / 1024f);
		GL20.glUniform1f(color[0], 1f);
		GL20.glUniform1f(color[1], 1f);
		GL20.glUniform1f(color[2], .8f);
		
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
        
		GL11.glColor4d(76 / 255d, 181 / 255d, 8 / 255d, 1d);
        
        if(input.getMouseX() >= 1920 / 2 - gameSettings.getFont().getWidth("START A GAME", 48 / 128f) && input.getMouseX() <= 1920 / 2 + gameSettings.getFont().getWidth("START A GAME", 48f / 128f))
        {
            if(input.getMouseY() >= 600 - 64 && input.getMouseY() <= 600 + 32)
            {
                GL11.glColor4f(0, 0, 0, 1);
                if(input.getMouse(0).isPressed())
                {
                	application.initGui(new GuiPlayerChoose(this, colorShader, time, color));
                }
            }
        }
        if(input.getKey(GLFW.GLFW_KEY_ENTER).isPressed())
        {
        	GL11.glColor4f(0, 0, 0, 1);
        }
        
        gameSettings.getFont().draw("START A GAME", 1920 / 2f - gameSettings.getFont().getWidth("START A GAME", 48f / 128f) / 2, 600f, .5f, 48f / 128f);
        
		GL11.glColor4d(76 / 255d, 181 / 255d, 8 / 255d, 1d);
        
        if(input.getMouseX() >= 1920 / 2 - gameSettings.getFont().getWidth("START A GAME", 48 / 128f) && input.getMouseX() <= 1920 / 2 + gameSettings.getFont().getWidth("START A GAME", 48f / 128f))
        {
            if(input.getMouseY() >= 700 - 64 && input.getMouseY() <= 700 + 32)
            {
                GL11.glColor4f(0, 0, 0, 1);
                if(input.getMouse(0).isPressed())
                {
                	application.initGui(new GuiOnlineChoose(this, colorShader, time, color));
                }
            }
        }
        
        gameSettings.getFont().draw("PLAY ONLINE", 1920 / 2f - gameSettings.getFont().getWidth("PLAY ONLINE", 48f / 128f) / 2, 700f, .5f, 48f / 128f);
        
		gameSettings.getFont().unbind();
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-1, 1, -1, 1, -1, 1);
		GL11.glPopMatrix();
	}

	public void update(double delta)
	{
		super.update(delta);
		
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
		
		if(input.getKey(GLFW.GLFW_KEY_ENTER).wasQuickPressed())
		{
        	application.initGui(new GuiPlayerChoose(this, colorShader, time, color));
		}
	}
	
}
