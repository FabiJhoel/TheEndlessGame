package com.EndlessGame.GUI;

import BusinessLogic.VehicleProperties;
import BusinessLogic.WeaponProperties;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Vehicle extends PathObject{

	VehicleProperties vehicle;
	Weapon currentWeapon;
	
	public Vehicle(BackGroundMoveable pBackground, Bitmap pImage) {
		super(pBackground, pImage, 0);
		vehicle = new VehicleProperties();
		currentWeapon = new Weapon(background,0,vehicle.getCurrentWeapon(),true);
		coordX = (int)((340-(340*offset))*density); 
		coordY = (int)((380-(380*offset))*density);
		currentWeapon.setCoordX(coordX+width);
		currentWeapon.setCoordY(coordY);
	}

	protected void drawVehicle(Canvas canvas) {
		drawPathObject(canvas);
		currentWeapon.drawWeapon(canvas);
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

}
