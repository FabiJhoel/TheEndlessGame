package com.EndlessGame.GUI;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import java.util.Random;

public class Enemy {
	
	Bitmap enemyImage;
	BackGroundMoveable background;
	private int width;
	private int height;
	private int speed;
	private int coordX;
	private int coordY;
	private static int leftRoadLimit = -1;
	private final float offset = (float)0.3478260869;
	private float density; 
	private float laneWidth;
	private static int topRoadLimit = -108;
	
	
	public Enemy(BackGroundMoveable pBackground, Bitmap pImage, int pSpeed)
	{
		background = pBackground;
		enemyImage = pImage;
		width = pImage.getWidth();
		height = pImage.getHeight();
		speed = pSpeed;
        
        density = background.getResources().getDisplayMetrics().density;
        //System.out.println("Density: "+density);
        laneWidth = (float)(115-(115*offset))*density;
        leftRoadLimit = (int)((105-(105*offset))*density);
        System.out.println("LRL: "+leftRoadLimit);

        
		coordX = chooseLane();
		coordY = topRoadLimit;
        topRoadLimit = topRoadLimit - (2*height);
        	
	}
	
	public void drawEnemy(Canvas canvas)
	{
		Rect src = new Rect(0, 0, width, height); //left, top, right, bottom
        Rect dst = new Rect(coordX, coordY, coordX+width, coordY+height);
        canvas.drawBitmap(enemyImage, src, dst, null);
        coordY += (speed + speed/4) ;
	}
	
	private int chooseLane()
	{
		Random randomGenerator = new Random();
		int randomLane = randomGenerator.nextInt(5);  
		int laneSelected = (int)(leftRoadLimit+randomLane*laneWidth);
		
		return laneSelected;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public static int getLeftRoadLimit() {
		return leftRoadLimit;
	}

	public static void setLeftRoadLimit(int leftRoadLimit) {
		Enemy.leftRoadLimit = leftRoadLimit;
	}
	
	public static int getTopRoadLimit() {
		return topRoadLimit;
	}

	public static void setTopRoadLimit(int topRoadLimit) {
		Enemy.topRoadLimit = topRoadLimit;
	}
	

}
