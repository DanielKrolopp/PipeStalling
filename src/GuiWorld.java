import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glFrustum;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glViewport;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3d;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.polaris.engine.App;
import com.polaris.engine.gui.GuiScreen;
import com.polaris.engine.render.Shader;
import com.polaris.engine.util.MathHelper;

public class GuiWorld extends GuiScreen<GameSettings>
{

	public static World world;

	private Shader backgroundShader;
	private int time;
	private int[] color;

	private Vector3d background;
	private Vector3d blocks;
	private Vector3d[] players;

	private int[] playerCharacters;

	public GuiWorld(App<GameSettings> app, Shader s, int t, int[] c, int count, int ... pc) 
	{
		super(app);
		playerCharacters = pc;

		backgroundShader = s;
		time = t;
		color = c;
	}
	
	public void close()
	{
		super.close();
		backgroundShader.destroy();
	}

	public void init()
	{
		super.init();
		
		players = new Vector3d[playerCharacters.length];
		
		for(int i = 0; i < players.length; i++)
		{
			players[i] = CharacterType.getCharacter(playerCharacters[i]).getColor();
		}
		
		background = genColor(players.length);
		blocks = genColor(players.length);
		
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

		int windowWidth = gameSettings.getWindowWidth();
		int windowHeight = gameSettings.getWindowHeight();
		glViewport(0, 0, windowWidth, windowHeight);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		double ymax = .01 * Math.tan( 60 * Math.PI / 360.0 );
		double ymin = -ymax;
		double xmin = ymin * windowWidth / windowHeight;
		double xmax = ymax * windowWidth / windowHeight;
		glFrustum( xmin, xmax, ymin, ymax, .01, 2000);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glColor4f(1, 1, 1, 1);

		world.render(delta, players, blocks);
	}

	private Vector3d genColor(int iteration)
	{
		boolean foundColor = false;
		int red = 0, green = 0, blue = 0, maxDiff = 0;

		while(!foundColor)
		{
			foundColor = true;
			red = MathHelper.random(256);
			green = MathHelper.random(256);
			blue = MathHelper.random(256);
			if(iteration != players.length)
			{
				maxDiff = Math.max(Math.abs(red - green), Math.abs(red - blue));
				maxDiff = Math.max(maxDiff, Math.abs(green - blue));
				if(maxDiff < 128)
				{
					foundColor = false;
				}
			}
			else if(background != null)
			{
				maxDiff = (int) Math.abs(background.x * 255 - red);
				maxDiff += (int) Math.abs(background.y * 255 - green);
				maxDiff += (int) Math.abs(background.z * 255 - blue);
				if(maxDiff < 275 - players.length * 15)
				{
					foundColor = false;
				}
			}
			else
			{
				maxDiff = Math.max(Math.abs(red - green), Math.abs(red - blue));
				maxDiff = Math.max(maxDiff, Math.abs(green - blue));
				if(maxDiff < 30)
				{
					foundColor = false;
				}
			}
			if(foundColor)
			{
				List<Integer> differences = new ArrayList<Integer>();
				for(int j = 0; j < iteration; j++)
				{
					Vector3d playerColor = players[j];
					maxDiff = (int) Math.abs(playerColor.x * 255 - red);
					maxDiff += (int) Math.abs(playerColor.y * 255 - green);
					maxDiff += (int) Math.abs(playerColor.z * 255 - blue);
					differences.add(new Integer(maxDiff));
					if(maxDiff < 275 - players.length * 15)
					{
						differences.clear();
						foundColor = false;
						j = iteration;
					}
				}
			}
		}
		return new Vector3d(red, green, blue);
	}

}
