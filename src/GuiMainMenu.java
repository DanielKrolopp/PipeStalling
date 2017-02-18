import java.io.File;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.polaris.engine.App;
import com.polaris.engine.gui.GuiScreen;
import com.polaris.engine.render.Shader;
import com.polaris.engine.render.VAO;

public class GuiMainMenu extends GuiScreen<GameSettings>
{

	private VAO vao;
	
	private Shader colorShader;
	private Shader fontShader;
	
	public GuiMainMenu(App<GameSettings> app) 
	{
		super(app);
	}
	
	public void init()
	{
		super.init();
		
		colorShader = Shader.createShader(new File("shaders/colorShader.vert"), new File("shaders/colorShader.frag"));
		fontShader = Shader.createShader(new File("shaders/fontShader.vert"), new File("shaders/fontShader.frag"));
		
		int tex = GL20.glGetUniformLocation(fontShader.getShaderId(), "tex");
		GL20.glUniform1i(tex, 0);
	}
	
	public void render(double delta)
	{
		super.render(delta);
		
		fontShader.bind();
		gameSettings.getFont().bind();
		gameSettings.getFont().draw("Pipe Stalling", 100, 100, 0, 1);
		gameSettings.getFont().unbind();
		fontShader.unbind();
		
		colorShader.bind();
		colorShader.unbind();
	}

}
