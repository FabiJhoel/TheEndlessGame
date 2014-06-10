package com.EndlessGame.GUI;

import java.util.ArrayList;

import BusinessLogic.VehicleProperties;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Vehicle extends PathObject{

	private VehicleProperties vehicle;
	private Weapon currentWeapon;
	private ArrayList<Bullet> bullets;
	private float laneWidth;
	
	public Vehicle(BackGroundMoveable pBackground, Bitmap pImage, int pSpeed) {
		super(pBackground, pImage, pSpeed);
		vehicle = new VehicleProperties();
		currentWeapon = new Weapon(background,0,vehicle.getCurrentWeapon(),true);
		coordX = (int)((340-(340*offset))*density); 
		coordY = (int)((420-(420*offset))*density);
		currentWeapon.setCoordX(coordX+width);
		currentWeapon.setCoordY(coordY);
		bullets = new ArrayList<>();
		laneWidth = (float)(115-(115*offset))*density;
	}

	protected void drawVehicle(Canvas canvas) {
		drawPathObject(canvas);
		currentWeapon.drawWeapon(canvas);
		for (Bullet bullet : bullets)
			bullet.drawBullet(canvas);
	}
	
	protected void addBullet()
	{
		int scope = currentWeapon.getWeapon().getLaneAmount();
		Bullet bullet;
		if(bullets.size() < 2*scope)
		{
			for (int cont = -1; cont < scope-1; cont++)
			{
				bullet = new Bullet(background,null,speed,true,scope);
				//AGREGAR X Y Y DE LAS BALAS AQUI
				bullet.setCoordY(coordY+bullet.getHeight()/4);
				if(scope == 1)
					bullet.setCoordX(coordX);
				else
				{
					bullet.setCoordX((int)(coordX+(laneWidth*cont)));
				}
				bullets.add(bullet);
			}
		}
	}

	public void setNewWeapon()
	{
		currentWeapon = new Weapon(background,0,vehicle.getCurrentWeapon(),true);
		currentWeapon.setCoordX(coordX+width);
		currentWeapon.setCoordY(coordY);
	}
	
	//getters and setters
	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}

	public void setCurrentWeapon(Weapon currentWeapon) {
		this.currentWeapon = currentWeapon;
	}

	public VehicleProperties getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleProperties vehicle) {
		this.vehicle = vehicle;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	
	@Override
	public void setSpeed(int pSpeed)
	{
		speed = pSpeed;
		for(Bullet bullet : bullets)
		{
			bullet.setSpeed(pSpeed);
		}
	}

}
