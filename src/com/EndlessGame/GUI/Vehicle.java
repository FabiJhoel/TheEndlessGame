package com.EndlessGame.GUI;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Vehicle{
	
	Bitmap teletImage;
	private int width;
	private int height;
	private int coordX;
	private int coordY;
	BackGroundMoveable background;

	public Vehicle(BackGroundMoveable pBackground, Bitmap pImage) {
		this.background = pBackground;
		teletImage = pImage;
		width = pImage.getWidth();
		height = pImage.getHeight();
		coordX = 440; 
		coordY = 500;
	}

	protected void drawVehicle(Canvas canvas) {
		Rect src = new Rect(0, 0, width, height); 
        Rect dst = new Rect(coordX, coordY, coordX+width, coordY+height);
        canvas.drawBitmap(teletImage, src, dst, null);
	}


	//getters and setters
	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
}
