package com.basset.shooter2;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.newdawn.slick.Color;

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
		
		hvlDrawQuadc(player.getxPos(), player.getyPos(), Player.PLAYER_SIZE, Player.PLAYER_SIZE, Color.blue);

	}


}
