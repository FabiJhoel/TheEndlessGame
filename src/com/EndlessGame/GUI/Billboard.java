package com.EndlessGame.GUI;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Billboard extends PathObject{
	
	private Bitmap adImage;
	
	public Billboard(BackGroundMoveable pBackground, Bitmap pImage, int pSpeed, Bitmap adImage)
	{		
		super(pBackground, pImage, pSpeed);
		this.adImage = adImage;
        
		coordX = (int)calculateOffset(720);
		coordY = -height;
	}
	
	public void drawBillboard(Canvas canvas, String intersectionNumb, String intersectionName, boolean isVisited)
	{
		drawPathObject(canvas);
		drawContent(canvas, intersectionNumb, intersectionName, isVisited);
        coordY += speed ;
	}
	
	public void drawContent(Canvas canvas, String intersectionNumb, String intersectionName, boolean isVisited)
	{
		 int adWidth = adImage.getWidth();
		 int adHeight = adImage.getHeight();
		
		// Draw ad image 
		Rect src = new Rect(0, 0, adWidth, adHeight);
        RectF dst = new RectF(coordX + calculateOffset(75), coordY + calculateOffset(150), coordX+adWidth + calculateOffset(75), coordY + adHeight + calculateOffset(150));
        canvas.drawBitmap(adImage, src, dst, null);
		
        // Node info
		Paint titlePaint = new Paint();
		titlePaint.setColor(Color.YELLOW);
		titlePaint.setTextSize(calculateOffset(20));
		titlePaint.setFakeBoldText(true);
		
		Paint valuesPaint = new Paint();
		valuesPaint.setColor(Color.YELLOW);
		valuesPaint.setTextSize(calculateOffset(15));
		
		if (isVisited)
		{
			titlePaint.setColor(Color.RED);
			valuesPaint.setColor(Color.RED);
		}
		
		canvas.drawText("Intersection Number:", coordX + calculateOffset(20), coordY + calculateOffset(60), titlePaint);
		canvas.drawText(intersectionNumb, coordX + calculateOffset(20), coordY + calculateOffset(80), valuesPaint);
		
		canvas.drawText("Intersection Name:", coordX + calculateOffset(20), coordY + calculateOffset(110), titlePaint);
		canvas.drawText(intersectionName, coordX + calculateOffset(20), coordY + calculateOffset(130), valuesPaint);
	}
	
}
