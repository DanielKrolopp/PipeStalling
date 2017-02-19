import org.joml.Vector3d;
import org.joml.Vector4d;

import com.polaris.engine.util.MathHelper;

public class EffectTimer 
{
	private Vector4d rotationVec = new Vector4d(0, 0, 0, 1);

	public void update(double delta){
		rotationVec.x = MathHelper.getExpValue(rotationVec.x, 0, .5, delta);
		rotationVec.y = MathHelper.getExpValue(rotationVec.y, 0, .5, delta);
		rotationVec.z = MathHelper.getExpValue(rotationVec.z, 0, .5, delta);
		
		rotationVec.x = MathHelper.getLinearValue(rotationVec.x, 0, .1, delta);
		rotationVec.y = MathHelper.getLinearValue(rotationVec.y, 0, .1, delta);
		rotationVec.z = MathHelper.getLinearValue(rotationVec.z, 0, .1, delta);
	}
	/*
	 * Precondition: effect cannot be null
	 * Returns succss of operation
	 */
	public boolean setEffect(Vector3d effect){
		rotationVec.x = Math.max(effect.x, rotationVec.x);
		rotationVec.y = Math.max(effect.y, rotationVec.y);
		rotationVec.z = Math.max(effect.z, rotationVec.z);
		return false;
	}

	public Vector4d getEffect(){
		return rotationVec;
	}
}
