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
		super(144, 1280, 720, "SHHHHHHHOOOOOOOOOOOOOOOOOOOOTER2", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize(){
		getTextureLoader().loadResource("Tileset_Stone");
		getTextureLoader().loadResource("Tileset_Tech");

		player = new Player(1280/2, 720/2);
		blocks = LevelLoader.loadLevel(100, 100, ""
				+ "0111987113\n"
				+ "8224247\n"
				+ "8242247\n"
				+ "8224427\n"
				+ "8222222222222222222247\n"
				, 0);

		
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
