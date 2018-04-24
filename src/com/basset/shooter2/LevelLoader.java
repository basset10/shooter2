package com.basset.shooter2;

public class LevelLoader {

	public static Block[] loadLevel(float xArg, float yArg, String levelArg, int textureIndexArg){
		Block[] output = new Block[levelArg.replace("\n", "").length()];
		String[] rows = levelArg.split("\n");
		int currentIndex = 0;
		for(float y = 0; y < rows.length; y++){
			for(float x = 0; x < rows[(int)y].length(); x++){
				output[currentIndex] = new Block(xArg + (x*Block.BLOCK_SIZE), yArg + (y*Block.BLOCK_SIZE), 
						Integer.parseInt(rows[(int)y].charAt((int)x) + ""), textureIndexArg, false);
				currentIndex++;
			}
		}
		return output;
	}
	
	/* public static int parseBlockCode(String blockCode) {
		
		int blockNum;
		
		blockNum = blockCode;
		
		return blockNum;
		
		
	}*/
	
}
