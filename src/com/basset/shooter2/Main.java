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

		player = new Player(1280/2, 720/2);
		blocks = new Block[10];
		
	}

	@Override
	public void update(float delta){
		Renderer.update(delta);
		player.update(delta);

		for(int i = 0; i < blocks.length; i++) {

			if(blocks[i] != null) {
				Renderer.drawBlock(blocks[i].getxPos(), blocks[i].getyPos(), blocks[i].getPatternIndex());
			}

		}

		Renderer.drawPlayer(player.getxPos(), player.getyPos());

	}


}
