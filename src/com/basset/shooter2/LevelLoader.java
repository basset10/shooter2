package com.basset.shooter2;

public class LevelLoader {

	public static BlockCode[] codes;

	/**
	 * Populates the 'codes' array with values for translating edge-definition level load strings
	 */
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

	/**
	 * 
	 * Turns a bunch of strings into a 'level' (an array of block objects).
	 * This allows us to use strings of specific characters to create
	 * complex level layouts instead of doing <p>
	 * <code>blocks[1298] = new Block(1500, 2100, 1, 2, false);</code><p>
	 * a thousand times.
	 * 
	 * @param xArg The x-origin of the level (this is added to the x-coord of every block in the level)
	 * @param yArg The y-origin of the level (this is added to the y-coord of every block in the level)
	 * @param levelArg The level edge-definition map
	 * @param indexMapArg The level tilesheet-definition map
	 * @param collisionMapArg The level collision map
	 * @return A complete level (an array of block objects) based off the input parameters
	 * 
	 * @author os_reboot
	 */
	public static Block[] loadLevel(float xArg, float yArg, String levelArg, String indexMapArg, String collisionMapArg){
		//Prepare an output array
		Block[] output = new Block[levelArg.replace("\n", "").length()];
		
		//Split each map into rows for further analysis
		String[] rows = levelArg.split("\n");
		String[] rowsIM = indexMapArg.split("\n");
		String[] rowsCM = collisionMapArg.split("\n");
		
		//Check that rows are equal size
		if(rows.length != rowsIM.length && rows.length != rowsCM.length) System.out.println("Level string not equal size to a map string!");
		
		//Loop through each row
		int currentIndex = 0;
		for(float y = 0; y < rows.length; y++){
			
			//Check that rows are equal size
			if(rows[(int)y].length() != rowsIM[(int)y].length() && rows[(int)y].length() != rowsCM[(int)y].length()) 
				System.out.println("Level string not equal size to a map string!");
			
			//Loop through each column
			for(float x = 0; x < rows[(int)y].length(); x++){
				
				//Separate the block's edge code
				int code = parseBlockCode(rows[(int)y].charAt((int)x));
				
				//Separate the block's collision code
				boolean collision = rowsCM[(int)y].charAt((int)x) == '1' ? true : false;
				
				//Make sure we want to draw the block instead of hiding it (use 'n' to hide blocks in the level maps)
				if(code != -1)
					//Finally create out block object and add it to the output array
					output[currentIndex] = new Block(xArg + (x*Block.BLOCK_SIZE), yArg + (y*Block.BLOCK_SIZE), 
							code, Integer.parseInt(rowsIM[(int)y].charAt((int)x) + ""), collision);
				
				//Go to the next character and loop to create more block objects
				currentIndex++;
			}
		}
		//Output our completed level
		return output;
	}

	/**
	 * Turns a block edge-code into a tilesheet number (i.e. upper-left 
	 * code 'L' becomes int 0 for the upper-left tile in the sheet).
	 * 
	 * @param blockCode
	 * @return
	 */
	private static int parseBlockCode(char blockCode) {
		int number = 0;
		for(int i = 0; i < codes.length; i++){//Loop to find the corresponding code
			
			//If we found the correct code set the output to it's value from the 'codes' array.
			if(codes[i].getKey() == blockCode) number = codes[i].getValue();
		}
		return number;
	}

}
