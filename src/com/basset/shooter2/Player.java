package com.basset.shooter2;

import org.lwjgl.input.Keyboard;

import com.osreboot.ridhvl.HvlMath;

public class Player {

	public static final float PLAYER_SIZE = 20f;
	public static final int PLAYER_SPEED = 250;
	public static final int PLAYER_ACCELERATION = 100;
	public static final int PLAYER_DECELERATION = 1000;	

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
		
		acceleration = HvlMath.stepTowards(acceleration, delta*90000, ((HvlMath.distance(0, 0, xSpeed, ySpeed)) * PLAYER_ACCELERATION) + 100);


		if(!Keyboard.isKeyDown(Keyboard.KEY_W) && !Keyboard.isKeyDown(Keyboard.KEY_S)) {
						
			ySpeed = HvlMath.stepTowards(ySpeed, delta * PLAYER_DECELERATION, 0);
			
		}

		if(!Keyboard.isKeyDown(Keyboard.KEY_D) && !Keyboard.isKeyDown(Keyboard.KEY_A)) {

			xSpeed = HvlMath.stepTowards(xSpeed, delta * PLAYER_DECELERATION, 0);
			
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			
			ySpeed = HvlMath.stepTowards(ySpeed, acceleration * delta, -PLAYER_SPEED);
		}


		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			
			ySpeed = HvlMath.stepTowards(ySpeed, acceleration * delta, PLAYER_SPEED);
		}


		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			
			xSpeed = HvlMath.stepTowards(xSpeed, acceleration * delta, PLAYER_SPEED);
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			
			xSpeed = HvlMath.stepTowards(xSpeed, acceleration * delta, -PLAYER_SPEED);
		}

		yPos = yPos+(delta * ySpeed);
		xPos = xPos+(delta * xSpeed);

System.out.println(acceleration);

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
