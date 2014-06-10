package com.EndlessGame.GUI;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class PathObject {
	
	Bitmap image;
	BackGroundMoveable background;
	protected int width;
	protected int height;
	protected int speed;
	protected int coordX;
	protected int coordY;
	protected final float offset = (float)0.3478260869;
	protected float density; 
	
	public PathObject (BackGroundMoveable pBackground, Bitmap pImage, int pSpeed)
	{
		background = pBackground;
		speed = pSpeed;
		image = pImage;
		if (image != null)
		{
			width = pImage.getWidth();
			height = pImage.getHeight();
		}
		density = background.getResources().getDisplayMetrics().density;
	}

	protected void drawPathObject(Canvas canvas)
	{
		Rect src = new Rect(0, 0, width, height);
        Rect dst = new Rect(coordX, coordY, coordX+width, coordY+height);
        canvas.drawBitmap(image, src, dst, null);
	}
	
	protected float calculateOffset(int pDpi)
	{
		return (float)((pDpi-(pDpi*offset))*density);
	}
	
	//getters and setters
	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public BackGroundMoveable getBackground() {
		return background;
	}

	public void setBackground(BackGroundMoveable background) {
		this.background = background;
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

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

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public float getOffset() {
		return offset;
	}
	
}
