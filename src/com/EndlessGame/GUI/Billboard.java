package com.EndlessGame.GUI;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Billboard {

	Bitmap billboardImage;
	BackGroundMoveable background;
	private int width;
	private int height;
	private int speed;
	private int coordX;
	private int coordY;
	private final float offset = (float)0.3478260869;
	private float density; 
	private float laneWidth;
	private static int topRoadLimit = -108;
	
	
	public Billboard(BackGroundMoveable pBackground, Bitmap pImage, int pSpeed)
	{
		background = pBackground;
		billboardImage = pImage;
		width = pImage.getWidth();
		height = pImage.getHeight();
		speed = pSpeed;
        
        density = background.getResources().getDisplayMetrics().density;
        laneWidth = (float)(115-(115*offset))*density;
        
		coordX = chooseLane();
		coordY = topRoadLimit;
        topRoadLimit = topRoadLimit - (2*height);
        	
	}
	
	public void drawEnemy(Canvas canvas)
	{
		Rect src = new Rect(0, 0, width, height); //left, top, right, bottom
        Rect dst = new Rect(coordX, coordY, coordX+width, coordY+height);
        canvas.drawBitmap(billboardImage, src, dst, null);
        coordY += (speed + speed/4) ;
	}
	
	private int chooseLane()
	{
		Random randomGenerator = new Random();
		int randomLane = randomGenerator.nextInt(5);  
		int laneSelected = (int)(randomLane*laneWidth);
		
		return laneSelected;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public static int getTopRoadLimit() {
		return topRoadLimit;
	}

	public static void setTopRoadLimit(int topRoadLimit) {
		Billboard.topRoadLimit = topRoadLimit;
	}
	
}
