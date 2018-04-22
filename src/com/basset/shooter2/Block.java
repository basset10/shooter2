package com.basset.shooter2;

public class Block {

	float xPos;
	float yPos;
	int patternIndex;
	boolean collidable;
	
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
	
}




