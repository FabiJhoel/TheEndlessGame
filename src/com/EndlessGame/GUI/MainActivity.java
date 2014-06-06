package com.EndlessGame.GUI;

import com.EndlessGame.GUI.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import BusinessLogic.*;

import java.math.BigInteger;
import java.util.ArrayList;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //No Title
		setContentView(R.layout.main_menu);
	}

	public void btnStartOnClick(View view){
		startActivity(new Intent("com.EndlessGame.GUI.MAINSCREEN"));
	}

}
