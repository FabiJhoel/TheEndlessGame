package com.example.theendlessgame;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import BusinessLogic.*;

import java.math.BigInteger;
import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        PathGraph pg = new PathGraph();
        
        /*pg.setInitialIntersection();
        pg.generateLevel();
        pg.generateLevel();
        pg.generateLevel();
        pg.generateLevel();
        pg.generateLevel();*/
        
        BigInteger seed = BigInteger.valueOf(6);
        Node primero = new Node(seed,1,"");       
        primero.generateAdjacents();
        
        for (Node a : primero.getNextArcs())
        {
        	a.generateAdjacents();
        }
        
        BigInteger seed2 = BigInteger.valueOf(310523254145208177L);
        Node segundo = new Node(seed2,1,"");
        segundo.generateAdjacents();
        
        for (Node a : segundo.getNextArcs())
        {
        	a.generateAdjacents();
        }
        
        
        ArrayList<Node> paths = new ArrayList<Node>();
        paths.add(primero);
        paths.add(segundo);
        Node resp = pg.suggestPath(paths);
        
        System.out.println(resp.getSeed() + "  Nivel: " + resp.getLevel());
        
      /*  BigInteger seed = BigInteger.valueOf(6);
        BigInteger m = BigInteger.valueOf((long)Math.pow(2, 60) + 1);
        BigInteger a = BigInteger.valueOf(9301);
        BigInteger c = BigInteger.valueOf(49297);
                

        
        for (int i= 0; i<20; i++)
        {
        	//seed = (a*seed + c) % m
        	
        	System.out.println(seed);

        	seed = seed.multiply(a);
        	seed = seed.add(c);
        	seed = seed.mod(m);
        	
        }*/

    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
