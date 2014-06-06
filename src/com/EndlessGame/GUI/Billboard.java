package com.EndlessGame.GUI;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
		writeText(canvas);
        coordY += speed ;
	}
	
	public void writeText(Canvas canvas)
	{
		Paint p = new Paint();
		p.setStyle(Paint.Style.FILL);
		p.setColor(Color.YELLOW);
		
		canvas.drawText("TEXTOOOOO", coordX, coordY, p);
	}
	
}
