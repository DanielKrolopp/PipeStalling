import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glFrustum;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glViewport;

import java.io.File;

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
	
	private String[] names = new String[] {"Loadstar", "Bulbastore", "Jumpernaut", "Mad Adder"};
	private Vector3d[] colors = new Vector3d[] {CharacterType.LOAD.getColor(), CharacterType.STORE.getColor(), CharacterType.JUMP.getColor(),
			CharacterType.ADD.getColor()};
	private int player1 = 0;
	private int player2 = 1;
	
	private double player1Ticks = 0;
	private double player2Ticks = 3;

	public GuiPlayerChoose(GuiMainMenu gui, Shader s, int t) 
	{
		super(gui);
		
		colorShader = s;
		time = t;
	}

	public void update(double delta)
	{
		super.update(delta);
		
		player1Ticks += delta;
		player2Ticks += delta;
		
		int move1 = 0;
		int move2 = 0;
		
		if(gameSettings.getPlayerLeft(0).wasQuickPressed())
		{
			move1--;
		}
		if(gameSettings.getPlayerRight(0).wasQuickPressed())
		{
			move1 ++;
		}
		
		if(gameSettings.getPlayerLeft(1).wasQuickPressed())
		{
			move2 ++;
		}
		if(gameSettings.getPlayerRight(1).wasQuickPressed())
		{
			move2 --;
		}
		
		if(input.getKey(GLFW.GLFW_KEY_ENTER).wasQuickPressed())
		{
			application.initGui(new GuiWorld(application, colorShader, time, 2, player1, player2));
		}
		
		player1 = (player1 + move1);
		if(player1 < 0)
			player1 = 3;
		if(player1 > 3)
			player1 = 0;
		if(player1 == player2)
		{
			player1 = (player1 + move1);
			if(player1 < 0)
				player1 = 3;
			if(player1 > 3)
				player1 = 0;
		}
		player2 = (player2 + move2);
		if(player2 < 0)
			player2 = 3;
		if(player2 > 3)
			player2 = 0;
		if(player2 == player1)
		{
			player2 = (player2 + move2);
			if(player2 < 0)
				player2 = 3;
			if(player2 > 3)
				player2 = 0;
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
		GL20.glUniform1f(time, (System.currentTimeMillis() % 1024) / 1024f);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(0, 0);
		GL11.glVertex2d(0, 1080);
		GL11.glVertex2d(1920, 1080);
		GL11.glVertex2d(1920, 0);
		GL11.glEnd();

		colorShader.unbind();
		
		GL11.glPushMatrix();
		GL11.glColor4d(76 / 255d, 181 / 255d, 8 / 255d, 1d);
		gameSettings.getFont().bind();
		gameSettings.getFont().draw("Player 1", 1920 / 4f - gameSettings.getFont().getWidth("Player 1", .8f) / 2, 200f, .5f, .8f);    
		gameSettings.getFont().draw("Player 2", 1920 * 3 / 4f - gameSettings.getFont().getWidth("Player 2", .8f) / 2, 200f, .5f, .8f);
		GL11.glPushMatrix();
		GL11.glColor4f(50 / 255f, 50 / 255f, 50 / 255f, 1f);
		gameSettings.getFont().draw("VS", 1920 / 2 - gameSettings.getFont().getWidth("VS", 1.2f + (float)Math.abs((ticksExisted % 2 - 1) / 2)) / 2, 500f, .5f, 1.2f + (float)Math.abs((ticksExisted % 2 - 1) / 2));
		GL11.glPopMatrix();
		GL11.glColor4d(colors[player1].x, colors[player1].y, colors[player1].z, 1f);
		gameSettings.getFont().draw(names[player1], 1920 / 4f - gameSettings.getFont().getWidth(names[player1], .6f) / 2, 700f, .5f, .6f);       
		GL11.glColor4d(colors[player2].x, colors[player2].y, colors[player2].z, 1f);
        gameSettings.getFont().draw(names[player2], 1920 * 3 / 4f - gameSettings.getFont().getWidth(names[player2], .6f) / 2, 700f, .5f, .6f);       
        gameSettings.getFont().unbind();

        GL11.glPopMatrix();
        
		int windowWidth = gameSettings.getWindowWidth();
		int windowHeight = gameSettings.getWindowHeight();
		glViewport(0, windowHeight / 2, windowWidth / 2, windowHeight / 2);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		double ymax = .01 * Math.tan( 60 * Math.PI / 360.0 );
		double ymin = -ymax;
		double xmin = ymin * windowWidth / windowHeight;
		double xmax = ymax * windowWidth / windowHeight;
		glFrustum( xmin, xmax, ymin, ymax, .01, 20 );
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glColor4f(1, 1, 1, 1);
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0, -2, -7);
		GL11.glRotatef((float) player1Ticks * 90, 0, 1, 0);
		GL11.glColor4d(colors[player1].x, colors[player1].y, colors[player1].z, 1f);
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		genCube();
		GL11.glColor4f(0, 0, 0, 1);
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		GL11.glLineWidth(1f);
		GL11.glScaled(1.05d, 1.05d, 1.05d);
		genCube();
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		GL11.glPopMatrix();
		
		glViewport(windowWidth / 2, windowHeight / 2, windowWidth / 2, windowHeight / 2);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glFrustum( xmin, xmax, ymin, ymax, .01, 20 );
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0, -2, -7);
		GL11.glRotatef((float) player2Ticks * 90, 0, 1, 0);
		GL11.glColor4d(colors[player2].x, colors[player2].y, colors[player2].z, 1f);
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
