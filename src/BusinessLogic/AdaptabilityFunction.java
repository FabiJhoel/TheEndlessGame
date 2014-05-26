package BusinessLogic;

import java.util.ArrayList;

public class AdaptabilityFunction {
	public AdaptabilityFunction() {
    }
    
    /*
     * The adaptability funtion select individuals with the 
     * the biggest weapon scope for mating. For that reason, 
     * only weapons with scope higher than the average of 
     * the population will fit. 
    */
    public ArrayList<Integer> getPossibleParents(ArrayList<Weapon> pWeaponsPopulation){
        double laneAverage = getPopulationAverage(pWeaponsPopulation);
        ArrayList<Integer> possibleParents = new ArrayList<>();
        for (int index = 0; index < pWeaponsPopulation.size(); index++){
            if (pWeaponsPopulation.get(index).getLaneAmount() >= laneAverage){
                possibleParents.add(index);
            }
        }
        return possibleParents;
    }
    
    public double getPopulationAverage (ArrayList<Weapon> pWeaponsPopulation){
        double average = 0;
        for (Weapon weapon: pWeaponsPopulation){
            average += weapon.getLaneAmount();
        }
        average /= pWeaponsPopulation.size();
        return average;
    }
}
