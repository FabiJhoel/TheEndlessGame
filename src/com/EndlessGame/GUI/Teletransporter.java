package com.EndlessGame.GUI;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Teletransporter{
	
	Bitmap teletImage;
	private static int leftRoadLimit = -1;
	private int width;
	private int height;
	private int speed;
	private int coordX;
	private int coordY;
	BackGroundMoveable background;

	public Teletransporter(BackGroundMoveable pBackground, Bitmap pImage, int pSpeed, int pLeftRoadLimit) {
		this.background = pBackground;
		teletImage = pImage;
		width = pImage.getWidth();
		height = pImage.getHeight();
		speed = pSpeed;
		
		if (leftRoadLimit == -1)
			leftRoadLimit = pLeftRoadLimit;
			
		coordX = (int)leftRoadLimit; 
		coordY = -height;
		leftRoadLimit = leftRoadLimit+width;
	}

	protected void drawTeletransporter(Canvas canvas) {
		Rect src = new Rect(0, 0, width, height);
        Rect dst = new Rect(coordX, coordY, coordX+width, coordY+height);
        canvas.drawBitmap(teletImage, src, dst, null);
        coordY += speed;
	}

	//getters and setter
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public static float getLeftRoadLimit() {
		return leftRoadLimit;
	}

	public static void setLeftRoadLimit(int leftRoadLimit) {
		Teletransporter.leftRoadLimit = leftRoadLimit;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	

	
}
