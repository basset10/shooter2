package com.basset.shooter2;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotatea;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.painter.HvlCursor;

public class Renderer {

	public static final float PLAYER_FOOTSTEP_FREQUENCY = 0.05f,
			PLAYER_FOOTSTEP_SIZE = 8f;
	public static final int PLAYER_FOOTSTEP_COUNT = 10;

	public static void update(float delta){

		playerTimer += delta;

		playerMoveAmount += delta;
		
		opacity = HvlMath.stepTowards(opacity, delta * 1.3f, opacityGoal);
	}
	private static float opacity = 0;
	private static float opacityGoal = 0;
	private static float playerTimer = 0, playerMoveAmount = 0;
	private static ArrayList<HvlCoord2D> playerSteps = new ArrayList<>();

	public static void drawPlayer(float xArg, float yArg, float xSpeedArg, float ySpeedArg, float accelerationArg){

		opacityGoal = Math.max(Math.abs(xSpeedArg), Math.abs(ySpeedArg));
		opacityGoal = HvlMath.mapl(opacityGoal, 260, 400, 0, 1);



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



			hvlDrawQuadc(c.x, c.y, size*PLAYER_FOOTSTEP_SIZE, size*PLAYER_FOOTSTEP_SIZE, new Color(1.0f, 0.0f, 0.0f, opacity));


		}



		hvlRotate(xArg, yArg, playerTimer * 100f);

		hvlDrawQuadc(xArg, yArg, Player.PLAYER_SIZE * 1.0f, Player.PLAYER_SIZE  * 1.0f, new Color(0.1f, 0.1f, 1f));
		hvlDrawQuadc(xArg, yArg, Player.PLAYER_SIZE * 1.0f, Player.PLAYER_SIZE  * 1.0f, new Color(1.0f, 0.1f, 0.1f, opacity));

		hvlResetRotation();


		hvlRotate(xArg, yArg, -playerTimer * 200f);
		hvlDrawQuadc(xArg, yArg, Player.PLAYER_SIZE * 1.1f, Player.PLAYER_SIZE  * 1.1f, new Color(0.2f, 0.2f, 1f));
		hvlDrawQuadc(xArg, yArg, Player.PLAYER_SIZE * 1.1f, Player.PLAYER_SIZE  * 1.1f, new Color(1.0f, 0.2f, 0.2f, opacity));
		hvlResetRotation();





		hvlDrawQuadc(xArg, yArg, 
				Player.PLAYER_SIZE * ((float)Math.sin(playerTimer*5f)*0.2f + 1f), 
				Player.PLAYER_SIZE * ((float)Math.sin(playerTimer*5f)*0.2f + 1f),				
				new Color(0f, 0f, (float)Math.sin(playerTimer*10f)*0.2f + 0.8f));


		hvlDrawQuadc(xArg, yArg, 
				Player.PLAYER_SIZE * ((float)Math.sin(playerTimer*5f)*0.2f + 1f), 
				Player.PLAYER_SIZE * ((float)Math.sin(playerTimer*5f)*0.2f + 1f),				
				new Color((float)Math.sin(playerTimer*10f)*0.2f + 0.8f, 0f, 0f, opacity));
		
		
		
		
		//Draw directional indicator
		
		hvlDrawQuadc(500, 500, 8, 20, Color.green);
		
		
		


	}

	public static void drawBlock(float xArg, float yArg, int patternIndexArg, int textureIndex, boolean collision){
		//Set the UV based on the specific tile needed to draw (edge tiles, core tiles, etc.)
		float uvx = 1f - (float)(patternIndexArg % 4)*0.25f;
		float uvy = 1f - (float)(patternIndexArg / 4)*0.25f;
		
		//Re-color block for dev mode
		Color color = Color.white;
		if(Main.DEV_MODE_ENABLED){
			java.awt.Color color2 = java.awt.Color.getHSBColor(((float)textureIndex * 0.12345f)%1.0f, 0.7f, 0.5f);
			color = new Color(color2.getRed(), color2.getGreen(), color2.getBlue());
		}
		textureIndex = Main.DEV_MODE_ENABLED ? 0 : textureIndex + 1;
		
		//Shade block if collision is enabled
		float shadeAmount = Main.DEV_MODE_ENABLED ? 0.45f : 0.15f;
		if(collision) color = new Color(color.r * shadeAmount, color.g * shadeAmount, color.b * shadeAmount);
		
		//Finally draw the block
		hvlDrawQuadc(xArg, yArg, Block.BLOCK_SIZE, Block.BLOCK_SIZE, uvx, uvy, uvx - 0.25f, uvy - 0.25f, Main.getTexture(textureIndex), color);
	}

	public static void drawCrosshair(){
		
		//hvlRotate(HvlCursor.getCursorX(), HvlCursor.getCursorY(), playerTimer * 50);
		

		//Top section
		hvlDrawQuadc(HvlCursor.getCursorX(), HvlCursor.getCursorY() - (8 + ((float)Math.sin(playerTimer*10)*3)), 4, 12, Color.black);
		hvlDrawQuadc(HvlCursor.getCursorX(), HvlCursor.getCursorY() - (8 + ((float)Math.sin(playerTimer*10)*3)), 2, 10, Color.white);
		
		//Right section
		hvlDrawQuadc(HvlCursor.getCursorX() + (8 + ((float)Math.sin(playerTimer*10)*3)), HvlCursor.getCursorY(), 12, 4, Color.black);
		hvlDrawQuadc(HvlCursor.getCursorX() + (8 + ((float)Math.sin(playerTimer*10)*3)), HvlCursor.getCursorY(), 10, 2, Color.white);
		
		//Left section
		hvlDrawQuadc(HvlCursor.getCursorX() - (8 + ((float)Math.sin(playerTimer*10)*3)), HvlCursor.getCursorY(), 12, 4, Color.black);
		hvlDrawQuadc(HvlCursor.getCursorX() - (8 + ((float)Math.sin(playerTimer*10)*3)), HvlCursor.getCursorY(), 10, 2, Color.white);
		
		//Bottom section
		hvlDrawQuadc(HvlCursor.getCursorX(), HvlCursor.getCursorY() + (8 + ((float)Math.sin(playerTimer*10)*3)), 4, 12, Color.black);
		hvlDrawQuadc(HvlCursor.getCursorX(), HvlCursor.getCursorY() + (8 + ((float)Math.sin(playerTimer*10)*3)), 2, 10, Color.white);
		
		//hvlResetRotation();
		
		HvlCursor.setNativeHidden(true);
		
	}
	

}
