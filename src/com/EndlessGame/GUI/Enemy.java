package com.EndlessGame.GUI;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Enemy extends PathObject{
	
	private static int leftRoadLimit = -1;
	private float laneWidth;
	private static int topRoadLimit = -108;
	
	
	public Enemy(BackGroundMoveable pBackground, Bitmap pImage, int pSpeed)
	{
		super(pBackground, pImage, pSpeed);
        
        //System.out.println("Density: "+density);
        laneWidth = (float)(115-(115*offset))*density;
        leftRoadLimit = (int)((105-(105*offset))*density);
        //System.out.println("LRL: "+leftRoadLimit);
        
		coordX = chooseLane();
		coordY = topRoadLimit;
        topRoadLimit = topRoadLimit - (2*height);
        	
	}
	
	public void drawEnemy(Canvas canvas)
	{
		drawPathObject(canvas);
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
