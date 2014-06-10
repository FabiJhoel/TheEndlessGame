package com.EndlessGame.GUI;

import com.EndlessGame.GUI.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity {

	private MediaPlayer musicTheme;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //No Title
		setContentView(R.layout.main_menu);
		
		musicTheme = MediaPlayer.create(this, R.raw.main_menu_theme);
		musicTheme.setVolume((float)0.5, (float)0.5);
		musicTheme.setLooping(true);
		musicTheme.start();
	}
	
	public void btnStartOnClick (View view)
	{
		musicTheme.stop();
		this.finish();
		startActivity(new Intent("com.EndlessGame.GUI.MAINSCREEN"));
	}
}
