package BusinessLogic;

import java.util.ArrayList;
import java.util.Random;

public class WeaponPopulationAdministrator {
	private ArrayList<WeaponProperties> weaponsPopulation = new ArrayList<>();
    private GeneticAlgorithmManager geneticAlgorithmManager;

    public WeaponPopulationAdministrator() {
        geneticAlgorithmManager = new GeneticAlgorithmManager();
    }
    
    public void addWeapon(WeaponProperties pWeapon){
        weaponsPopulation.add(pWeapon);
    }

    public WeaponProperties generateDefaultWeapon(){
    	try
    	{
	        Random rand = new Random();
	        //random function:  ((max-min)+1)+min
	        byte thicknessByteRepresentacion = (byte)(rand.nextInt((255-0)+1)+0);
	        byte polygonPointsByteRepresentacion = (byte)(rand.nextInt((255-0)+1)+0);
	        byte laneAmountByteRepresentation = (byte)(rand.nextInt((255-0)+1)+0);
	        int colorSelected = rand.nextInt((3-1)+1)+1;
	        int[] color = new int[3];
	        switch(colorSelected){
	            case 1: //verde
	                color[0] = 0;
	                color[1] = 255;
	                color[2] = 77;
	                break;
	            case 2: //rojo 
	                color[0] = 255;
	                color[1] = 0;
	                color[2] = 51;
	                break;
	            case 3: //amarillo
	                color[0] = 255;
	                color[1] = 255;
	                color[2] = 0;
	                break;
	        }
	        
	        return new WeaponProperties(thicknessByteRepresentacion, polygonPointsByteRepresentacion, 
	                laneAmountByteRepresentation, color);
    	}
    	catch(Exception e)
    	{
    		System.out.println("ERROR: WeaponPopulationAdministrator.generateDefaultWeapon() failure");
    		return null;
    	}
    }
    
    public WeaponProperties generateWeapon(WeaponProperties pWeapon){
    	try
    	{
	        WeaponProperties newWeapon = geneticAlgorithmManager.createNewGeneration(pWeapon, weaponsPopulation);
	        return newWeapon;
    	}
    	catch(Exception e)
    	{
    		System.out.println("ERROR: WeaponPopulationAdministrator.generateWeapon() failure");
    		return null;
    	}
    }
}
