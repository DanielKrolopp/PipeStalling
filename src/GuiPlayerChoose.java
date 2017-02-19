 import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glFrustum;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glViewport;

import org.joml.Vector3d;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.polaris.engine.gui.GuiScreen;
import com.polaris.engine.render.Shader;

public class GuiPlayerChoose extends GuiScreen<GameSettings>
{

	private Shader colorShader;
	private int time;
	private int[] color;
	
	private String[] names = new String[] {"Loadstar", "Bulbastore", "Jumpernaut", "Mad Adder"};
	private Vector3d[] colors = new Vector3d[] {CharacterType.LOAD.getColor(), CharacterType.STORE.getColor(), CharacterType.JUMP.getColor(),
			CharacterType.ADD.getColor()};
	
	private int[] players;
	private double[] playerTicks;
	
	public GuiPlayerChoose(GuiMainMenu gui, Shader s, int t, int[] c) 
	{
		super(gui);
		
		colorShader = s;
		time = t;
		color = c;
		
		players = new int[] {0, 1};
		playerTicks = new double[] {0, 1};
	}
	
	public void close()
	{
		super.close();
	}

	public void update(double delta)
	{
		super.update(delta);
		
		for(int i = 0; i < players.length; i++)
		{
			playerTicks[i] += delta;
			if(gameSettings.getPlayerLeft(i).wasQuickPressed())
			{
				players[i] --;
			}
			
			if(gameSettings.getPlayerRight(i).wasQuickPressed())
			{
				players[i] ++;
			}
			
			if(players[i] < 0)
				players[i] = 3;
			if(players[i] > 3)
				players[i] = 0;
		}
		
		if(input.getKey(GLFW.GLFW_KEY_ENTER).wasQuickPressed())
		{
			boolean flag = true;
			for(int i = 0; i < players.length; i++)
			{
				for(int j = i + 1; j < players.length; j++)
				{
					if(players[i] == players[j])
						flag = false;
				}
			}
			if(flag)
				application.initGui(new GuiWorld(application, colorShader, time, color, players));
		}
		
		if(input.getKey(GLFW.GLFW_KEY_SPACE).wasQuickPressed())
		{
			int length = players.length == 4 ? 2 : players.length + 1;
			players = new int[length];
			playerTicks = new double[length];
			for(int i = 0; i < players.length; i ++)
			{
				players[i] = i;
				playerTicks[i] = i;
			}
		}
	}

