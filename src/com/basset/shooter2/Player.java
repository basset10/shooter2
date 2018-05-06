package com.basset.shooter2;

import org.lwjgl.input.Keyboard;

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

	public void update(float delta, Block[] blocks) {
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

		//VVVVV Handling collision below VVVVV
		
		//Calculate the player's new position
		float newyPos = yPos + (delta * ySpeed);
		float newxPos = xPos + (delta * xSpeed);
		
		for(Block b : blocks){
			if(b.getCollidable()){
				
				//If the player will collide with the block when their position is updated
				if(newyPos >= b.getyPos() - (Block.BLOCK_SIZE/2) && newyPos <= b.getyPos() + (Block.BLOCK_SIZE/2) && 
						newxPos >= b.getxPos() - (Block.BLOCK_SIZE/2) && newxPos <= b.getxPos() + (Block.BLOCK_SIZE/2)){
					
					//Variables for block's proximity to other blocks
					boolean above = checkForCollidableBlock(b.getxPos(), b.getyPos(), 0, -1, blocks);
					boolean below = checkForCollidableBlock(b.getxPos(), b.getyPos(), 0, 1, blocks);
					boolean left = checkForCollidableBlock(b.getxPos(), b.getyPos(), -1, 0, blocks);
					boolean right = checkForCollidableBlock(b.getxPos(), b.getyPos(), 1, 0, blocks);
					
					//Variables for player's proximity to the block
					boolean plRight = xPos >= b.getxPos() + (Block.BLOCK_SIZE/2);
					boolean plLeft = xPos <= b.getxPos() - (Block.BLOCK_SIZE/2);
					boolean plBelow = yPos >= b.getyPos() + (Block.BLOCK_SIZE/2);
					boolean plAbove = yPos <= b.getyPos() - (Block.BLOCK_SIZE/2);
					
					//Handling head-on face collisions
					if(plRight && !plLeft && !plBelow && !plAbove){//FLAT RIGHT COLLISION
						xSpeed = Math.max(0, xSpeed);
						newxPos = b.getxPos() + (Block.BLOCK_SIZE/2);
					}else if(!plRight && plLeft && !plBelow && !plAbove){//FLAT LEFT COLLISION
						xSpeed = Math.min(0, xSpeed);
						newxPos = b.getxPos() - (Block.BLOCK_SIZE/2);
					}else if(!plRight && !plLeft && plBelow && !plAbove){//FLAT BELOW COLLISION
						ySpeed = Math.max(0, ySpeed);
						newyPos = b.getyPos() + (Block.BLOCK_SIZE/2);
					}else if(!plRight && !plLeft && !plBelow && plAbove){//FLAT ABOVE COLLISION
						ySpeed = Math.min(0, ySpeed);
						newyPos = b.getyPos() - (Block.BLOCK_SIZE/2);
					}
					
					//Handling corner collisions
					if(plRight && plAbove){
						if(right){
							ySpeed = Math.min(0, ySpeed);
							newyPos = b.getyPos() - (Block.BLOCK_SIZE/2);
						}
						if(above){
							xSpeed = Math.max(0, xSpeed);
							newxPos = b.getxPos() + (Block.BLOCK_SIZE/2);
						}
						if(!right && !above){
							ySpeed = Math.min(0, ySpeed);
							newyPos = b.getyPos() - (Block.BLOCK_SIZE/2);
						}
					}
					if(plRight && plBelow){
						if(right){
							ySpeed = Math.max(0, ySpeed);
							newyPos = b.getyPos() + (Block.BLOCK_SIZE/2);
						}
						if(below){
							xSpeed = Math.max(0, xSpeed);
							newxPos = b.getxPos() + (Block.BLOCK_SIZE/2);
						}
						if(!right && !below){
							ySpeed = Math.max(0, ySpeed);
							newyPos = b.getyPos() + (Block.BLOCK_SIZE/2);
						}
					}
					if(plLeft && plAbove){
						if(left){
							ySpeed = Math.min(0, ySpeed);
							newyPos = b.getyPos() - (Block.BLOCK_SIZE/2);
						}
						if(above){
							xSpeed = Math.min(0, xSpeed);
							newxPos = b.getxPos() - (Block.BLOCK_SIZE/2);
						}
						if(!left && !above){
							ySpeed = Math.min(0, ySpeed);
							newyPos = b.getyPos() - (Block.BLOCK_SIZE/2);
						}
					}
					if(plLeft && plBelow){
						if(left){
							ySpeed = Math.max(0, ySpeed);
							newyPos = b.getyPos() + (Block.BLOCK_SIZE/2);
						}
						if(below){
							xSpeed = Math.min(0, xSpeed);
							newxPos = b.getxPos() - (Block.BLOCK_SIZE/2);
						}
						if(!left && !below){
							ySpeed = Math.max(0, ySpeed);
							newyPos = b.getyPos() + (Block.BLOCK_SIZE/2);
						}
					}
				}
			}
		}
		
		//Finally update the player's position based on collision-recalculated values
		yPos = newyPos;
		xPos = newxPos;

	}

	private boolean checkForCollidableBlock(float x, float y, int relativeX, int relativeY, Block[] blocks){
		for(Block b : blocks){
			if(b.getCollidable())
				if(b.getxPos() == x + ((float)relativeX*Block.BLOCK_SIZE) &&
					b.getyPos() == y + ((float)relativeY*Block.BLOCK_SIZE)) return true;
		}
		return false;
	}

	public float getxPos(){
		return xPos;
	}

	public float getyPos() {
		return yPos;
	}

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

	public float getAcceleration() {
		return acceleration;
	}

}
