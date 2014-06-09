package com.EndlessGame.GUI;

import java.util.Random;

import BusinessLogic.WeaponPopulationAdministrator;
import BusinessLogic.WeaponProperties;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Paint.Style;

public class Weapon extends PathObject{
	
	private static int leftRoadLimit = -1;
	private float laneWidth;
	private int radious;
	private WeaponProperties weapon;
	private static WeaponPopulationAdministrator wPAdmin = new WeaponPopulationAdministrator();
	
	public Weapon(BackGroundMoveable pBackground, int pSpeed, WeaponProperties pWeapon)
	{
		super(pBackground, null, pSpeed);
        
        laneWidth = (float)(115-(115*offset))*density;
        leftRoadLimit = (int)((105-(105*offset))*density);
        radious = 50;
        
		coordX = radious + chooseLane();
		coordY = radious + (-3*radious); 
		
		if (pWeapon == null)
			weapon = wPAdmin.generateDefaultWeapon();
	}

	private int chooseLane()
	{
		Random randomGenerator = new Random();
		int randomLane = randomGenerator.nextInt(5);  
		int laneSelected = (int)(leftRoadLimit+randomLane*laneWidth);
		
		return laneSelected;
	}
	
	protected void drawWeapon(Canvas canvas)
	{
		canvas.drawPath(createPath(), setPathCharacteristics());
		coordY += (speed + speed/4);
	}
	
	protected Paint setPathCharacteristics()
	{
		Paint paint = new Paint();
		paint.setStrokeWidth(weapon.getThickness());	
		int color = Color.argb(255, weapon.getColor()[0],  weapon.getColor()[1],  weapon.getColor()[2]);
		paint.setColor(color);
		paint.setStyle(Style.FILL_AND_STROKE);
		
		return paint;
	}
	
	protected Path createPath()
	{
		Path path = new Path();
		int sides = weapon.getPolygonPoints();
		Point[] points = new Point[sides];
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < sides; ++i) {
			float theta = (float)(i* 2 * Math.PI / sides);
		    x = (int)(Math.cos(theta)*radious+coordX);
		    y = (int)(Math.sin(theta)*radious+coordY);
		    points[i] = new Point(x, y);
		}
		
		path.moveTo(points[sides-1].x, points[sides-1].y);
		for (int j = 0; j < sides; j++)
		{
			path.lineTo(points[j].x, points[j].y);
			path.moveTo(points[j].x, points[j].y);
		}
		path.lineTo(points[0].x, points[0].y);

		path.close();
		
		return path;
	}
	
	//getters and setters
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public static int getLeftRoadLimit() {
		return leftRoadLimit;
	}

	public static void setLeftRoadLimit(int leftRoadLimit) {
		Weapon.leftRoadLimit = leftRoadLimit;
	}
	
}
