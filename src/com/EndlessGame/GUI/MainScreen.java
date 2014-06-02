package com.EndlessGame.GUI;

import com.example.guiexample.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import BusinessLogic.*;

public class MainScreen extends Activity{

	private PathAnimator pathAnimator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //No Title
		setContentView(R.layout.main_screen);

		pathAnimator = new PathAnimator(this);
		pathAnimator.start();
	}

}
