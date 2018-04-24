package com.basset.shooter2;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlCoord2D;

public class Renderer {

	public static final float PLAYER_FOOTSTEP_FREQUENCY = 0.05f,
			PLAYER_FOOTSTEP_SIZE = 8f;
	public static final int PLAYER_FOOTSTEP_COUNT = 10;
	
	public static void update(float delta){
		playerTimer += delta;
		
		playerMoveAmount += delta;
	}

	private static float playerTimer = 0, playerMoveAmount = 0;
	private static ArrayList<HvlCoord2D> playerSteps = new ArrayList<>();
	
	public static void drawPlayer(float xArg, float yArg){
		if(playerMoveAmount > PLAYER_FOOTSTEP_FREQUENCY){
			playerMoveAmount = 0;
			playerSteps.add(new HvlCoord2D(xArg, yArg));
			if(playerSteps.size() > PLAYER_FOOTSTEP_COUNT) playerSteps.remove(0);
		}
		float count = 0;
		for(HvlCoord2D c : playerSteps){
			count++;
			float size = (count - (playerMoveAmount/PLAYER_FOOTSTEP_FREQUENCY))/playerSteps.size();
			hvlDrawQuadc(c.x, c.y, size*PLAYER_FOOTSTEP_SIZE, size*PLAYER_FOOTSTEP_SIZE, Color.blue);
		}
		
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
	
	public static void drawBlock(float xArg, float yArg, int patternIndexArg, int textureIndex){
		float uvx = 1f - (float)(patternIndexArg % 4)*0.25f;
		float uvy = 1f - (float)(patternIndexArg / 4)*0.25f;
		hvlDrawQuadc(xArg, yArg, Block.BLOCK_SIZE, Block.BLOCK_SIZE, uvx, uvy, uvx - 0.25f, uvy - 0.25f, Main.getTexture(textureIndex));
	}

}
