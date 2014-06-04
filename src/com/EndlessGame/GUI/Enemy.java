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
	private static int topRoadLimit = -108;
	private final int lane1 = 220;
	private final int lane2 = 430;
	private final int lane3 = 640;
	private final int lane4 = 880;
	private final int lane5 = 1090;
	
	
	public Enemy(BackGroundMoveable pBackground, Bitmap pImage, int pSpeed)
	{
		background = pBackground;
		enemyImage = pImage;
		width = pImage.getWidth();
		height = pImage.getHeight();
		speed = pSpeed;
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
		int randomLane = 0;  
		
		for (int i = 0; i<10; i++)
        {   
			randomLane = 0;                      
            Random randomGenerator = new Random();
            
            while (randomLane == 0)
            {
                randomLane = randomGenerator.nextInt(6);
            }
        }
		
		switch (randomLane)
		{
			case 1:
				return lane1;
				
			case 2:
				return lane2;
				
			case 3: 
				return lane3;
				
			case 4:
				return lane4;
				
			case 5:
				return lane5;
				
			default:
				return lane1;
				
		}
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
		Enemy.topRoadLimit = topRoadLimit;
	}
	

}
