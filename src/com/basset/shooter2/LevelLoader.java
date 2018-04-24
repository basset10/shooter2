package com.basset.shooter2;

public class LevelLoader {
	
	public static BlockCode[] codes;
	
	public static void initialize() {
		
		codes = new BlockCode[] {
				new BlockCode('L',0),
				new BlockCode('U',1),
				new BlockCode('R',3),
				new BlockCode('A',5),
				new BlockCode('B',6),
				new BlockCode('2',7),
				new BlockCode('1',8),
				new BlockCode('a',9),
				new BlockCode('b',10),
				new BlockCode('l',12),
				new BlockCode('d',14),
				new BlockCode('r',15),
				new BlockCode('c', new int[]{2,4,11,13})

		};
		
		
	}
	
	

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
	
	private static int parseBlockCode(char blockCode) {
		
		char blockNum;
		
		blockNum = blockCode;
		
		return blockNum;
		
		
	}
	
}
