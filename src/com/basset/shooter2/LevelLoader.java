package com.basset.shooter2;

public class LevelLoader {
	
	public static BlockCode[] codes;
	
	public static void initialize() {
		codes = new BlockCode[] {
				new BlockCode('n',-1),
				new BlockCode('L',0),
				new BlockCode('U',1),
				new BlockCode('R',3),
				new BlockCode('A',5),
				new BlockCode('B',6),
				new BlockCode('k',7),
				new BlockCode('j',8),
				new BlockCode('a',9),
				new BlockCode('b',10),
				new BlockCode('l',12),
				new BlockCode('d',14),
				new BlockCode('r',15),
				new BlockCode('c', new int[]{2,4,11,13})
		};
	}
	
	

	public static Block[] loadLevel(float xArg, float yArg, String levelArg, String indexMapArg){
		Block[] output = new Block[levelArg.replace("\n", "").length()];
		String[] rows = levelArg.split("\n");
		String[] rowsIM = indexMapArg.split("\n");
		if(rows.length != rowsIM.length) System.out.println("Level string not equal size to index map string!");
		int currentIndex = 0;
		for(float y = 0; y < rows.length; y++){
			if(rows[(int)y].length() != rowsIM[(int)y].length()) System.out.println("Level string not equal size to index map string!");
			for(float x = 0; x < rows[(int)y].length(); x++){
				int code = parseBlockCode(rows[(int)y].charAt((int)x));
				if(code != -1)
				output[currentIndex] = new Block(xArg + (x*Block.BLOCK_SIZE), yArg + (y*Block.BLOCK_SIZE), 
						code, Integer.parseInt(rowsIM[(int)y].charAt((int)x) + ""), false);
				currentIndex++;
			}
		}
		return output;
	}
	
	private static int parseBlockCode(char blockCode) {
		int number = 0;
		for(int i = 0; i < codes.length; i++){
			if(codes[i].getKey() == blockCode) number = codes[i].getValue();
		}
		return number;
		
		
	}
	
}
