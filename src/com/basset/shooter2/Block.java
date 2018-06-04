package com.basset.shooter2;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.HvlMath;

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

	public static boolean hasLineOfSight(Block[] blocks, HvlCoord2D start, HvlCoord2D end){
		boolean output = true;
		for(int i = 0; i < blocks.length; i++){
			if(blocks[i] != null && blocks[i].getCollidable()){
				if(HvlMath.raytrace(start, end, 
						new HvlCoord2D(blocks[i].getxPos() - (BLOCK_SIZE/2), blocks[i].getyPos() - (BLOCK_SIZE/2)),
						new HvlCoord2D(blocks[i].getxPos() + (BLOCK_SIZE/2), blocks[i].getyPos() + (BLOCK_SIZE/2))) != null ||
						HvlMath.raytrace(start, end, 
								new HvlCoord2D(blocks[i].getxPos() - (BLOCK_SIZE/2), blocks[i].getyPos() + (BLOCK_SIZE/2)),
								new HvlCoord2D(blocks[i].getxPos() + (BLOCK_SIZE/2), blocks[i].getyPos() - (BLOCK_SIZE/2))) != null){
					output = false;
				}
			}
		}
		return output;
	}
	
	public static boolean isBlockNear(float x, float y, int relativeX, int relativeY, Block[] blocks, boolean mustBeColldable){
		for(Block b : blocks){
			if(b != null && (!mustBeColldable || b.getCollidable()))
				if(b.getxPos() == x + ((float)relativeX*Block.BLOCK_SIZE) &&
					b.getyPos() == y + ((float)relativeY*Block.BLOCK_SIZE)) return true;
		}
		return false;
	}

}




