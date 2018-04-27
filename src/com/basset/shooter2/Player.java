package com.basset.shooter2;

import org.lwjgl.input.Keyboard;

import com.osreboot.ridhvl.HvlMath;

public class Player {

	public static final float PLAYER_SIZE = 20f;
	public static final int PLAYER_SPEED = 250;

	private float xPos;
	private float yPos;
	private float xSpeed;
	private float ySpeed;

	public Player(float x, float y) {

		xPos = x;
		yPos = y;

		xSpeed = 0;
		ySpeed = 0;

	}

	public void update(float delta) {
		
		if(!Keyboard.isKeyDown(Keyboard.KEY_W) && !Keyboard.isKeyDown(Keyboard.KEY_S) && !Keyboard.isKeyDown(Keyboard.KEY_D) && !Keyboard.isKeyDown(Keyboard.KEY_A)) {
		
			ySpeed = HvlMath.stepTowards(ySpeed, delta * 1750, 0);
			xSpeed = HvlMath.stepTowards(xSpeed, delta * 1750, 0);
			
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			ySpeed = HvlMath.stepTowards(ySpeed, delta * 2000, -PLAYER_SPEED);
		}
	


		 if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			ySpeed = HvlMath.stepTowards(ySpeed, delta * 2000, PLAYER_SPEED);
		}


		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xSpeed = HvlMath.stepTowards(xSpeed, delta * 2000, PLAYER_SPEED);
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xSpeed = HvlMath.stepTowards(xSpeed, delta * 2000, -PLAYER_SPEED);
		}

		yPos = yPos+(delta * ySpeed);
		xPos = xPos+(delta * xSpeed);

		
	
	}

	public float getxPos(){
		return xPos;
	}

	/*public void setxPos(float xArg) {
		xPos = xArg;
	}*/

	public float getyPos() {
		return yPos;
	}

	/*public void setyPos(float yArg) {
		yPos = yArg;
	}*/

	public float getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(float xArg) {
		xSpeed = xArg;
	}

	public float getySpeed() {
		return ySpeed;
	}

	public void setySpeed(float yArg) {
		ySpeed = yArg;
	}

}
