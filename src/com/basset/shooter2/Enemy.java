package com.basset.shooter2;

import com.osreboot.ridhvl.HvlMath;

public class Enemy {
	
	public static final float ENEMY_SIZE = 16f;
	public static final float ENEMY_MAX_HEALTH = 100f;
	public static final float ENEMY_MAX_SPEED = 50f;
	
	private float x;
	private float y;
	private float health;
	private boolean alerted;
	
	public Enemy(float xArg, float yArg) {
		x = xArg;
		y = yArg;
		health = ENEMY_MAX_HEALTH;
		alerted = false;
	}
	
	public void update(float delta, Block[] blocks, Player player) {
		
		if(health < ENEMY_MAX_HEALTH) {
			
			alerted = true;
			
		}
		
		if(alerted) {
			
			x = HvlMath.stepTowards(x, ENEMY_MAX_SPEED * (delta*3), player.getxPos());
			y = HvlMath.stepTowards(y, ENEMY_MAX_SPEED * (delta*3), player.getyPos());
			
		}
		
		
	}
	
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getHealth() {
		return health;
	}
	
	public boolean getAlerted() {
		return alerted;
	}
	
	public void setAlerted(boolean alertedArg) {
		alerted = alertedArg;
	}
	
	public void setHealth(float healthArg) {
		health = healthArg;
	}
	

}
