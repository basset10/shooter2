package com.basset.shooter2;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.HvlMath;

public class Player {

	public static final float PLAYER_SIZE = 20f;
	public static int maxSpeed = 260;
	public static final int PLAYER_ACCELERATION = 120;
	public static final int PLAYER_DECELERATION = 1100;	

	private float xPos;
	private float yPos;
	private float xSpeed;
	private float ySpeed;
	private float acceleration = 1.00f;

	public Player(float x, float y) {

		xPos = x;
		yPos = y;

		xSpeed = 0;
		ySpeed = 0;

	}

	public void update(float delta) {
		
		acceleration = HvlMath.stepTowards(acceleration, delta*97500, ((HvlMath.distance(0, 0, xSpeed, ySpeed)) * PLAYER_ACCELERATION) + 100);
		
		
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			maxSpeed = 400;
		}else {
			maxSpeed = 260;
		}

		if(!Keyboard.isKeyDown(Keyboard.KEY_W) && !Keyboard.isKeyDown(Keyboard.KEY_S)) {
			ySpeed = HvlMath.stepTowards(ySpeed, delta * PLAYER_DECELERATION, 0);
		}

		if(!Keyboard.isKeyDown(Keyboard.KEY_D) && !Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xSpeed = HvlMath.stepTowards(xSpeed, delta * PLAYER_DECELERATION, 0);
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			ySpeed = HvlMath.stepTowards(ySpeed, acceleration * delta, -maxSpeed);
		}


		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			ySpeed = HvlMath.stepTowards(ySpeed, acceleration * delta, maxSpeed);
		}


		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xSpeed = HvlMath.stepTowards(xSpeed, acceleration * delta, maxSpeed);
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xSpeed = HvlMath.stepTowards(xSpeed, acceleration * delta, -maxSpeed);
		}

		
		yPos = HvlMath.limit(yPos+(delta * ySpeed), 0, Display.getHeight());
		xPos = HvlMath.limit(xPos+(delta * xSpeed), 0, Display.getWidth());

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
