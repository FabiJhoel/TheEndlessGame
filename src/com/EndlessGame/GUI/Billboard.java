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
        
		coordX = (int)calculateOffset(720);
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
        //canvas.drawBitmap(adImage, src, dst, null);
		
        // Node info
		Paint titlePaint = new Paint();
		titlePaint.setColor(Color.YELLOW);
		titlePaint.setTextSize(calculateOffset(20));
		titlePaint.setFakeBoldText(true);
		
		Paint valuesPaint = new Paint();
		valuesPaint.setColor(Color.GREEN);
		valuesPaint.setTextSize(calculateOffset(15));
		
		canvas.drawText("Intersection Number:", coordX + calculateOffset(20), coordY + calculateOffset(50), titlePaint);
		canvas.drawText(intersectionNumb, coordX + calculateOffset(20), coordY + calculateOffset(70), valuesPaint);
		
		canvas.drawText("Intersection Name:", coordX + calculateOffset(20), coordY + calculateOffset(100), titlePaint);
		canvas.drawText(intersectionName, coordX + calculateOffset(20), coordY + calculateOffset(140), valuesPaint);
	}
	
}
