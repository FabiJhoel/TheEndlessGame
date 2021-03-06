package com.EndlessGame.GUI;

import java.util.ArrayList;
import java.util.Random;

import com.EndlessGame.GUI.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class BackGroundMoveable extends View{

	private Bitmap mainPath, warpPath, roadPath;
	private ArrayList<Teletransporter> teletransporters;
	private ArrayList<Enemy> enemies;
	private Vehicle vehicle;
	private Billboard billboard;
	private Weapon roadWeapon;
	private String intersectionNumb, intersectionName;
	private Random rand = new Random();
	private int farY, nearY, newFarY, speed; 
	private int enemiesAmount, billboardAmount, teletransportersAmount, roadWeaponAmount;
	private boolean warp, stop, isVisited;
	private final float offset = (float)0.3478260869;
	private final float density = getResources().getDisplayMetrics().density;
	private final float leftRoadLimit = (float)(80-(80*offset))*density;
		
	public BackGroundMoveable(Context context)
	{	
		super(context);
		farY = nearY = newFarY = speed = 0;
		warp = stop = isVisited = false;
		intersectionNumb = "";
		intersectionName = "";
		billboard = null;
		roadWeapon = null;		
		teletransporters = new ArrayList<>();
		enemies = new ArrayList<>();
		teletransportersAmount = enemiesAmount = billboardAmount = roadWeaponAmount = -1;
		vehicle = new Vehicle(this, BitmapFactory.decodeResource(getResources(), R.drawable.car3),speed);
		mainPath = roadPath = BitmapFactory.decodeResource(getResources(), R.drawable.main_path);
		warpPath = BitmapFactory.decodeResource(getResources(), R.drawable.warp2);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		update(canvas);
		
		if (warp == false)
		{
			drawBillboard(canvas);
			drawTeletransporters(canvas);
			drawVehicle(canvas);
			drawEnemy(canvas);
			drawRoadWeapon(canvas);
		}
		
		if(!stop)
			invalidate();
	}
	
	private void update(Canvas canvas)
	{
		//http://androidtrainningcenter.blogspot.com/2012/12/android-game-development-moving-or.html
		int offset1 = 1;
		int offset2 = 2;
		
		canvas.drawBitmap(mainPath, 0, 0, null);
		
		farY = farY + offset1 + speed;
		// decrement the near background
		nearY = nearY + offset2 + speed;
		// calculate the wrap factor for matching image draw
		newFarY = (farY)-mainPath.getHeight();
		// if we have scrolled all the way, reset to start
		if (newFarY > 0) 
		{
			farY = 0;
			// only need one draw
			canvas.drawBitmap(mainPath, 0, farY, null);
		} 
		else 
		{
			// need to draw original and wrap
			canvas.drawBitmap(mainPath, 0, farY, null);
			canvas.drawBitmap(mainPath, 0, newFarY, null);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		int eventId = event.getAction();
		float coordX = event.getX();
		float laneWidth = (float)(115-(115*offset))*density;  //(dpi-(dpi*offset))*density
		float rigthPathLimit = (float)(650-(650*offset))*density;
		float leftPathLimit = (float)(80-(80*offset))*density;
		
		switch(eventId)
		{
			case MotionEvent.ACTION_DOWN: //this motion event prevents undesirable touches
				if(coordX > vehicle.getCoordX())
				{
					if((vehicle.getCoordX()+laneWidth)<=rigthPathLimit)
					{
						vehicle.setCoordX((int)(vehicle.getCoordX()+laneWidth));
						vehicle.getCurrentWeapon().setCoordX(vehicle.getCoordX()+vehicle.getWidth());
					}
				}
				
				else if(coordX < vehicle.getCoordX())
				{
					if ((vehicle.getCoordX()-laneWidth) >= leftPathLimit)
					{
						vehicle.setCoordX((int)(vehicle.getCoordX()-laneWidth));
						vehicle.getCurrentWeapon().setCoordX(vehicle.getCoordX()+vehicle.getWidth());
					}
				}
				
				break;
		}
		
		return true;
	}

	private void drawTeletransporters(Canvas canvas){
		
		if (teletransportersAmount == -1)
		{	
			for (Teletransporter teletranspoter : teletransporters)
			{
				teletranspoter.drawTeletransporter(canvas);
				teletranspoter.setSpeed(speed);
			}
		}
		else
		{
			teletransporters.clear();
			addTeletransporters();
			teletransportersAmount = -1;	
		}
	}
	
	private void drawRoadWeapon(Canvas canvas){
		
		if (roadWeaponAmount == -1)
		{	
			if(roadWeapon != null)
				roadWeapon.drawWeapon(canvas);
		}
		
		else
		{
			roadWeapon = new Weapon(this,speed,null,false);
			roadWeaponAmount = -1;	
		}
	}
	
	private void drawBillboard(Canvas canvas)
	{			
		if (billboardAmount == -1)
		{	
			if (billboard != null)
				billboard.drawBillboard(canvas, intersectionNumb, intersectionName, isVisited);
		}
		
		else
		{
			billboard = null;
			addNewBillboard();
			billboardAmount = -1;	
		}
	}
	
	private void drawVehicle(Canvas canvas)
	{
		vehicle.drawVehicle(canvas);
		vehicle.setSpeed(speed);
	}
	
	private void drawEnemy(Canvas canvas)
	{
		if (enemiesAmount == -1)
		{	
			for (Enemy enemy : enemies)
			{
				enemy.drawEnemy(canvas);				
				enemy.setSpeed(speed);
			}
		}
		
		else
		{
			enemies.clear();
			addEnemies();
			enemiesAmount = -1;		
		}
	}

	private void addTeletransporters()
	{
		switch(teletransportersAmount){
		case 1:
			teletransporters.add(new Teletransporter(this,BitmapFactory.decodeResource(getResources(), R.drawable.tunel5),speed,(int)leftRoadLimit));
			Teletransporter.setLeftRoadLimit(-1);
			break;
		case 2:
			teletransporters.add(new Teletransporter(this,BitmapFactory.decodeResource(getResources(), R.drawable.tunel2),speed,(int)leftRoadLimit));
			teletransporters.add(new Teletransporter(this,BitmapFactory.decodeResource(getResources(), R.drawable.tunel3),speed,(int)leftRoadLimit));
			Teletransporter.setLeftRoadLimit(-1);
			break;
		case 3:
			teletransporters.add(new Teletransporter(this,BitmapFactory.decodeResource(getResources(), R.drawable.tunel2),speed,(int)leftRoadLimit));
			teletransporters.add(new Teletransporter(this,BitmapFactory.decodeResource(getResources(), R.drawable.tunel1),speed,(int)leftRoadLimit));
			teletransporters.add(new Teletransporter(this,BitmapFactory.decodeResource(getResources(), R.drawable.tunel2),speed,(int)leftRoadLimit));
			Teletransporter.setLeftRoadLimit(-1);
			break;
		}
	}
	
	private void addEnemies()
	{
		switch(enemiesAmount)
		{		
		case 1:
			enemies.add(new Enemy(this,BitmapFactory.decodeResource(getResources(), R.drawable.spaceship),speed));
			Enemy.setTopRoadLimit(-108);
			break;
		case 2:
			enemies.add(new Enemy(this,BitmapFactory.decodeResource(getResources(), R.drawable.spaceship),speed));
			enemies.add(new Enemy(this,BitmapFactory.decodeResource(getResources(), R.drawable.spaceship),speed));
			Enemy.setTopRoadLimit(-108);
			break;
		case 3:
			enemies.add(new Enemy(this,BitmapFactory.decodeResource(getResources(), R.drawable.spaceship),speed));
			enemies.add(new Enemy(this,BitmapFactory.decodeResource(getResources(), R.drawable.spaceship),speed));
			enemies.add(new Enemy(this,BitmapFactory.decodeResource(getResources(), R.drawable.spaceship),speed));
			Enemy.setTopRoadLimit(-108);
			break;
		}
	}
	
	private void addNewBillboard()
	{
		if (billboardAmount == 1)
		{
			TypedArray adImages_array = getResources().obtainTypedArray(R.array.ad_images);
			int rndInt = rand.nextInt(adImages_array.length());
			Bitmap adImage = BitmapFactory.decodeResource(getResources(), adImages_array.getResourceId(rndInt, 0));
			adImages_array.recycle();
			
			billboard = new Billboard(this, BitmapFactory.decodeResource(getResources(), R.drawable.billboard), speed, adImage);
		}
	}
	
	//Getter and Setter
	public Bitmap getMainPath() {
		return mainPath;
	}

	public void setMainPath(Bitmap mainPath) {
		this.mainPath = mainPath;
	}

	public boolean getWarp() {
		return warp;
	}

	public void setWarp(boolean warp) {
		this.warp = warp;
	}

	public Bitmap getRoadPath() {
		return roadPath;
	}

	public void setRoadPath(Bitmap roadPath) {
		this.roadPath = roadPath;
	}

	public Bitmap getWarpPath() {
		return warpPath;
	}

	public void setWarpPath(Bitmap warpPath) {
		this.warpPath = warpPath;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getTeletransportersAmount() {
		return teletransportersAmount;
	}

	public void setTeletransportersAmount(int teletransportersAmount) {
		this.teletransportersAmount = teletransportersAmount;
	}
	
	public ArrayList<Teletransporter> getTeletransporters() {
		return teletransporters;
	}

	public void setTeletransporters(ArrayList<Teletransporter> teletransporters) {
		this.teletransporters = teletransporters;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public int getEnemiesAmount() {
		return enemiesAmount;
	}

	public void setEnemiesAmount(int enemiesAmount) {
		this.enemiesAmount = enemiesAmount;
	}

	public float getDensity() {
		return density;
	}

	public float getLeftRoadLimit() {
		return leftRoadLimit;
	}

	public void setBillboard(Billboard pBillboard)
	{
		billboard = pBillboard;
	}

	public int getBillboardAmount() {
		return billboardAmount;
	}

	public void setBillboardAmount(int billboardAmount) {
		this.billboardAmount = billboardAmount;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public int getRoadWeaponAmount() {
		return roadWeaponAmount;
	}

	public void setRoadWeaponAmount(int roadWeaponAmount) {
		this.roadWeaponAmount = roadWeaponAmount;
	}

	public Weapon getRoadWeapon() {
		return roadWeapon;
	}

	public void setRoadWeapon(Weapon roadWeapon) {
		this.roadWeapon = roadWeapon;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}	
	
	public String getIntersectionNumb() 
	{
		return intersectionNumb;
	}

	public void setIntersectionNumb(String intersectionNumb)
	{
		this.intersectionNumb = intersectionNumb;
	}

	public String getIntersectionName()
	{
		return intersectionName;
	}

	public void setIntersectionName(String intersectionName)
	{
		this.intersectionName = intersectionName;
	}	
}
