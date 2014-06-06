package com.EndlessGame.GUI;

import java.util.Random;

import com.EndlessGame.GUI.R;

import BusinessLogic.*;
import android.widget.Button;
import android.widget.RelativeLayout;

public class PathAnimator extends Thread{
	private int speed;
	private int seconds;
	private MainScreen mainScreen;
	private BackGroundMoveable background;
	private PathGraph pathGraph;
	private boolean teletransCollision;
	private Node currentNode;

	private PlayerInfo player;
	
	public PathAnimator(MainScreen pMainScreen)
	{				
		this.mainScreen = pMainScreen;
		this.speed = seconds = 0;
		
		pathGraph = new PathGraph();
		currentNode = pathGraph.setInitialIntersection();
		pathGraph.generateLevel(currentNode);
		
		background = new BackGroundMoveable(mainScreen);
		putBackground();
		player = new PlayerInfo("");
		teletransCollision = false;
	}
	
	public PlayerInfo getPlayer() 
	{
		return player;
	}
	
	public void run(){
		boolean stop = false;
		while(!stop){
			if (seconds < 50)
			{
				increaseSpeed();
				
				if (seconds == 25)
					activateEnemies();
			}
			else	
				activateTeletransporters();
					
			// Verify collision and select new currentNode and visit it
			if (teletransCollision)
				checkTeletransporterCollision();
	
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
		if(speed<8){
			speed++;
			background.setSpeed(speed);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
			
				e1.printStackTrace();
			}
		}
		
	}
	
	public void activateEnemies()
	{              
        Random randomGenerator = new Random();
        int enemiesNumb = randomGenerator.nextInt(3) +1;
		background.setEnemiesAmount(enemiesNumb);
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
				
				if (currentNode.getIsReturn())
				{
					pathGraph.loadHashVisitedNodes(currentNode);
					currentNode = pathGraph.selectVisitedNode(currentNode);
					System.out.println("SI SE DEVOLVIO: "+ currentNode.getId() +" SEMILLA: "+ currentNode.getSeed() + " HIJOS: " + currentNode.getRealArcs().size());
					currentNode.getSeedOp().setInitialSeed(currentNode.getSeed());
				}
				
				//generate new level
				pathGraph.generateLevel(currentNode);				
				
				// Activate collision
				teletransCollision = false;
				
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
	
	protected void putBackground(){
		RelativeLayout surface = (RelativeLayout)mainScreen.findViewById(R.id.mainLayout);
		surface.addView(background);
		Button button = (Button)mainScreen.findViewById(R.id.button1);
		button.bringToFront();
	}

}
