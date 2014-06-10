package com.EndlessGame.GUI;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class GameOverScreen extends Activity{

	private MediaPlayer musicTheme;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //No Title
		setContentView(R.layout.game_over);
		
		musicTheme = MediaPlayer.create(this, R.raw.game_over_theme);
		musicTheme.setVolume((float)0.5, (float)0.5);
		musicTheme.setLooping(true);
		musicTheme.start();
	}
}
