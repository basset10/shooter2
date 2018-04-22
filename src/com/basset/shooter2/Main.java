package com.basset.shooter2;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class Main extends HvlTemplateInteg2D {

	public static void main (String args[]){
		new Main();
	}
	
	Player player;

	public Main(){
		super(144, 1280, 720, "SHHHHHHHOOOOOOOOOOOOOOOOOOOOTER2", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize(){
		
		player = new Player(1280/2, 720/2);

	}

	@Override
	public void update(float delta){
		Renderer.update(delta);
		player.update(delta);
		
		Renderer.drawPlayer(player.getxPos(), player.getyPos());

	}


}
