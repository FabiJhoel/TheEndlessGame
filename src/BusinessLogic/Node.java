package BusinessLogic;

import java.util.ArrayList;
import java.math.BigInteger;

public class Node {
	
	private BigInteger seed; // intersection number
	private int level;
	private boolean visited;
	private String name;
	private boolean isReturn;
	private Node parentArc;
	private ArrayList<Node> nextArcs = new ArrayList<Node>();
	private Billboard billboard;
	private SeedOperator seedOp = SeedOperator.getInstance();
	
	public Node(BigInteger pSeed, int pLevel, String pName){
		
		seed = pSeed;
		level = pLevel;
		name = pName;
		visited = false;
	}

	public BigInteger getSeed() {
		return seed;
	}

	public void setSeed(BigInteger seed) {
		this.seed = seed;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean getVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}
	
	public Billboard getBillboard()
	{
		return this.billboard;
	}
	
	public void setBillboard(Billboard billboard)
	{
		this.billboard = billboard;
	}
	
	public Node getParentArc(){
		
		return parentArc;
	}
	
	public void setParentArc(Node previousArc){
		
		parentArc = previousArc;
	}
	
	public ArrayList<Node> getNextArcs() {
		return nextArcs;
	}

	public void addNextArc(Node nextArc) {
		
		if (findArc(nextArc) == -1)
			nextArcs.add(nextArc);
	}

	public int findArc(Node pNode){
		
        for (int cont = 0; cont < nextArcs.size(); cont++) 
        {
            if (pNode.seed == nextArcs.get(cont).seed)
                return cont;
        }
        
        return -1;
    }	
	
	 public int assignLevel(int parentLevel)
    {
    	int level = parentLevel + 1;
    	
    	if (level <= 4)
    		return level;
    	
    	else
    		return 1;
    }
	
	public void generateAdjacents()
	{
		int numbOfChildren = seedOp.getNumbOfNextIntersections(seed);
		BigInteger childrenSeed = null; 
		
		System.out.println("PADRE #" + seed + " Nivel: " + level);
		
		for (int contChildren = 0; contChildren < numbOfChildren; contChildren++)
		{		
			childrenSeed = seedOp.getNewSeed();			
			addNextArc(new Node(childrenSeed, assignLevel(level), ""));
		}
		
		for (Node c : getNextArcs())
		{
			System.out.println("HIJO #" + c.getSeed() + " Nivel: " + c.getLevel());
		}
	}
	

}
