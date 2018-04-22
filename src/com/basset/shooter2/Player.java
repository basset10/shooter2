package com.basset.shooter2;

public class Player {
	
	public static final float PLAYER_SIZE = 20f;

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
