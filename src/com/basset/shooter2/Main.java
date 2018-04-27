package com.basset.shooter2;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class Main extends HvlTemplateInteg2D {

	public static void main (String args[]){
		new Main();
	}

	Player player;
	Block[] blocks;

	public Main(){
		super(144, 1000, 700, "SHHHHHHHOOOOOOOOOOOOOOOOOOOOTER2", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize(){
		LevelLoader.initialize();
		getTextureLoader().loadResource("Tileset_Stone");
		getTextureLoader().loadResource("Tileset_Tech");

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
				+ "0000nnn111\n"
				+ "0000nnnn11\n"
				+ "0000nnnn11\n"
				+ "0000000111\n"
				+ "0000000111\n"
				);


	}

	@Override
	public void update(float delta){
		Renderer.update(delta);
		player.update(delta);

		for(int i = 0; i < blocks.length; i++) {

			if(blocks[i] != null) {
				Renderer.drawBlock(blocks[i].getxPos(), blocks[i].getyPos(), blocks[i].getPatternIndex(), blocks[i].getTextureIndex());
			}

		}

		Renderer.drawPlayer(player.getxPos(), player.getyPos());

	}


}
