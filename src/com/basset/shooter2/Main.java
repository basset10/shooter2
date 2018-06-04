package com.basset.shooter2;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawPolygon;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlForceRefresh;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.HvlCamera2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class Main extends HvlTemplateInteg2D {

	public static void main (String args[]){
		new Main();
	}

	public static final boolean DEV_MODE_ENABLED = false;

	public static final float BLOCK_SHADOW_SIZE_X = 60f;
	public static final float BLOCK_SHADOW_SIZE_Y = 20f;
	public static final float BLOCK_SHADOW_OPACITY = 0.3f;

	Player player;
	Block[] blocks;
	HvlCamera2D camera;
	Enemy[] enemies;
	PlayerBullet[] bullets;

	public Main(){
		super(144, 1000, 700, "SHHHHHHHOOOOOOOOOOOOOOOOOOOOTER2", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize(){
		//Level loader needs a chance to initialize it's private variables
		LevelLoader.initialize();

		//Loading the game textures
		getTextureLoader().loadResource("Tileset_Dev");
		getTextureLoader().loadResource("Tileset_Stone");	//This becomes tilesheet index 0 (stone)
		getTextureLoader().loadResource("Tileset_Stone2");	//This becomes tilesheet index 1 (stone ore)
		getTextureLoader().loadResource("Tileset_Tech");	//This becomes tilesheet index 2 (tech lights)
		getTextureLoader().loadResource("Tileset_Tech2");	//This becomes tilesheet index 3 (tech)
		getTextureLoader().loadResource("Tileset_Tech3");	//This becomes tilesheet index 4 (tech vents)
		getTextureLoader().loadResource("Sprite_Tech_Support");
		getTextureLoader().loadResource("Sprite_Ore_1");

		//Instantiate the camera with a preset perspective to be centered around an object.
		camera = new HvlCamera2D(Display.getWidth()/2, Display.getHeight()/2, 0, 1f, HvlCamera2D.ALIGNMENT_CENTER);

		//Instantiate the player and give them a starting location.
		player = new Player(200, 300);

		//Instantiate first enemy
		enemies = new Enemy[] {

				new Enemy(450, 450),
				new Enemy(600, 600),

		};

		//Instantiate Player Bullets
		bullets = new PlayerBullet[] {

				new PlayerBullet()

		};

		//Let's load a level for the player to explore.
		String[] fileLevel = LevelLoader.getLevelFromFile("res\\Level1.txt");
		blocks = LevelLoader.loadLevel(50, 50, 
				fileLevel[0],//The edge map loaded from our text file 
				fileLevel[1],//The tilesheet map loaded from our text file 
				fileLevel[2]);//The collision map loaded from our text file
	}

	@Override
	public void update(float delta){
		//Updating utilities that do things every frame

		if(Keyboard.isKeyDown(Keyboard.KEY_X)) {

			for(int i = 0; i < enemies.length; i++) {
				enemies[i].setHealth(enemies[i].getHealth() - delta);
			}

		}


		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			exit();
		}

		Renderer.update(delta);
		player.update(delta, blocks);

		for(int i = 0; i < bullets.length; i++) {
			bullets[i].update(delta, player);
		}

		for(int i = 0; i < enemies.length; i++) {
			enemies[i].update(delta, blocks, player);
		}

		//Set the camera's location to the player's location
		camera.setPosition(player.getxPos(), player.getyPos());
		camera.doTransform(new HvlAction0() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				//VVVVV Begin camera transform VVVVV
				//(everything after this line is drawn relative to the world)

				//Loop through every block in the level and draw it.
				for(int i = 0; i < blocks.length; i++) {

					//This null check is necessary in case there are empty slots in the level array.
					//E.g. {block0, block1, block2, block3, [], []}
					//> with [] being null
					if(blocks[i] != null) {
						Renderer.drawBlock(blocks[i].getxPos(), blocks[i].getyPos(), blocks[i].getPatternIndex(), blocks[i].getTextureIndex(), blocks[i].getCollidable());
					}
				}
				for(int i = 0; i < blocks.length; i++) {
					//Handling shadows below VVVVV
					if(blocks[i] != null && blocks[i].getCollidable()){
						hvlForceRefresh();
						if(!Block.isBlockNear(blocks[i].getxPos(), blocks[i].getyPos(), 0, 1, blocks, true)){
							if(!Block.isBlockNear(blocks[i].getxPos(), blocks[i].getyPos(), 1, 1, blocks, true)){
								hvlDrawPolygon(blocks[i].getxPos(), blocks[i].getyPos(), new HvlCoord2D[]{
										new HvlCoord2D(-Block.BLOCK_SIZE/2, Block.BLOCK_SIZE/2),
										new HvlCoord2D(Block.BLOCK_SIZE/2, Block.BLOCK_SIZE/2),
										new HvlCoord2D((Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_X, (Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_Y),
										new HvlCoord2D(-(Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_X, (Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_Y),
								}, new Color(0f, 0f, 0f, BLOCK_SHADOW_OPACITY));
							}else{
								hvlDrawPolygon(blocks[i].getxPos(), blocks[i].getyPos(), new HvlCoord2D[]{
										new HvlCoord2D(-Block.BLOCK_SIZE/2, Block.BLOCK_SIZE/2),
										new HvlCoord2D(Block.BLOCK_SIZE/2, Block.BLOCK_SIZE/2),
										new HvlCoord2D((Block.BLOCK_SIZE/2), (Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_Y),
										new HvlCoord2D(-(Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_X, (Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_Y),
								}, new Color(0f, 0f, 0f, BLOCK_SHADOW_OPACITY));
							}
						}
						if(!Block.isBlockNear(blocks[i].getxPos(), blocks[i].getyPos(), 1, 0, blocks, true)){
							if(!Block.isBlockNear(blocks[i].getxPos(), blocks[i].getyPos(), 1, 1, blocks, true)){
								hvlDrawPolygon(blocks[i].getxPos(), blocks[i].getyPos(), new HvlCoord2D[]{
										new HvlCoord2D(Block.BLOCK_SIZE/2, -Block.BLOCK_SIZE/2),
										new HvlCoord2D(Block.BLOCK_SIZE/2, Block.BLOCK_SIZE/2),
										new HvlCoord2D((Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_X, (Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_Y),
										new HvlCoord2D((Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_X, -(Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_Y),
								}, new Color(0f, 0f, 0f, BLOCK_SHADOW_OPACITY));
							}else{
								hvlDrawPolygon(blocks[i].getxPos(), blocks[i].getyPos(), new HvlCoord2D[]{
										new HvlCoord2D(Block.BLOCK_SIZE/2, -Block.BLOCK_SIZE/2),
										new HvlCoord2D(Block.BLOCK_SIZE/2, Block.BLOCK_SIZE/2),
										new HvlCoord2D((Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_X, (Block.BLOCK_SIZE/2)),
										new HvlCoord2D((Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_X, -(Block.BLOCK_SIZE/2) + BLOCK_SHADOW_SIZE_Y),
								}, new Color(0f, 0f, 0f, BLOCK_SHADOW_OPACITY));
							}
						}
					}
					//Handling shadows above ^^^^^
				}

				//Let's draw some random textures to make things look nice
				//drawTestSprites();

				//Draw the player
				Renderer.drawPlayer(player.getxPos(), player.getyPos(), player.getxSpeed(), player.getySpeed(), player.getAcceleration());

				for(int i = 0; i < bullets.length; i++) {
					if(bullets[i] != null) {
						Renderer.drawBullets(player, bullets[i]);
					}
				}

				for(int i = 0; i < enemies.length; i++) {
					Renderer.drawEnemy(enemies[i].getX(), enemies[i].getY(), enemies[i].getAlerted(), player);
					//hvlDrawQuadc(enemies[i].getMovementGoal().x, enemies[i].getMovementGoal().y, 10, 10, Color.pink);
					//hvlDrawLine(enemies[i].getX(), enemies[i].getY(), enemies[i].getMovementGoal().x, enemies[i].getMovementGoal().y, Color.pink);
				}
				//^^^^^ End camera transform ^^^^^
			}
		});










		Renderer.drawCrosshair();

	}

	/**
	 * This temporary experimental method draws a few test images I made
	 * around the level.
	 * 
	 * @author os_reboot
	 */
	public void drawTestSprites(){
		//Draw two supports at pixel-perfect locations
		hvlDrawQuadc(368, 225, 64, 64, getTexture(5));
		hvlDrawQuadc(368, 375, 64, 64, getTexture(5));

		//Draw a rotated support
		hvlRotate(650, 532, -90);
		hvlDrawQuadc(650, 532, 64, 64, getTexture(5));
		hvlResetRotation();

		//Draw a rotated ore sprite
		hvlRotate(128, 222, 40);
		hvlDrawQuadc(128, 222, 42, 42, getTexture(6));
		hvlResetRotation();

		//Draw another rotated ore sprite
		hvlRotate(370, 412, 120);
		hvlDrawQuadc(370, 412, 32, 32, getTexture(6));
		hvlResetRotation();
	}

}
