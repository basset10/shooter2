package com.basset.shooter2;

public class Block {
	
	public static final float BLOCK_SIZE = 64;

	private float xPos;
	private float yPos;
	private int patternIndex;
	private boolean collidable;
	
	public Block(float xArg, float yArg, int patternIndexArg, boolean collidableArg) {
		
		xPos = xArg;
		yPos = yArg;
		patternIndex = patternIndexArg;
		collidable = collidableArg;
		
	}
	
	public float getxPos() {
		return xPos;
	}
	
	public void setxPos(float xArg) {
		xPos = xArg;
	}
	
	public float getyPos() {
		return yPos;
	}
	
	public void setyPos(float yArg) {
		yPos = yArg;
	}
	
	public void setPatternIndex(int indexArg) {
		patternIndex = indexArg;
	}
	
	public int getPatternIndex() {
		return patternIndex;
	}
	
	
	
}




