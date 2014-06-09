package com.EndlessGame.GUI;

import java.util.Random;

import com.EndlessGame.GUI.R;

import BusinessLogic.*;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PathAnimator extends Thread{
	private int speed;
	private int seconds;
	private MainScreen mainScreen;
	private BackGroundMoveable background;
	private PathGraph pathGraph;
	private boolean teletransCollision, enemiesCollision;
	private Node currentNode;
	//private TextView pointsTxtView;
	//private TextView livesNumbTxtView;
	private PlayerInfo player;
	private boolean stop;
	
	
	public PathAnimator(MainScreen pMainScreen)
	{				
		this.mainScreen = pMainScreen;
		this.speed = seconds = 0;
		
		pathGraph = new PathGraph();
		currentNode = pathGraph.setInitialIntersection();
		pathGraph.generateLevel(currentNode);
		
		background = new BackGroundMoveable(mainScreen);
		showBackground();
		teletransCollision = enemiesCollision = false;
		stop = false;
		
		player = new PlayerInfo("");
	}
	
	public PlayerInfo getPlayer() {
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
			}
			
			else	
				activateTeletransporters();
					
			// Verify collision
			if (teletransCollision)
				checkTeletransporterCollision();
			if (enemiesCollision)
				checkEnemiesCollision();
	
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
	
	public void increaseSpeed(){
		if(speed < 5)
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
	
	public void activateEnemies()
	{              
        Random randomGenerator = new Random();
        int enemiesNumb = randomGenerator.nextInt(3) +1;
		background.setEnemiesAmount(enemiesNumb);
		
		//activate enemies collision check
		enemiesCollision = true;
	}
	
	public void activateTeletransporters()
	{
		activateBillboard();
		background.setTeletransportersAmount(currentNode.getRealArcs().size()); 
		seconds = 0;	
		teletransCollision = true;
	}
	
	public void activateBillboard()
	{
		background.setIntersectionNumb(currentNode.getSeed().toString());
		background.setIntersectionName(currentNode.getName());
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
	
	protected void checkTeletransporterCollision(){
		
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
				teletransport();
				
				System.out.println("ESCOGIDO: "+(index+1));
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
					player.accumulateScore(2);
				else
					player.accumulateScore(3);	
				
				//generate new level
				pathGraph.generateLevel(currentNode);				
				
				// Activate collision
				teletransCollision = false;
				
				break;
			}
		}		
	}

	protected void checkEnemiesCollision()
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
				player.setLives(player.getLives()-1);
				System.out.println("VIDAS RESTANTES: "+player.getLives());
				// Deactivate collision
				enemiesCollision = false;
				checkLives();
				break;
			}
		}	
	}
	
	protected void teletransport(){
		int warpSpeed = 40;
		background.setWarp(true);
		background.setMainPath(background.getWarpPath());
		background.setSpeed(warpSpeed);
		background.setTeletransportersAmount(0);
		background.setBillboardAmount(0);
		/*background.clearBillboard();
		background.clearTeletransporters();*/
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		background.setWarp(false);
		background.setSpeed(speed);
		background.setMainPath(background.getRoadPath());
	}
	
	protected void showBackground()
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
	}
	
	protected void checkLives()
	{
		if (player.checkLives())
		{
			stop = true;
			background.setStop(true);
		}
	}


}
