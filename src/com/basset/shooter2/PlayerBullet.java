package com.basset.shooter2;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.painter.HvlCursor;

public class PlayerBullet {

	public static final float BULLET_SIZE = 5f;

	private float bulletX;
	private float bulletY;
	private float bulletSpeedx;
	private float bulletSpeedy;
	private boolean fired;

	public PlayerBullet() {

	}

	public void update(float delta, Player player) {

		if(Mouse.isButtonDown(0)) {

			fired = true;
			
			bulletX = player.getxPos();
			bulletY = player.getyPos();
			
			
			bulletSpeedx = HvlCursor.getCursorX() - (Display.getWidth()/2);
			bulletSpeedy = HvlCursor.getCursorY() - (Display.getHeight()/2);
			
			HvlCoord2D bulletSpeed = new HvlCoord2D(bulletSpeedx, bulletSpeedy);
			
			bulletSpeed.normalize();
						
			bulletSpeedx = bulletSpeed.x * 750;
			bulletSpeedy = bulletSpeed.y * 750;

		}

		if(fired) {

			bulletX = bulletX + (bulletSpeedx * delta);
			bulletY = bulletY + (bulletSpeedy * delta);


		}


	}

	public float getBulletX() {
		return bulletX;
	}

	public void setBulletX(float bulletX) {
		this.bulletX = bulletX;
	}

	public float getBulletY() {
		return bulletY;
	}

	public void setBulletY(float bulletY) {
		this.bulletY = bulletY;
	}

	public float getBulletSpeedx() {
		return bulletSpeedx;
	}

	public void setBulletSpeedx(float bulletSpeedx) {
		this.bulletSpeedx = bulletSpeedx;
	}

	public float getBulletSpeedy() {
		return bulletSpeedy;
	}

	public void setBulletSpeedy(float bulletSpeedy) {
		this.bulletSpeedy = bulletSpeedy;
	}

	public boolean isFired() {
		return fired;
	}

	public void setFired(boolean fired) {
		this.fired = fired;
	}




	//if(Mouse.isButtonDown(0)){


	//System.out.println("Click");
	//}


}


