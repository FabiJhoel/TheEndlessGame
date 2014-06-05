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

		/*PathGraph pg = new PathGraph();*/
		        
	       /* BigInteger seed = BigInteger.valueOf(6);
	        Node primero = new Node(seed,1,""); 
	        primero.setId(9);
	        primero.setLevel(3);
	        primero.generateAdjacents();
	        Node prueba = null;
	        
	        for (Node n : primero.getNextArcs())
	        {
	        	n.generateAdjacents();
	        	prueba = n;
	        }
	        
	        ArrayList<Node> padres = primero.getAllParents();
	        
	        for (Node n : padres)
	        {
	        	System.out.println("PADRE : " + n.getId() + " " + n.getSeed());
	        }*/

	       
	        
	      /*  pg.setInitialIntersection();
	        pg.generateLevel();
	        pg.generateLevel();
	        pg.generateLevel();*/
	        
	        /*BigInteger seed = BigInteger.valueOf(6);
	        Node primero = new Node(seed,1,"");       
	        primero.generateAdjacents();
	        
	        for (Node a : primero.getNextArcs())
	        {
	        	a.generateAdjacents();
	        }
	        
	        BigInteger seed2 = BigInteger.valueOf(310523254145208177L);
	        Node segundo = new Node(seed2,1,"");
	        
	        segundo.getSeedOp().setInitialSeed(seed2);
	        
	        segundo.generateAdjacents();
	        
	        for (Node a : segundo.getNextArcs())
	        {
	        	a.generateAdjacents();
	        }
	        
	        
	        ArrayList<Node> paths = new ArrayList<Node>();
	        paths.add(primero);
	        paths.add(segundo);
	        Node resp = pg.suggestPath(paths);
	        
	        System.out.println(resp.getSeed() + "  Nivel: " + resp.getLevel());*/
		
	}

	public void btnStartOnClick(View view){
		startActivity(new Intent("com.EndlessGame.GUI.MAINSCREEN"));
	}

}
