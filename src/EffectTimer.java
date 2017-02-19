import org.joml.Vector3d;
import org.joml.Vector4d;

import com.polaris.engine.util.MathHelper;

public class EffectTimer {
	private long timer;
	private static final long DURATION = 1 * 1000; //Seconds an effect lasts
	private long expiry;
	private Vector3d rotationVec = new Vector3d(0);

	public EffectTimer(){
		timer = System.currentTimeMillis();
	}

	public void update(double delta){
		rotationVec.x = MathHelper.getExpValue(rotationVec.x, 0, .5, delta);
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

	public Vector3d getEffect(){
		return rotationVec;
	}
}
