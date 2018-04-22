package com.basset.shooter2;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.newdawn.slick.Color;

public class Renderer {

	public static void update(float delta){
		playerTimer += delta;
	}

	private static float playerTimer = 0;
	
	public static void drawPlayer(float xArg, float yArg){
		hvlRotate(xArg, yArg, playerTimer * 100f);
		hvlDrawQuadc(xArg, yArg, Player.PLAYER_SIZE * 1.0f, Player.PLAYER_SIZE  * 1.0f, new Color(0.1f, 0.1f, 1f));
		hvlResetRotation();
		
		hvlRotate(xArg, yArg, -playerTimer * 200f);
		hvlDrawQuadc(xArg, yArg, Player.PLAYER_SIZE * 1.1f, Player.PLAYER_SIZE  * 1.1f, new Color(0.2f, 0.2f, 1f));
		hvlResetRotation();
		
		hvlDrawQuadc(xArg, yArg, 
				Player.PLAYER_SIZE * ((float)Math.sin(playerTimer*5f)*0.2f + 1f), 
				Player.PLAYER_SIZE * ((float)Math.sin(playerTimer*5f)*0.2f + 1f), 
				new Color(0f, 0f, (float)Math.sin(playerTimer*10f)*0.2f + 0.8f));
	}
	
	public static void drawBlock(float xArg, float yArg, float patternIndexArg){
		hvlDrawQuadc(xArg, yArg, Block.BLOCK_SIZE, Block.BLOCK_SIZE, Main.getTexture(0));
	}

}
