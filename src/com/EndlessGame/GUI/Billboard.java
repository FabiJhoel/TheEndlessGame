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
	
	public void drawBillboard(Canvas canvas, String intersectionNumb, String intersectionName)
	{
		drawPathObject(canvas);
		writeText(canvas, intersectionNumb, intersectionName);
        coordY += speed ;
	}
	
	public void writeText(Canvas canvas, String intersectionNumb, String intersectionName)
	{
		Paint titlePaint = new Paint();
		titlePaint.setColor(Color.YELLOW);
		titlePaint.setTextSize(30);
		titlePaint.setFakeBoldText(true);
		
		Paint valuesPaint = new Paint();
		valuesPaint.setColor(Color.YELLOW);
		valuesPaint.setTextSize(25);
		
		canvas.drawText("Intersection Number:", coordX + 70, coordY + 80, titlePaint);
		canvas.drawText(intersectionNumb, coordX + 70, coordY + 110, valuesPaint);
		
		canvas.drawText("Intersection Name:", coordX + 70, coordY + 150, titlePaint);
		canvas.drawText(intersectionName, coordX + 70, coordY + 180, valuesPaint);
	}
	
}
