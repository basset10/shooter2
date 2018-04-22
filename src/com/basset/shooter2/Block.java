package com.basset.shooter2;

public class Block {
	
	public static final float BLOCK_SIZE = 100;

	private float xPos;
	private float yPos;
	private int patternIndex;
	private int textureIndex;
	private boolean collidable;
	
	public Block(float xArg, float yArg, int patternIndexArg, int textureIndexArg, boolean collidableArg) {
		
		xPos = xArg;
		yPos = yArg;
		patternIndex = patternIndexArg;
		collidable = collidableArg;
		textureIndex = textureIndexArg;
		
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
	
	public void setTextureIndex(int indexArg) {
		textureIndex = indexArg;
	}
	
	public int getTextureIndex() {
		return textureIndex;
	}
	
	public boolean getCollidable() {
		return collidable;
	}
	
	
}




