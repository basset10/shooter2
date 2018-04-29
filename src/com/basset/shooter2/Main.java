package com.basset.shooter2;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class Main extends HvlTemplateInteg2D {

	public static void main (String args[]){
		new Main();
	}

	Player player;
	Block[] blocks;
//	HvlCamera2D camera;

	public Main(){
		super(144, 1000, 700, "SHHHHHHHOOOOOOOOOOOOOOOOOOOOTER2", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize(){
		LevelLoader.initialize();
		getTextureLoader().loadResource("Tileset_Stone");
		getTextureLoader().loadResource("Tileset_Tech");
		getTextureLoader().loadResource("Tileset_Tech2");
		getTextureLoader().loadResource("Tileset_Tech3");

//		camera = new HvlCamera2D(Display.getWidth()/2, Display.getHeight()/2, 0, 1f, HvlCamera2D.ALIGNMENT_CENTER);
		
		
		
		player = new Player(1280/2, 720/2);
		
		/*
		 * This is a complete outward-facing square in edge-definition layout syntax:
		 * LUUUR
		 * jccck
		 * jccck
		 * jccck
		 * ldddr
		 * 
		 * And inward:
		 * ccccc
		 * cAdBc
		 * ck jc
		 * caUbc
		 * ccccc
		 * 
		 * The LevelLoader maps go in the following order:
		 * 1. Edge-definition
		 * 2. Tileset selection
		 * 3. Collision definition
		 */
		blocks = LevelLoader.loadLevel(50, 50, ""
				+ "LUUURLUUUR\n"
				+ "jccArldBck\n"
				+ "jLRkLURlBk\n"
				+ "jlrkjcaRjk\n"
				+ "jccklddrjk\n"
				+ "ldBaUURLbk\n"
				+ "URldddrldr\n"
				,""
				+ "0000031221\n"
				+ "0000032222\n"
				+ "0330232233\n"
				+ "0330232222\n"
				+ "0000223232\n"
				+ "0000000123\n"
				+ "0000000121\n"
				,""
				+ "0000000000\n"
				+ "0000000000\n"
				+ "0000111000\n"
				+ "0000111100\n"
				+ "0000111100\n"
				+ "0000000000\n"
				+ "1100000000\n"
				);


	}

	@Override
	public void update(float delta){
		Renderer.update(delta);
		player.update(delta);
//		camera.setPosition(player.getxPos(), player.getyPos());

//		camera.doTransform(new HvlAction0() {
//			@Override
//			public void run() {
				for(int i = 0; i < blocks.length; i++) {

					if(blocks[i] != null) {
						Renderer.drawBlock(blocks[i].getxPos(), blocks[i].getyPos(), blocks[i].getPatternIndex(), blocks[i].getTextureIndex(), blocks[i].getCollidable());
					}

					Renderer.drawPlayer(player.getxPos(), player.getyPos());
				}	
//			}
//		});
		

		

	}


}
