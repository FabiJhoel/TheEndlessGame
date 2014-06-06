package com.EndlessGame.GUI;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Vehicle extends PathObject{

	public Vehicle(BackGroundMoveable pBackground, Bitmap pImage) {
		super(pBackground, pImage, 0);
		
		coordX = (int)((340-(340*offset))*density); 
		coordY = (int)((380-(380*offset))*density);
	}

	protected void drawVehicle(Canvas canvas) {
		drawPathObject(canvas);
	}
}
