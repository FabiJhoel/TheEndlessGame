package com.EndlessGame.GUI;

import java.util.Random;

import com.EndlessGame.GUI.R;

import BusinessLogic.*;
import android.widget.RelativeLayout;

public class PathAnimator extends Thread{
	private int speed;
	private int seconds;
	private MainScreen mainScreen;
	private BackGroundMoveable background;
	private PathGraph pathGraph;
	private Node currentNode;
	
	public PathAnimator(MainScreen pMainScreen)
	{
		// Logical path
		pathGraph = new PathGraph();
		
		// Load 4 levels and set initial position
		currentNode = pathGraph.setInitialIntersection();	
		
		pathGraph.generateLevel();// Children of initial node
		pathGraph.generateLevel();
		pathGraph.generateLevel(); 
		
		pathGraph.visitIntersection(currentNode);
				
		this.mainScreen = pMainScreen;
		this.speed = seconds = 0;
		background = new BackGroundMoveable(mainScreen);
		putBackground();
	}
	
	public void run(){
		boolean stop = false;
		while(!stop){
			if (seconds < 50)
			{
				increaseSpeed();
				
				if (seconds == 25)
				{
					activateEnemies();
				}
			}
			else
			{	//Verify if currentNode is a return path
				if (currentNode.getIsReturn())
					currentNode = pathGraph.selectVisitedNode(currentNode);
				
				activateTeletransporters();

				// Generate new level
				pathGraph.generateLevel(); 
			}
			
			// Verify collision and select new currentNode and visit it
			checkTeletransporterCollision();
			// currentNode = el node que indica la colisión
			pathGraph.visitIntersection(currentNode);		
			
			
			
			try 
			{
				Thread.sleep(200);
			} 
			
			catch (InterruptedException e1) 
			{
				// TODO Auto-generated catch block				
				e1.printStackTrace();
			}
			
			seconds++;
		}
	}
	
	public void increaseSpeed(){
		if(speed<6){
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
		int enemiesNumb = 0;  
		
		for (int i = 0; i<10; i++)
        {   
			enemiesNumb = 0;                      
            Random randomGenerator = new Random();
            
            while (enemiesNumb == 0)
            {
                enemiesNumb = randomGenerator.nextInt(4);
            }
        }
		
		background.setEnemiesAmount(enemiesNumb);
	}
	
	public void activateTeletransporters(){

		background.setTeletransportersAmount(3/*currentNode.getNextArcs().size()*/);  ///////////Indicar cantidad de Intersecciones
		seconds = 0;
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
			if(collition){
				teletransport();
				System.out.println("ESCOGIDO: "+(index+1));
				break;
			}
		}		
	}
	
	protected void teletransport(){
		int warpSpeed = 40;
		background.setWarp(true);
		background.setMainPath(background.getWarpPath());
		background.setSpeed(warpSpeed);
		background.setTeletransportersAmount(-1);
		background.clearTeletransporters();
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
	}

}
