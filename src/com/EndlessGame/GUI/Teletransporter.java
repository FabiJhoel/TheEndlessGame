package com.EndlessGame.GUI;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Teletransporter extends PathObject{
	
	private static int leftRoadLimit = -1;

	public Teletransporter(BackGroundMoveable pBackground, Bitmap pImage, int pSpeed, int pLeftRoadLimit) {
		super(pBackground, pImage, pSpeed);
		
		if (leftRoadLimit == -1)
			leftRoadLimit = pLeftRoadLimit;
			
		coordX = (int)leftRoadLimit; 
		coordY = -height;
		leftRoadLimit = leftRoadLimit+width;
	}

	protected void drawTeletransporter(Canvas canvas) {
		drawPathObject(canvas);
        coordY += speed;
	}

	public static float getLeftRoadLimit() {
		return leftRoadLimit;
	}

	public static void setLeftRoadLimit(int leftRoadLimit) {
		Teletransporter.leftRoadLimit = leftRoadLimit;
	}
	
}