	public void render(double delta)
	{
		super.render(delta);
		
		GL11.glPushMatrix();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1920, 1080, 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		
		colorShader.bind();
		GL20.glUniform1f(color[0], 1f);
		GL20.glUniform1f(color[1], 1f);
		GL20.glUniform1f(color[2], .8f);
		GL20.glUniform1f(time, (System.currentTimeMillis() % 1024) / 1024f);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(0, 0);
		GL11.glVertex2d(0, 1080);
		GL11.glVertex2d(1920, 1080);
		GL11.glVertex2d(1920, 0);
		GL11.glEnd();

		colorShader.unbind();
		
		GL11.glPushMatrix();
		
		GL11.glPushMatrix();
		gameSettings.getFont().bind();
		
		GL11.glColor4f(50 / 255f, 50 / 255f, 50 / 255f, 1f);
		float size =  1.2f + (float)Math.abs((ticksExisted % 2 - 1) / 2);
		gameSettings.getFont().draw("VS", 1920 / 2 - gameSettings.getFont().getWidth("VS", size) / 2, 500f + gameSettings.getFont().getSize() * size / 2, .5f, size);
		GL11.glPopMatrix();
		
		GL11.glColor4d(70 / 255d, 70 / 255d, 70 / 255d, 1d);
		for(int i = 0; i < players.length; i++)
		{
			gameSettings.getFont().draw("Player " + (i + 1), 1920 * (i % 2 * 2 + 1) / 4f - gameSettings.getFont().getWidth("Player " + (i + 1), .8f) / 2, 100f + (i / 2) * 900, .5f, .8f);
		}
		
		for(int i = 0; i < players.length; i++)
		{
			GL11.glColor4d(colors[players[i]].x, colors[players[i]].y, colors[players[i]].z, 1f);
			gameSettings.getFont().draw(names[players[i]], 1920 * (i % 2 * 2 + 1) / 4f - gameSettings.getFont().getWidth(names[players[i]], .6f) / 2, 500f + (i / 2) * 100, .5f, .6f);       
		}
		//gameSettings.getFont().draw("Player 1", 1920 / 4f - gameSettings.getFont().getWidth("Player 1", .8f) / 2, 200f, .5f, .8f);    
		//gameSettings.getFont().draw("Player 2", 1920 * 3 / 4f - gameSettings.getFont().getWidth("Player 2", .8f) / 2, 200f, .5f, .8f);
		
		//gameSettings.getFont().draw(names[player1], 1920 / 4f - gameSettings.getFont().getWidth(names[player1], .6f) / 2, 700f, .5f, .6f);       
		//GL11.glColor4d(colors[player2].x, colors[player2].y, colors[player2].z, 1f);
        //gameSettings.getFont().draw(names[player2], 1920 * 3 / 4f - gameSettings.getFont().getWidth(names[player2], .6f) / 2, 700f, .5f, .6f);       
        gameSettings.getFont().unbind();

        GL11.glPopMatrix();
        
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glColor4f(1, 1, 1, 1);
        
        int windowWidth = gameSettings.getWindowWidth();
		int windowHeight = gameSettings.getWindowHeight();
		double ymax = .01 * Math.tan( 60 * Math.PI / 360.0 );
		double ymin = -ymax;
		double xmin = ymin * windowWidth / windowHeight;
		double xmax = ymax * windowWidth / windowHeight;
		
        for(int i = 0; i < players.length; i++)
        {
    		glViewport(windowWidth / 12 + (windowWidth / 2) * (i % 2), windowHeight * 2 / 3 - (int)(windowHeight / 2.1f * (int)(i / 2)), windowWidth / 3, windowHeight / 3);
    		glMatrixMode(GL_PROJECTION);
    		glLoadIdentity();
    		glFrustum( xmin, xmax, ymin, ymax, .01, 20 );
    		glMatrixMode(GL_MODELVIEW);
    		glLoadIdentity();
    		
    		GL11.glPushMatrix();
    		GL11.glTranslatef(0, -2, -7);
    		GL11.glRotatef((float) playerTicks[i] * 90, 0, 1, 0);
    		GL11.glColor4d(colors[players[i]].x, colors[players[i]].y, colors[players[i]].z, 1f);
    		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
    		genCube();
    		GL11.glColor4f(0, 0, 0, 1);
    		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
    		GL11.glLineWidth(1f);
    		GL11.glScaled(1.05d, 1.05d, 1.05d);
    		genCube();
    		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
    		GL11.glPopMatrix();
        }
	}
	
	public void genCube()
	{
		GL11.glBegin(GL11.GL_QUADS);                // Begin drawing the color cube with 6 quads
		// Top face (y = 1.0f)
		// Define vertices in counter-clockwise (CCW) order with normal pointing out
		GL11.glVertex3f( 1.0f, 1.0f, -1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
		GL11. glVertex3f(-1.0f, 1.0f,  1.0f);
		GL11.glVertex3f( 1.0f, 1.0f,  1.0f);

		// Front face  (z = 1.0f)
		GL11.glVertex3f( 1.0f,  1.0f, 1.0f);
		GL11.glVertex3f(-1.0f,  1.0f, 1.0f);
		GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
		GL11.glVertex3f( 1.0f, -1.0f, 1.0f);

		// Back face (z = -1.0f)
		GL11.glVertex3f( 1.0f, -1.0f, -1.0f);
		GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
		GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
		GL11.glVertex3f( 1.0f,  1.0f, -1.0f);

		// Left face (x = -1.0f)    // Blue
		GL11.glVertex3f(-1.0f,  1.0f,  1.0f);
		GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
		GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
		GL11.glVertex3f(-1.0f, -1.0f,  1.0f);

		// Right face (x = 1.0f)  // Magenta
		GL11.glVertex3f(1.0f,  1.0f, -1.0f);
		GL11.glVertex3f(1.0f,  1.0f,  1.0f);
		GL11.glVertex3f(1.0f, -1.0f,  1.0f);
		GL11.glVertex3f(1.0f, -1.0f, -1.0f);
		GL11.glEnd();  // End of drawing color-cube
	}

}
