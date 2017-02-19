import org.joml.Vector4d;

import com.google.common.base.Stopwatch;
import com.polaris.engine.util.MathHelper;

public class EffectTimer {
	private long timer;
	private static final long DURATION = 1 * 1000; //Seconds an effect lasts
	private long expiry;
	private Vector4d rotationVec = new Vector4d(0);
	
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
	public boolean setEffect(Vector4d effect){
		if(MathHelper.isEqual(rotationVec.x, 0, .1)){
			rotationVec.x = effect.x;
			expiry = System.currentTimeMillis() + DURATION;
			return true;
		}
		return false;
	}
	
	public Vector4d getEffect(){
		if(rotationVec == null)
		{
			return new Vector4d(0);
		}
		return rotationVec;
	}
}
