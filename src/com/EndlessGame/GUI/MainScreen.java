package com.EndlessGame.GUI;

import com.EndlessGame.GUI.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import BusinessLogic.*;

public class MainScreen extends Activity{

	private PathAnimator pathAnimator;
	private TextView pointsTxtView;
	private TextView livesNumbTxtView;
	int i =0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //No Title
		setContentView(R.layout.main_screen);
		
		pointsTxtView = (TextView)findViewById(R.id.pointsTextView);	
		livesNumbTxtView = (TextView)findViewById(R.id.livesNumbTextView);		
		
		pathAnimator = new PathAnimator(this);
		pathAnimator.start();
		updatePlayerInfo();

	}
	
	private void updatePlayerInfo() {

	    new Thread() 
	    {
	        public void run() 
	        {
	            while (true) 
	            {
	                try {
	                    runOnUiThread(new Runnable() 
	                    {
	                        @Override
	                        public void run() 
	                        {
	                            pointsTxtView.setText(String.valueOf(pathAnimator.getPlayer().getScore()));
	                            livesNumbTxtView.setText(String.valueOf(pathAnimator.getPlayer().getLives()));
	                        }
	                    });
	                    
	                    Thread.sleep(300);   
	                } 
	                
	                catch (InterruptedException e) 
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }.start();
	}

	public void shootButtonOnClick(View view)
	{
		MediaPlayer sound = MediaPlayer.create(this, R.raw.disparo_bueno);
		sound.seekTo(900);
		sound.start();
		pathAnimator.getBackground().getVehicle().addBullet();
		pathAnimator.setBulletVehicleCollision(true);
	}
}
