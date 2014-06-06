package com.EndlessGame.GUI;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Billboard extends PathObject{

	public Billboard(BackGroundMoveable pBackground, Bitmap pImage, int pSpeed)
	{
		super(pBackground, pImage, pSpeed);
        
		coordX = (int)((750-(750*offset))*density);
		coordY = -height;  	
	}
	
	public void drawBillboard(Canvas canvas)
	{
		drawPathObject(canvas);
        coordY += speed ;
	}
	
}
