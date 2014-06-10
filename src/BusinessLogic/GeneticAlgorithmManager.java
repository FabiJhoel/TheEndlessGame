package BusinessLogic;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithmManager {
	private AdaptabilityFunction adaptabilityFunction;

    public GeneticAlgorithmManager() {
        adaptabilityFunction = new AdaptabilityFunction();
    }
    
    public WeaponProperties createNewGeneration(WeaponProperties pParentA, ArrayList<WeaponProperties> pWeaponsPopulation){ //here the algorithm crosses parents
        WeaponProperties parentB = selectParent(pWeaponsPopulation);

        /*Mating Color*/
        int[] newColor = new int[3];
        for (int index = 0; index < 3; index++){
            newColor[index] = (crossChromosomes((byte)(pParentA.getColor()[index]), 
                                               (byte)(parentB.getColor()[index])) & (0xff));
        }
 
        /*Mating Thickness*/
        byte newThickness = crossChromosomes(pParentA.getThicknessByteRepresentacion(), 
                                             parentB.getThicknessByteRepresentacion());
        
        /*Mating PolygonPoints*/
        byte newPolygonPoints = crossChromosomes(pParentA.getPolygonPointsByteRepresentacion(),
                                                 parentB.getPolygonPointsByteRepresentacion());
        
        /*Mating laneAmount*/
        byte newLaneAmount = crossChromosomes(pParentA.getLaneAmountByteRepresentation(), 
                                             parentB.getLaneAmountByteRepresentation());
             
        /*Control population size*/
        controlPopulationSize(pWeaponsPopulation);
        
        return new WeaponProperties(newThickness, newPolygonPoints, newLaneAmount, newColor);  
    }
    
    public WeaponProperties selectParent(ArrayList<WeaponProperties> pWeaponsPopulation){
    	try
    	{
	        Random rand = new Random();
	        ArrayList<Integer> possibleParents = adaptabilityFunction.getPossibleParents(pWeaponsPopulation);
	        int selectedParent = (rand.nextInt(((possibleParents.size()-1)-0)+1)+0);
	        return pWeaponsPopulation.get(possibleParents.get(selectedParent));
    	}
    	catch(Exception e)
    	{
    		System.out.println("ERROR: GeneticAlgorithmManager.selectParent() failure");
    		return null;
    	}
    }
    
    private byte crossChromosomes (byte parentA, byte parentB){
        Random rand = new Random();
        byte offspring = 0;
        int section = (rand.nextInt((7-1)+1)+1);
        int section2 = 8 - section;
        byte masqueradeA = generateMasquerade(section,false);
        byte masqueradeB = generateMasquerade(section2, true);
        offspring = (byte)((parentA & masqueradeA) | (parentB & masqueradeB));
        
        //Mutation
        if (((int)(Math.random()*(100-1))) > 90){ //10%
            int mutatedBit = (int)(Math.random()*(7-0));
            if ((offspring & (1 << mutatedBit)) == 0)
                offspring = (byte)(offspring | (1 << mutatedBit));
            else
                offspring = (byte)(offspring & ~(1 << mutatedBit));

            System.out.println("\nMUTO EN BIT: "+mutatedBit);
        }
        
        return offspring;
    }
    
    private byte generateMasquerade(int pSize, boolean shift){
        int masquerade = 0b00000000;
        for (int counter = 0; counter < pSize; counter++){
            masquerade = (masquerade | (int)Math.pow(2, counter));
        }
        if (shift)
            masquerade = masquerade<<(8-pSize);
        return (byte)masquerade;
    }
    
    private void controlPopulationSize(ArrayList<WeaponProperties> pWeaponsPopulation){
        //remove population excess using random death
        if(pWeaponsPopulation.size() >= 20){
            pWeaponsPopulation.remove((int)(Math.random()*(19-0)));
        }
    }
}
