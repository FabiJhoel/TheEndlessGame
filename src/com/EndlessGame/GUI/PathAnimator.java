package com.EndlessGame.GUI;

import java.util.ArrayList;

import com.example.guiexample.R;

import android.widget.RelativeLayout;

public class PathAnimator extends Thread{
	private int speed;
	private int seconds;
	private MainScreen mainScreen;
	private BackGroundMoveable background;
	
	public PathAnimator(MainScreen pMainScreen){
		this.mainScreen = pMainScreen;
		this.speed = seconds = 0;
		background = new BackGroundMoveable(mainScreen);
		putBackground();
	}
	
	public void run(){
		boolean stop = false;
		while(!stop){
			if (seconds < 50){
				increaseSpeed();
			}
			else{
				activateTeletransporters();
			}
			isTeletransporterCollition();
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			seconds++;
		}
	}
	
	public void increaseSpeed(){
		if(speed<12){
			speed++;
			background.setSpeed(speed);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public void activateTeletransporters(){
		background.setTeletransportersAmount(3);  ///////////Indicar cantidad de Intersecciones
		seconds = 0;
	}
	
	protected void isTeletransporterCollition(){
		boolean collition = false;
		Vehicle vehicle = background.getVehicle();

		for (Teletransporter teletransporter : background.getTeletransporters()){
			collition = teletransporter.getCoordX() < vehicle.getCoordX() &&
						vehicle.getCoordX() > teletransporter.getCoordX() + teletransporter.getWidth() && 
						teletransporter.getCoordY() > vehicle.getCoordY() && 
						vehicle.getCoordY() < teletransporter.getCoordY() + teletransporter.getHeight();
			if(collition){
				teletransport();
				System.out.println(collition);
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
			Thread.sleep(3000);
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
