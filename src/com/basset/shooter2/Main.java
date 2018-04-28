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

//		camera = new HvlCamera2D(Display.getWidth()/2, Display.getHeight()/2, 0, 1f, HvlCamera2D.ALIGNMENT_CENTER);
		
		player = new Player(1280/2, 720/2);
		blocks = LevelLoader.loadLevel(50, 50, ""
				+ "LUUURLUUUR\n"
				+ "jccArldBck\n"
				+ "jccknnnlBk\n"
				+ "jccknnnnjk\n"
				+ "jccknnnnjk\n"
				+ "jccaUURLbk\n"
				+ "ldddddrldr\n"
				,""
				+ "0000011111\n"
				+ "0000011111\n"
				+ "0000nnn222\n"
				+ "0000nnnn22\n"
				+ "0000nnnn22\n"
				+ "0000000122\n"
				+ "0000000111\n"
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
						Renderer.drawBlock(blocks[i].getxPos(), blocks[i].getyPos(), blocks[i].getPatternIndex(), blocks[i].getTextureIndex());
					}

					Renderer.drawPlayer(player.getxPos(), player.getyPos());
				}	
//			}
//		});
		

		

	}


}
