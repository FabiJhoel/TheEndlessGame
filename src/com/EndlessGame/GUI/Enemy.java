package com.EndlessGame.GUI;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Enemy extends PathObject{
	
	private static int leftRoadLimit = -1;
	private float laneWidth;
	private static int topRoadLimit = -108;
	private Bullet bullet;
	
	
	public Enemy(BackGroundMoveable pBackground, Bitmap pImage, int pSpeed)
	{
		super(pBackground, pImage, pSpeed);
        
        //System.out.println("Density: "+density);
        laneWidth = (float)(115-(115*offset))*density;
        leftRoadLimit = (int)((105-(105*offset))*density);

        bullet = null;
        
		coordX = chooseLane();
		coordY = topRoadLimit;
        topRoadLimit = topRoadLimit - (2*height);
        	
	}
	
	public void drawEnemy(Canvas canvas)
	{
		try
		{
			drawPathObject(canvas);
	        coordY += (speed + speed/8) ;
	        if(bullet != null)
	        	bullet.drawBullet(canvas);
		}
		catch(Exception e)
    	{
    		System.out.println("ERROR: Enemy.drawEnemy() failure");
    	}
	}
	
	public void addBullet()
	{
		try
		{
			bullet = new Bullet(background,null,speed,false,1);
			//AGREGAR X Y Y DE LAS BALAS AQUI
			bullet.setCoordY(coordY+height);
			bullet.setCoordX(coordX);
		}
		catch(Exception e)
    	{
    		System.out.println("ERROR: Enemy.addBullet() failure");
    	}
	}
	
	private int chooseLane()
	{
		Random randomGenerator = new Random();
		int randomLane = randomGenerator.nextInt(5);  
		int laneSelected = (int)(leftRoadLimit+randomLane*laneWidth);
		
		return laneSelected;
	}
	
	//getters and setters
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
		if(bullet != null)
			bullet.setSpeed(speed);
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

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	
	
}
