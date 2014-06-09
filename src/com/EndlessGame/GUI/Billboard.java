package com.EndlessGame.GUI;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Billboard extends PathObject{
	
	private Bitmap adImage;
	
	public Billboard(BackGroundMoveable pBackground, Bitmap pImage, int pSpeed, Bitmap adImage)
	{		
		super(pBackground, pImage, pSpeed);
		this.adImage = adImage;
        
		coordX = (int)((750-(750*offset))*density);
		coordY = -height;
	}
	
	public void drawBillboard(Canvas canvas, String intersectionNumb, String intersectionName)
	{
		drawPathObject(canvas);
		drawContent(canvas, intersectionNumb, intersectionName);
        coordY += speed ;
	}
	
	public void drawContent(Canvas canvas, String intersectionNumb, String intersectionName)
	{
		 int adWidth = adImage.getWidth();
		 int adHeight = adImage.getHeight();
		
		// Draw ad image 
		Rect src = new Rect(0, 0, adWidth, adHeight);
        Rect dst = new Rect(coordX, coordY, coordX+adWidth, coordY+adHeight);
        canvas.drawBitmap(adImage, src, dst, null);
		
        // Node info
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
