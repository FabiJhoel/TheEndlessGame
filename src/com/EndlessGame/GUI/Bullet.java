package com.EndlessGame.GUI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Bullet extends PathObject{
	
	boolean type; //false = enemy true = vehicle
	int scope;
	
	public Bullet (BackGroundMoveable pBackground, Bitmap pImage, int pSpeed, boolean pType, int pScope)
	{
		super(pBackground, pImage, pSpeed);
		type =  pType;
		scope = pScope;
		setImage(image);
	}
	
	public void drawBullet (Canvas canvas)
	{
		try
		{
			drawPathObject(canvas);
			if (type)
				coordY -= (speed + speed/4);
			else
				coordY += (speed + speed/2);
		}
		catch(Exception e)
    	{
    		System.out.println("ERROR: Bullet.drawBullet() failure");
    	}
	}
	
	@Override
	public void setImage(Bitmap pImage)
	{
		if (!type)
			image = BitmapFactory.decodeResource(background.getResources(), R.drawable.bullet_e);
		else
		{
			if (scope == 1)
				image = BitmapFactory.decodeResource(background.getResources(), R.drawable.bullet1);
			else if (scope == 2)
				image = BitmapFactory.decodeResource(background.getResources(), R.drawable.bullet2);
			else
				image = BitmapFactory.decodeResource(background.getResources(), R.drawable.bullet3);
		}
		
		width = image.getWidth();
		height = image.getHeight();
	}
}
