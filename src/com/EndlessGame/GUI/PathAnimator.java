package com.EndlessGame.GUI;

import java.util.Random;

import com.EndlessGame.GUI.R;

import BusinessLogic.*;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PathAnimator extends Thread{
	private int speed;
	private int seconds;
	private MainScreen mainScreen;
	private BackGroundMoveable background;
	private PathGraph pathGraph;
	private boolean teletransCollision, enemiesCollision, roadWeaponCollision, bulletVehicleCollision;
	private Node currentNode;
	private PlayerInfo player;
	private boolean stop;
	private MediaPlayer mainSound;
	
	
	public PathAnimator(MainScreen pMainScreen)
	{				
		player = new PlayerInfo("");
		pathGraph = new PathGraph();
		currentNode = pathGraph.setInitialIntersection();
		pathGraph.generateLevel(currentNode);
		this.mainScreen = pMainScreen;
		this.speed = seconds = 0;
		mainSound = MediaPlayer.create(mainScreen, R.raw.main_screen_theme);
		mainSound.setLooping(true);
		mainSound.setVolume((float)0.3, (float)0.3);	
		background = new BackGroundMoveable(mainScreen);
		showBackground();
		teletransCollision = enemiesCollision = roadWeaponCollision = bulletVehicleCollision = false;
		stop = false;		
	}
	
	public PlayerInfo getPlayer()
	{
		return player;
	}

	public void run()
	{
		while(!stop)
		{
			if (seconds < 50)
			{
				increaseSpeed();				
				
				if (seconds == 25)
					activateEnemies();
				else if (seconds == 26)
					activateEnemiesShoots();
				else if (seconds == 40)
					activateWeapon();
			}
			
			else	
				activateTeletransporters();
					
			// Verify collision
			if (teletransCollision)
				checkTeletransporterCollision();
			if (enemiesCollision)
				checkEnemiesCollision();
			if (roadWeaponCollision)
				checkRoadWeaponCollision();
			if(bulletVehicleCollision)
				checkbulletVehicleCollision();
	
			try 
			{
				Thread.sleep(200);
			}
			
			catch (InterruptedException e1) 
			{			
				e1.printStackTrace();
			}
			
			seconds++;
		}
	}
	
	private void increaseSpeed(){
		if(speed < 7)
		{
			speed++;
			background.setSpeed(speed);
			
			try 
			{
				Thread.sleep(500);
			} 
			
			catch (InterruptedException e1) 
			{			
				e1.printStackTrace();
			}
		}	
	}
	
	private void activateEnemies()
	{              
        Random randomGenerator = new Random();
        int enemiesNumb = randomGenerator.nextInt(3) +1;
		background.setEnemiesAmount(enemiesNumb);
		
		//activate enemies collision check
		enemiesCollision = true;
	}
	
	private void activateWeapon()
	{
		background.setRoadWeaponAmount(1);
		
		//activate enemies collision check
		roadWeaponCollision = true;
	}
	
	private void activateTeletransporters()
	{
		activateBillboard();
		background.setTeletransportersAmount(currentNode.getRealArcs().size()); 
		seconds = 0;	
		teletransCollision = true;
	}
	
	private void activateBillboard()
	{
		background.setIntersectionNumb(currentNode.getSeed().toString());
		background.setIntersectionName(currentNode.getName());
		background.setVisited(pathGraph.findVisitedNode(currentNode));
		background.setBillboardAmount(1);
		
		try 
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e1) 
		{			
			e1.printStackTrace();
		}
	}
	
	private void activateEnemiesShoots()
	{
		for (Enemy enemy : background.getEnemies())
		{
			enemy.addBullet();
			MediaPlayer sound = MediaPlayer.create(mainScreen, R.raw.disparo_malo);
			sound.setVolume((float)0.3, (float)0.3);
			sound.start();
			
			try 
			{
				Thread.sleep(500);
			}
			
			catch (InterruptedException e1) 
			{			
				e1.printStackTrace();
			}
		}
	}
	
	private void checkbulletVehicleCollision()
	{
		boolean collition = false;
		Vehicle vehicle = background.getVehicle();
		Bullet bullet;
		Enemy enemy;
		int index = 0;
		int index2 = 0;
		
		while(index < background.getEnemies().size())
		{
			enemy = background.getEnemies().get(index);
			while(index2 < vehicle.getBullets().size())
			{
				bullet = vehicle.getBullets().get(index2);
				collition = bullet.getCoordX() <= enemy.getCoordX() + enemy.getWidth()&&
						bullet.getCoordX()+bullet.getWidth() >= enemy.getCoordX() && 
								bullet.getCoordY()+bullet.getHeight() >= enemy.getCoordY() && 
										bullet.getCoordY() <= enemy.getCoordY() + enemy.getHeight();
				if(collition)
				{
					System.out.println("MATE A UNO");
					vehicle.getBullets().remove(index2);
					background.getEnemies().remove(index);
					collition = false;
					break;
				}
				
				else
					index2++;
			}
			
			if(!collition)
				index++;
		}	
	}
	
	private void checkTeletransporterCollision(){
		
		boolean collition = false;
		Vehicle vehicle = background.getVehicle();
		Teletransporter teletransporter;
		
		for (int index = 0; index < background.getTeletransporters().size(); index++){
			teletransporter = background.getTeletransporters().get(index);
			collition = vehicle.getCoordX() <= teletransporter.getCoordX() + teletransporter.getWidth()&&
						vehicle.getCoordX()+vehicle.getWidth() >= teletransporter.getCoordX() && 
					    vehicle.getCoordY()+vehicle.getHeight() >= teletransporter.getCoordY() && 
						vehicle.getCoordY() <= teletransporter.getCoordY() + teletransporter.getHeight();
			if(collition)
			{
				MediaPlayer sound = MediaPlayer.create(mainScreen, R.raw.teletransport);
				sound.start();
				teletransport();
				
				currentNode = currentNode.getRealArcs().get(index);				
				
				// Verify if is a return node
				if (currentNode.getIsReturn())
				{
					pathGraph.loadHashVisitedNodes(currentNode);
					currentNode = pathGraph.selectVisitedNode(currentNode);
					System.out.println("SI SE DEVOLVIO: "+ currentNode.getId() +" SEMILLA: "+ currentNode.getSeed() + " HIJOS: " + currentNode.getRealArcs().size());
					currentNode.getSeedOp().setInitialSeed(currentNode.getSeed());
				}
				
				// Accumulate points
				if (pathGraph.findVisitedNode(currentNode))
					player.accumulateScore(1);
				else
					player.accumulateScore(3);	
				
				//generate new level
				pathGraph.generateLevel(currentNode);				
				
				// Activate collision
				teletransCollision = false;
				
				vehicle.getBullets().clear();
				bulletVehicleCollision = false;
				
				break;
			}
		}		
	}

	private void checkEnemiesCollision()
	{
		boolean collition = false;
		Vehicle vehicle = background.getVehicle();
		Enemy enemy;
		
		for (int index = 0; index < background.getEnemies().size(); index++){
			enemy = background.getEnemies().get(index);
			collition = vehicle.getCoordX() <= enemy.getCoordX() + enemy.getWidth()&&
						vehicle.getCoordX()+vehicle.getWidth() >= enemy.getCoordX() && 
					    vehicle.getCoordY()+vehicle.getHeight() >= enemy.getCoordY() && 
						vehicle.getCoordY() <= enemy.getCoordY() + enemy.getHeight();
			if(collition)
			{				
				MediaPlayer sound = MediaPlayer.create(mainScreen, R.raw.choque);
				sound.start();
				player.setLives(player.getLives()-1);
				// Deactivate collision
				enemiesCollision = false;
				checkLives();
				break;
			}
			
			else if(checkbulletEnemieCollision(enemy))
				break;
		}	
	}
	
	private boolean checkbulletEnemieCollision(Enemy pEnemy)
	{
		boolean collition = false;
		Vehicle vehicle = background.getVehicle();
		Bullet bullet = pEnemy.getBullet();

		if (bullet != null)
		{
			collition = bullet.getCoordX() <= vehicle.getCoordX() + vehicle.getWidth()&&
					bullet.getCoordX()+bullet.getWidth() >= vehicle.getCoordX() && 
							bullet.getCoordY()+bullet.getHeight() >= vehicle.getCoordY() && 
									bullet.getCoordY() <= vehicle.getCoordY() + vehicle.getHeight();
			if(collition)
			{
				MediaPlayer sound = MediaPlayer.create(mainScreen, R.raw.choque);
				sound.start();
				pEnemy.setBullet(null);
				player.setLives(player.getLives()-1);
				// Deactivate collision
				enemiesCollision = false;
				checkLives();
				return true;
			}
		}
		
		return false;
	}
	
	private void checkRoadWeaponCollision()
	{
		boolean collition = false;
		Vehicle vehicle = background.getVehicle();
		Weapon weapon = background.getRoadWeapon();
		
		if (weapon != null)
		{
			collition = vehicle.getCoordX() <= weapon.getCoordX() + weapon.getWidth()&&
						vehicle.getCoordX()+vehicle.getWidth() >= weapon.getCoordX() && 
					    vehicle.getCoordY()+vehicle.getHeight() >= weapon.getCoordY() && 
						vehicle.getCoordY() <= weapon.getCoordY() + weapon.getHeight();
			if(collition)
			{			
				MediaPlayer sound = MediaPlayer.create(mainScreen, R.raw.arma_nueva);
				sound.start();
				vehicle.getVehicle().generateWeapon(weapon.getWeapon());
				vehicle.setNewWeapon();
				// Deactivate collision
				roadWeaponCollision = false;
			}
		}
	}
	
	private void teletransport()
	{
		int warpSpeed = 40;
		background.setWarp(true);
		background.setMainPath(background.getWarpPath());
		background.setSpeed(warpSpeed);
		background.setTeletransportersAmount(0);
		background.setBillboardAmount(0);
	
		try 
		{
			Thread.sleep(800);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		background.setWarp(false);
		background.setSpeed(speed);
		background.setMainPath(background.getRoadPath());
	}
	
	private void showBackground()
	{
		RelativeLayout surface = (RelativeLayout)mainScreen.findViewById(R.id.mainLayout);
		surface.addView(background);
		
		Button shootButton = (Button)mainScreen.findViewById(R.id.shootButton);
		shootButton.bringToFront();
		
		TextView scoreTxtView = (TextView)mainScreen.findViewById(R.id.scoreTextView);
		scoreTxtView.bringToFront();
		
		TextView pointsTxtView = (TextView)mainScreen.findViewById(R.id.pointsTextView);
		pointsTxtView.bringToFront();
		
		TextView livesTxtView = (TextView)mainScreen.findViewById(R.id.livesTextView);
		livesTxtView.bringToFront();
		
		TextView livesNumbTxtView = (TextView)mainScreen.findViewById(R.id.livesNumbTextView);
		livesNumbTxtView.bringToFront();	
		
		mainSound.start();
	}
	
	private void checkLives()
	{
		if (player.checkLives())
		{
			stop = true;
			background.setStop(true);
			mainSound.stop();
			mainScreen.finish();
			mainScreen.startActivity(new Intent("com.EndlessGame.GUI.GAMEOVERSCREEN"));
		}
	}

	//getters and setters
	public BackGroundMoveable getBackground() {
		return background;
	}

	public void setBackground(BackGroundMoveable background) {
		this.background = background;
	}

	
	public boolean isBulletVehicleCollision() {
		return bulletVehicleCollision;
	}

	public void setBulletVehicleCollision(boolean bulletVehicleCollision) {
		this.bulletVehicleCollision = bulletVehicleCollision;
	}

	
}
