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

import com.polaris.engine.App;
import com.polaris.engine.gui.GuiScreen;
import com.polaris.engine.render.Shader;

public class GuiWinScreen extends GuiScreen<GameSettings>
{
	
	private Shader backgroundShader;
	private int time;
	private int[] color;
	private Vector3d background;
	private Vector3d player;
	
	public GuiWinScreen(App<GameSettings> app, Shader s, int t, int[] c, Vector3d b, Vector3d p)
	{
		super(app);
		backgroundShader = s;
		time = t;
		color = c;
		background = b;
		player = p;
	}
	
	public void update(double delta)
	{
		super.update(delta);
		
		if(input.getKey(GLFW.GLFW_KEY_ENTER).wasQuickPressed())
		{
			application.initGui(new GuiMainMenu(application, backgroundShader, time, color));
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
		
		backgroundShader.bind();
		GL20.glUniform1f(color[0], (float)background.x);
		GL20.glUniform1f(color[1], (float)background.y);
		GL20.glUniform1f(color[2], (float)background.z);
		GL20.glUniform1f(time, (System.currentTimeMillis() % 1024) / 1024f);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(0, 0);
		GL11.glVertex2d(0, 1080);
		GL11.glVertex2d(1920, 1080);
		GL11.glVertex2d(1920, 0);
		GL11.glEnd();
		backgroundShader.unbind();
		
		GL11.glPushMatrix();
		gameSettings.getFont().bind();
		
		GL11.glColor4f(50 / 255f, 50 / 255f, 50 / 255f, 1f);
		float size =  1.2f + (float)Math.abs((ticksExisted % 2 - 1) / 2);
		gameSettings.getFont().draw("THE WINNER!", 1920 / 2 - gameSettings.getFont().getWidth("THE WINNER!", size) / 2, 100f + gameSettings.getFont().getSize() * size / 2, .5f, size);
		GL11.glPopMatrix();
		gameSettings.getFont().unbind();
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glColor4f(1, 1, 1, 1);
        
        int windowWidth = gameSettings.getWindowWidth();
		int windowHeight = gameSettings.getWindowHeight();
		double ymax = .01 * Math.tan( 60 * Math.PI / 360.0 );
		double ymin = -ymax;
		double xmin = ymin * windowWidth / windowHeight;
		double xmax = ymax * windowWidth / windowHeight;
		
		glViewport(0, 0, windowWidth, windowHeight);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glFrustum( xmin, xmax, ymin, ymax, .01, 20 );
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0, -2, -7);
		GL11.glRotatef((float) ticksExisted * 90, 0, 1, 0);
		GL11.glColor4d(player.x, player.y, player.z, 1f);
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
