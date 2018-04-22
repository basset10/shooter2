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
		blocks[0] = new Block(200, 100, 8, true);
		blocks[1] = new Block(250, 100, 0, true);
		blocks[2] = new Block(300, 100, 15, true);
		blocks[3] = new Block(350, 100, 0, true);
		blocks[4] = new Block(400, 100, 2, true);
		blocks[5] = new Block(450, 100, 0, true);
		blocks[6] = new Block(500, 100, 4, true);
		blocks[7] = new Block(550, 100, 0, true);
		blocks[8] = new Block(600, 100, 9, true);

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
