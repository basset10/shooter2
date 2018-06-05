package com.basset.shooter2;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.HvlMath;

public class Enemy {

	public static final float ENEMY_SIZE = 16f;
	public static final float ENEMY_MAX_HEALTH = 100f;
	public static final float ENEMY_MAX_SPEED = 50f;
	public static final float ENEMY_IDLE_TIME = 3f;

	private float x;
	private float y;
	private float direction;
	private float health;
	private boolean alerted;

	private HvlCoord2D moveGoal;
	private float idleTime, oldDirection, alertProgress;

	public Enemy(float xArg, float yArg) {
		x = xArg;
		y = yArg;
		health = ENEMY_MAX_HEALTH;
		alerted = false;
		moveGoal = new HvlCoord2D(xArg, yArg);
		idleTime = 0;
		alertProgress = 0;
	}

	public void update(float delta, Block[] blocks, Player player) {
		if(health < ENEMY_MAX_HEALTH){
			alerted = true;
		}

		if(alerted){
			alertProgress = HvlMath.stepTowards(alertProgress, delta/ENEMY_IDLE_TIME, 1f);
			if(idleTime > ENEMY_IDLE_TIME){
				HvlCoord2D movementNormal = new HvlCoord2D(x - moveGoal.x, y - moveGoal.y);
				movementNormal.normalize();
				movementNormal.mult(ENEMY_MAX_SPEED * delta);
				x = HvlMath.stepTowards(x, Math.abs(movementNormal.x), moveGoal.x);
				y = HvlMath.stepTowards(y, Math.abs(movementNormal.y), moveGoal.y);
				if(!(x == moveGoal.x && y == moveGoal.y)){
					oldDirection = direction = HvlMath.fullRadians(new HvlCoord2D(x, y), moveGoal)*180f/(float)Math.PI;
				}
			}else{
				idleTime += delta;
				direction = HvlMath.lerp(oldDirection, HvlMath.fullRadians(new HvlCoord2D(x, y), moveGoal)*(180f/(float)Math.PI), idleTime/ENEMY_IDLE_TIME);
			}

			if(x == moveGoal.x && y == moveGoal.y){
				idleTime = 0;
				boolean lineOfSightMove = false;
				boolean lineOfSightPlayer = false;
				int maxLoopCount = 100;
				do{
					maxLoopCount--;
					if(maxLoopCount == 0){
						moveGoal = new HvlCoord2D(x, y);
						break;
					}
					int blockIndex = 0;
					do{
						blockIndex = HvlMath.randomInt(blocks.length);
					}while(blocks[blockIndex] == null || blocks[blockIndex].getCollidable());
					float blockX = HvlMath.randomFloatBetween(-Block.BLOCK_SIZE/2, Block.BLOCK_SIZE/2);
					float blockY = HvlMath.randomFloatBetween(-Block.BLOCK_SIZE/2, Block.BLOCK_SIZE/2);
					moveGoal = new HvlCoord2D(blockX + blocks[blockIndex].getxPos(), blockY + blocks[blockIndex].getyPos());
					lineOfSightMove = Block.hasLineOfSight(blocks, new HvlCoord2D(x, y), moveGoal);
					lineOfSightPlayer = Block.hasLineOfSight(blocks, new HvlCoord2D(player.getxPos(), player.getyPos()), moveGoal);
				}while((x == moveGoal.x && y == moveGoal.y) || !lineOfSightMove || !lineOfSightPlayer);
			}
		}
	}

	public float getAlertProgress() {
		return alertProgress;
	}

	public boolean isIdle(){
		return idleTime < ENEMY_IDLE_TIME;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getDirection() {
		return direction;
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

	public HvlCoord2D getMovementGoal(){
		return moveGoal;
	}

}
