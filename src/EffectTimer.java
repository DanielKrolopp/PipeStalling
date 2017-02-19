import org.joml.Vector4d;

import com.google.common.base.Stopwatch;

public class EffectTimer {
	private long timer;
	private static final long DURATION = 1 * 1000; //Seconds an effect lasts
	private long expiry;
	private Vector4d rotationVec;
	
	public EffectTimer(){
		timer = System.currentTimeMillis();
	}

	public void update(double delta){
		timer = System.currentTimeMillis();
		if(rotationVec != null){
			if(timer > expiry){
				rotationVec = null;
			}
		}
	}
	/*
	 * Precondition: effect cannot be null
	 * Returns succss of operation
	 */
	public boolean setEffect(Vector4d effect){
		if(rotationVec == null){
			rotationVec = effect;
			expiry = System.currentTimeMillis() + DURATION;
			return true;
		}
		return false;
	}
	
	public Vector4d getEffect(){
		return rotationVec;
	}
}
